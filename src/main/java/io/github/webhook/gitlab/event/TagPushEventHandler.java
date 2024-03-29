package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.tag.TagPushHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface TagPushEventHandler extends EventHandler<TagPushHook, Object> {
    default Class<TagPushHook> getDataType() {
        return TagPushHook.class;
    }

}
