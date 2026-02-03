package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.mergerequest.MergeRequestHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class MergeRequestHookNotifyEventHandler extends NotifyEventHandler<MergeRequestHook> {

    @Override
    public Class<MergeRequestHook> getDataType() {
        return MergeRequestHook.class;
    }

    public MergeRequestHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<MergeRequestHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, MergeRequestHook data) {
        String action = data.getObjectAttributes().getAction();
        return action != null && !"update".equals(action);
    }


}
