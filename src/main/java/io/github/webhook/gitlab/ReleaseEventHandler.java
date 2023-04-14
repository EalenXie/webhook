package io.github.webhook.gitlab;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.vo.release.ReleaseHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface ReleaseEventHandler extends EventHandler<ReleaseHook> {
    default Class<ReleaseHook> getDataType() {
        return ReleaseHook.class;
    }

}
