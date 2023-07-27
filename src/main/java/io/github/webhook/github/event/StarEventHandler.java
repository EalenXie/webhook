package io.github.webhook.github.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.github.dto.StarPayload;

/**
 * @author EalenXie created on 2023/7/19 16:10
 */
public interface StarEventHandler extends EventHandler<StarPayload, Object> {
    default Class<StarPayload> getDataType() {
        return StarPayload.class;
    }

}
