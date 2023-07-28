package io.github.webhook.github.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.github.dto.PullRequestPayload;

/**
 * @author EalenXie created on 2023/7/28 13:35
 */
public interface PullRequestEventHandler extends EventHandler<PullRequestPayload, Object> {
    default Class<PullRequestPayload> getDataType() {
        return PullRequestPayload.class;
    }

}
