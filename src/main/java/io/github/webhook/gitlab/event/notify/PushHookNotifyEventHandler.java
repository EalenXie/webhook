package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.PushEventHandler;
import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.push.PushHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class PushHookNotifyEventHandler extends GitlabNotifyEventHandler<PushHook> implements PushEventHandler {

    public PushHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, PushHook data) {
        return !ObjectUtils.isEmpty(data.getCommits());
    }

    @Override
    public NotifyMessage generate(Webhook webhook, PushHook pushHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(pushHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(pushHook.getUserId())));
        List<Commit> commits = pushHook.getCommits();
        Project project = pushHook.getProject();
        String userUsername = pushHook.getUserUsername();
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String ref = pushHook.getRef();
        String branch = ref.replace("refs/heads/", "");
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

}
