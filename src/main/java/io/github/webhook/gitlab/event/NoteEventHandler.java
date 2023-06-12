package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.note.NoteHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface NoteEventHandler extends EventHandler<NoteHook, Object> {
    default Class<NoteHook> getDataType() {
        return NoteHook.class;
    }

}
