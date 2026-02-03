package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.tag.TagPushHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class TagPushHookNotifyEventHandler extends NotifyEventHandler<TagPushHook> {

    public TagPushHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<TagPushHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    public Class<TagPushHook> getDataType() {
        return TagPushHook.class;
    }

    @Override
    public boolean shouldHandleEvent(Webhook webhook, TagPushHook data) {
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            return MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getRef());
        }
        return super.shouldHandleEvent(webhook, data);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, TagPushHook data) {
        return Objects.equals(data.getObjectKind(), "tag_push");
    }


}
