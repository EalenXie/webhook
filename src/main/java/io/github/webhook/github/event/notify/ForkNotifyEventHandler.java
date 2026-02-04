package io.github.webhook.github.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.ForkPayload;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class ForkNotifyEventHandler extends NotifyEventHandler<ForkPayload> {

    public ForkNotifyEventHandler(MessageGenerator<ForkPayload> messageGenerator) {
        super(messageGenerator);
    }


}
