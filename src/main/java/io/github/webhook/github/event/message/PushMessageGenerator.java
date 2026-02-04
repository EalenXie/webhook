package io.github.webhook.github.event.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.github.dto.Commit;
import io.github.webhook.github.dto.PushPayload;
import io.github.webhook.github.dto.Pusher;
import io.github.webhook.github.dto.Repository;

import java.util.Collections;
import java.util.List;

public class PushMessageGenerator implements MessageGenerator<PushPayload> {
    @Override
    public WebhookMessage generate(Webhook webhook, PushPayload payload) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle("push");
        message.setNotifies(Collections.singletonList(String.valueOf(payload.getPusher().getEmail())));
        List<Commit> commits = payload.getCommits();
        Repository repository = payload.getRepository();
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String ref = payload.getRef();
        String branch = ref.replace("refs/heads/", "");
        sb.append(String.format("[[%s:%s]](%s/tree/%s) ", repository.getName(), branch, repository.getHtmlUrl(), branch));
        String c = commits.size() > 1 ? "commits" : "commit";
        Pusher pusher = payload.getPusher();
        String name = pusher.getName();
        String user = String.format("[%s](https://github.com/%s)", name, name);
        sb.append(String.format("<font color='#000000'>%s %s new %s by %s %s </font>%n%n", "push", commits.size(), c, "\uD83D\uDE00", user));
        for (Commit commit : commits) {
            sb.append(String.format("> [%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getMessage()));
        }
        message.setMessage(sb.toString());
        return message;
    }
}
