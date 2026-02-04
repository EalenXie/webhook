package io.github.webhook.github.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.StarPayload;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class StarNotifyEventHandler extends NotifyEventHandler<StarPayload> {

    public StarNotifyEventHandler(MessageGenerator<StarPayload> messageGenerator) {
        super(messageGenerator);
    }

}
