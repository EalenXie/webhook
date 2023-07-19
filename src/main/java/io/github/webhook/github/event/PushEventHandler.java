package io.github.webhook.github.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.github.dto.PushPayload;

/**
 * @author EalenXie created on 2023/7/19 16:10
 */
public interface PushEventHandler extends EventHandler<PushPayload, Object> {
    default Class<PushPayload> getDataType() {
        return PushPayload.class;
    }

}
