package io.github.webhook.gitlab.message;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.tag.TagPushHook;
import io.github.webhook.meta.Webhook;

import java.util.Collections;

public class TagPushHookMessageGenerator implements MessageGenerator<TagPushHook> {
    @Override
    public WebhookMessage generate(Webhook webhook, TagPushHook tagPushHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(tagPushHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(tagPushHook.getUserId())));
        Project project = tagPushHook.getProject();
        String userUsername = tagPushHook.getUserUsername();
        String[] refSplit = tagPushHook.getRef().split("/");
        String tag = refSplit[refSplit.length - 1];
        String t = String.format("[%s](%s/-/tree/%s)", tag, project.getWebUrl(), tag);
        String p = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String user = String.format("[%s](%s)", userUsername, MessageGenerator.getUserHomePage(project.getWebUrl(), userUsername));
        message.setMessage(String.format("%s push new tag(%s) by %s %s%n%n > %s", p, t, user, "\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80", tagPushHook.getMessage()));
        return message;
    }
}
