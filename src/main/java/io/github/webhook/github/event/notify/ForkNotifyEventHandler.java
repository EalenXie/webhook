package io.github.webhook.github.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.ForkPayload;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class ForkNotifyEventHandler extends NotifyEventHandler<ForkPayload> {

    public ForkNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<ForkPayload> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    public Class<ForkPayload> getDataType() {
        return ForkPayload.class;
    }
}
