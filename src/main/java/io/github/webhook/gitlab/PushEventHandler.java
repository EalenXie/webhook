package io.github.webhook.gitlab;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.vo.push.PushHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface PushEventHandler extends EventHandler<PushHook> {
    default Class<PushHook> getDataType() {
        return PushHook.class;
    }

}
