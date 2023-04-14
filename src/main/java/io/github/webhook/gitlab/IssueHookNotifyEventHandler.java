package io.github.webhook.gitlab;

import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.User;
import io.github.webhook.gitlab.vo.issue.IssueHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.MessageGenerator;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
@Component
public class IssueHookNotifyEventHandler implements MessageGenerator<IssueHook>, IssueEventHandler {

    @Resource
    private NotifierFactory notifierFactory;

    @Override
    public void handleEvent(Webhook webhook, IssueHook data) {
        NotifyMessage generate = generate(data);
        Notifier notifier = notifierFactory.getNotifier(webhook);
        notifier.notify(webhook, generate);
    }

    @Override
    public NotifyMessage generate(IssueHook issueHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(issueHook.getObjectKind());
        // TODO
        message.setNotifies(null);
        IssueHook.ObjectAttributes objectAttributes = issueHook.getObjectAttributes();
        Project project = issueHook.getProject();
        User user = issueHook.getUser();
        StringBuilder sb = new StringBuilder();
        String projectUrl = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String issue = String.format("[#%s](%s)", issueHook.getObjectAttributes().getId(), objectAttributes.getUrl());
        String titleEmoji = "";
        String statusEmoji = "";
        if (objectAttributes.getState().equals("opened")) {
            titleEmoji = "\uD83D\uDD34";
            statusEmoji = "\uD83D\uDE4B\u200D♂️";
        } else if (objectAttributes.getState().equals("closed")) {
            titleEmoji = "\uD83D\uDFE2";
            statusEmoji = "✌️";
        }
        sb.append(String.format("#### %s%s **%s** %n", titleEmoji, projectUrl, objectAttributes.getTitle()));
        sb.append(String.format("<font color='#000000'>The Issue [%s] %s%s by [%s](%s) </font> %n>%s", issue, objectAttributes.getState(), statusEmoji, user.getUsername(), getUserHomePage(project.getWebUrl(), user.getUsername()), objectAttributes.getDescription()));
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
