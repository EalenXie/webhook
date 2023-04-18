package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.mergerequest.MergeRequestHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface MergeRequestEventHandler extends EventHandler<MergeRequestHook> {
    default Class<MergeRequestHook> getDataType() {
        return MergeRequestHook.class;
    }

}
