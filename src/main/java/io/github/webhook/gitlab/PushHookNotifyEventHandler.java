package io.github.webhook.gitlab;

import io.github.webhook.gitlab.vo.Commit;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.push.PushHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.MessageGenerator;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
@Component
public class PushHookNotifyEventHandler implements MessageGenerator<PushHook>, PushEventHandler {

    @Resource
    private NotifierFactory notifierFactory;

    @Override
    public void handleEvent(Webhook webhook, PushHook data) {
        NotifyMessage generate = generate(data);
        Notifier notifier = notifierFactory.getNotifier(webhook);
        notifier.notify(webhook, generate);
    }

    @Override
    public NotifyMessage generate(PushHook pushHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(pushHook.getObjectKind());
        // TODO
        message.setNotifies(null);
        List<Commit> commits = pushHook.getCommits();
        Project project = pushHook.getProject();
        String userUsername = pushHook.getUserUsername();
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String[] refSplit = pushHook.getRef().split("/");
        String branch = refSplit[refSplit.length - 1];
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) ", project.getName(), branch, project.getWebUrl(), branch));
        String c = commits.size() > 1 ? "commits" : "commit";
        String user = userUsername == null ? pushHook.getUserName() : String.format("[%s](%s)", userUsername, getUserHomePage(project.getWebUrl(), userUsername));
        sb.append(String.format("<font color='#000000'>%s %s new %s by %s %s </font>%n%n", pushHook.getEventName(), pushHook.getTotalCommitsCount(), c, "\uD83D\uDE00", user));
        for (Commit vo : commits) {
            sb.append(String.format("> [%s](%s) %s - %s%n%n", vo.getId().substring(0, 8), vo.getUrl(), vo.getAuthor().getName(), vo.getTitle()));
        }
        message.setMessage(sb.toString());
        return message;
    }

    static String getUserHomePage(String projectUrl, String username) {
        return String.format("%s/%s", getHostSchema(projectUrl), username);
    }

    static String getHostSchema(String projectUrl) {
        String schema = projectUrl.substring(0, projectUrl.indexOf("//"));
        String body = projectUrl.substring(projectUrl.indexOf("//") + 2);
        String host = body.substring(0, body.indexOf("/"));
        return schema + host;
    }


}
