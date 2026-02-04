package io.github.webhook.github.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.github.dto.PullRequestPayload;

/**
 * @author EalenXie created on 2023/7/19 16:18
 */
public class PullRequestNotifyEventHandler extends NotifyEventHandler<PullRequestPayload> {

    public PullRequestNotifyEventHandler(MessageGenerator<PullRequestPayload> messageGenerator) {
        super(messageGenerator);
    }


}
