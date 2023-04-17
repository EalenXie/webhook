package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.NoteEventHandler;
import io.github.webhook.gitlab.vo.Issue;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.User;
import io.github.webhook.gitlab.vo.note.NoteHook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class NoteHookNotifyEventHandler extends GitlabNotifyEventHandler<NoteHook> implements NoteEventHandler {

    public NoteHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public NotifyMessage generate(NoteHook noteHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(noteHook.getObjectKind());
        // TODO
        message.setNotifies(null);
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
