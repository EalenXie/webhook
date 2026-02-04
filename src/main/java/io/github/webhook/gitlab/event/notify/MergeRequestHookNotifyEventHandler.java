package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.mergerequest.MergeRequestHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class MergeRequestHookNotifyEventHandler extends NotifyEventHandler<MergeRequestHook> {


    public MergeRequestHookNotifyEventHandler(MessageGenerator<MergeRequestHook> messageGenerator) {
        super(messageGenerator);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, MergeRequestHook data) {
        String action = data.getObjectAttributes().getAction();
        return action != null && !"update".equals(action);
    }


}
