package io.github.webhook.gitlab.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Issue;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.User;
import io.github.webhook.gitlab.webhook.note.NoteHook;

import java.util.Collections;

public class NoteHookMessageGenerator implements MessageGenerator<NoteHook> {
    @Override
    public WebhookMessage generate(Webhook webhook, NoteHook noteHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(noteHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(noteHook.getUser().getName())));
        User user = noteHook.getUser();
        Project project = noteHook.getProject();
        Issue issue = noteHook.getIssue();
        NoteHook.ObjectAttributes attributes = noteHook.getObjectAttributes();
        StringBuilder sb = new StringBuilder();
        String u = String.format("[%s](%s)", user.getUsername(), MessageGenerator.getUserHomePage(project.getWebUrl(), user.getUsername()));
        String i = String.format("[#%s](%s)", issue.getId(), issue.getUrl());
        String n = String.format("[%s](%s)", noteHook.getObjectKind(), attributes.getUrl());
        sb.append(String.format("<font color='#000000'>%s%s add new %s in Issue[%s]</font>%n%n", u, "\uD83E\uDDD0", n, i));
        sb.append(String.format("**%s**%n%n>%s%n", issue.getTitle(), attributes.getNote()));
        message.setMessage(sb.toString());
        return message;
    }

}
