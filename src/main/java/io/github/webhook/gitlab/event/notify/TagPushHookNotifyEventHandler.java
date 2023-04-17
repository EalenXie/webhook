package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.TagPushEventHandler;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.tag.TagPushHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Objects;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class TagPushHookNotifyEventHandler extends GitlabNotifyEventHandler<TagPushHook> implements TagPushEventHandler {

    public TagPushHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, TagPushHook data) {
        return Objects.equals(data.getObjectKind(), "tag_push");
    }

    @Override
    public NotifyMessage generate(TagPushHook tagPushHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(tagPushHook.getObjectKind());
        // TODO
        message.setNotifies(null);
        Project project = tagPushHook.getProject();
        String userUsername = tagPushHook.getUserUsername();
        String[] refSplit = tagPushHook.getRef().split("/");
        String tag = refSplit[refSplit.length - 1];
        String t = String.format("[%s](%s/-/tree/%s)", tag, project.getWebUrl(), tag);
        String p = String.format("[%s](%s)", project.getName(), project.getWebUrl());
        String user = String.format("[%s](%s)", userUsername, getUserHomePage(project.getWebUrl(), userUsername));
        message.setMessage(String.format("%s push new tag(%s) by %s %s%n%n > %s", p, t, user, "\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80", tagPushHook.getMessage()));
        return message;
    }

}
