package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.release.ReleaseHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class ReleaseHookNotifyEventHandler extends NotifyEventHandler<ReleaseHook> {
    @Override
    public Class<ReleaseHook> getDataType() {
        return ReleaseHook.class;
    }

    public ReleaseHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<ReleaseHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }


    @Override
    protected boolean shouldNotify(Webhook webhook, ReleaseHook data) {
        String releaseAction = data.getAction();
        return !"update".equals(releaseAction);
    }


}
