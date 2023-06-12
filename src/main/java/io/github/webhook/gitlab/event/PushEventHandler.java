package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.push.PushHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface PushEventHandler extends EventHandler<PushHook, Object> {
    default Class<PushHook> getDataType() {
        return PushHook.class;
    }

}
