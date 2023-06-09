package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.NoteEventHandler;
import io.github.webhook.gitlab.webhook.Issue;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.User;
import io.github.webhook.gitlab.webhook.note.NoteHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class NoteHookNotifyEventHandler extends GitlabNotifyEventHandler<NoteHook> implements NoteEventHandler {

    public NoteHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, NoteHook noteHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(noteHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(noteHook.getUser().getId())));
        User user = noteHook.getUser();
        Project project = noteHook.getProject();
        Issue issue = noteHook.getIssue();
        NoteHook.ObjectAttributes objectAttributes = noteHook.getObjectAttributes();
        StringBuilder sb = new StringBuilder();
        String u = String.format("[%s](%s)", user.getUsername(), getUserHomePage(project.getWebUrl(), user.getUsername()));
        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
        String n = String.format("[%s](%s)", noteHook.getObjectKind(), objectAttributes.getUrl());
        sb.append(String.format("<font color='#000000'>%s%s add new %s in Issue[%s]</font>%n%n", u, "\uD83E\uDDD0", n, i));
        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), objectAttributes.getNote()));
        message.setMessage(sb.toString());
        return message;
    }

}
