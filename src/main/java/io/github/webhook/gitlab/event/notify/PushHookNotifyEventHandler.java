package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.push.PushHook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class PushHookNotifyEventHandler extends NotifyEventHandler<PushHook> {

    public PushHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<PushHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, PushHook data) {
        boolean shouldNotify = true;
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            shouldNotify = MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getRef());
        }
        if (shouldNotify) {
            return !ObjectUtils.isEmpty(data.getCommits());
        }
        return false;
    }


}
