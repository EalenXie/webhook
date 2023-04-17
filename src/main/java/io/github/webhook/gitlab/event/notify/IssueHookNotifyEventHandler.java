package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.IssueEventHandler;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.User;
import io.github.webhook.gitlab.vo.issue.IssueHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class IssueHookNotifyEventHandler extends GitlabNotifyEventHandler<IssueHook> implements IssueEventHandler {

    public IssueHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, IssueHook data) {
        String issueAction = data.getObjectAttributes().getAction();
        return !"update".equals(issueAction);
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


}
