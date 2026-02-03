package io.github.webhook.github.event.notify;


import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.PushPayload;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/7/19 16:18
 */
public class PushNotifyEventHandler extends NotifyEventHandler<PushPayload> {


    public PushNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<PushPayload> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }



    @Override
    protected boolean shouldNotify(Webhook webhook, PushPayload data) {
        return !ObjectUtils.isEmpty(data.getCommits());
    }
}
