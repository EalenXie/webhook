package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.tag.TagPushHook;
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
    protected boolean shouldNotify(Webhook webhook, TagPushHook data) {
        boolean shouldNotify = true;
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            shouldNotify = MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getRef());
        }
        if (shouldNotify) {
            return Objects.equals(data.getObjectKind(), "tag_push");
        }
        return false;
    }

}
