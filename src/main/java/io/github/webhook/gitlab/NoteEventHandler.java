package io.github.webhook.gitlab;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.vo.note.NoteHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface NoteEventHandler extends EventHandler<NoteHook> {
    default Class<NoteHook> getDataType() {
        return NoteHook.class;
    }

}
