package io.github.webhook.github.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.StarPayload;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class StarNotifyEventHandler extends NotifyEventHandler<StarPayload> {

    public StarNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<StarPayload> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    public Class<StarPayload> getDataType() {
        return StarPayload.class;
    }

}
