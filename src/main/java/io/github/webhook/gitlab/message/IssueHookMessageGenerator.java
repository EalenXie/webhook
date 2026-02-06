package io.github.webhook.gitlab.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.User;
import io.github.webhook.gitlab.webhook.issue.IssueHook;

import java.util.Collections;

public class IssueHookMessageGenerator implements MessageGenerator<IssueHook> {
    @Override
    public WebhookMessage generate(Webhook webhook, IssueHook issueHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(issueHook.getObjectKind());
        message.setNotifies(Collections.singletonList(issueHook.getUser().getName()));
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
        sb.append(String.format("<font color='#000000'>The Issue [%s] %s%s by [%s](%s) </font> %n>%s", issue, attributes.getState(), statusEmoji, user.getUsername(), MessageGenerator.getUserHomePage(project.getWebUrl(), user.getUsername()),
                attributes.getDescription() == null ? "" : attributes.getDescription()));
        message.setMessage(sb.toString());
        return message;
    }

}
