package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.note.NoteHook;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class NoteHookNotifyEventHandler extends NotifyEventHandler<NoteHook> {

    @Override
    public Class<NoteHook> getDataType() {
        return NoteHook.class;
    }

    public NoteHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<NoteHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }


}
