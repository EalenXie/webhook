package io.github.webhook.github.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.github.dto.ForkPayload;

/**
 * @author EalenXie created on 2023/7/28 17:18
 */
public interface ForkEventHandler extends EventHandler<ForkPayload, Object> {
    default Class<ForkPayload> getDataType() {
        return ForkPayload.class;
    }
}
