package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.release.ReleaseHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface ReleaseEventHandler extends EventHandler<ReleaseHook, Object> {
    default Class<ReleaseHook> getDataType() {
        return ReleaseHook.class;
    }

}
