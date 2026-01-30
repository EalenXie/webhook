package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.User;
import io.github.webhook.gitlab.webhook.issue.IssueHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class IssueHookNotifyEventHandler extends GitlabNotifyEventHandler<IssueHook> {

    @Override
    public Class<IssueHook> getDataType() {
        return IssueHook.class;
    }

    public IssueHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, IssueHook data) {
        String issueAction = data.getObjectAttributes().getAction();
        return !"update".equals(issueAction);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, IssueHook issueHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(issueHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(issueHook.getUser().getId())));
        IssueHook.ObjectAttributes attributes = issueHook.getObjectAttributes();
        Project project = issueHook.getProject();
        User user = issueHook.getUser();
        StringBuilder sb = new StringBuilder();
        String projectUrl = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String issue = String.format("[#%s](%s)", attributes.getId(), attributes.getUrl());
        String emoji = "";
        String statusEmoji = "";
        if (attributes.getState().equals("opened")) {
            emoji = "\uD83D\uDD34";
            statusEmoji = "\uD83D\uDE4B\u200D♂️";
        } else if (attributes.getState().equals("closed")) {
            emoji = "\uD83D\uDFE2";
            statusEmoji = "✌️";
        }
        sb.append(String.format("#### %s%s **%s** %n", emoji, projectUrl, attributes.getTitle()));
        sb.append(String.format("<font color='#000000'>The Issue [%s] %s%s by [%s](%s) </font> %n>%s", issue, attributes.getState(), statusEmoji, user.getUsername(), getUserHomePage(project.getWebUrl(), user.getUsername()),
                attributes.getDescription() == null ? "" : attributes.getDescription()));
        message.setMessage(sb.toString());
        return message;
    }


}
