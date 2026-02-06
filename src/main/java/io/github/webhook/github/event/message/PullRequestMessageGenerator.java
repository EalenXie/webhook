package io.github.webhook.github.event.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.github.dto.PullRequest;
import io.github.webhook.github.dto.PullRequestPayload;
import io.github.webhook.github.dto.Repository;

import java.util.Collections;

public class PullRequestMessageGenerator implements MessageGenerator<PullRequestPayload> {
    @Override
    public WebhookMessage generate(Webhook webhook, PullRequestPayload payload) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle("Pull Request");
        Repository repository = payload.getRepository();
        PullRequest pullRequest = payload.getPullRequest();
        message.setNotifies(Collections.singletonList(repository.getOwner().getEmail()));
        String action = payload.getAction();
        String emoji = "";
        if ("opened".equals(action)) {
            emoji = "\uD83D\uDCAC";
        } else if ("closed".equals(action)) {
            emoji = "\uD83D\uDEAB";
        }
        StringBuilder sb = new StringBuilder();
        String repo = String.format("[[%s]](%s)", repository.getName(), repository.getHtmlUrl());
        String pr = String.format("[[Pull request#%s]](%s)", pullRequest.getNumber(), pullRequest.getHtmlUrl());
        String sender = String.format("[%s](%s)", payload.getSender().getLogin(), payload.getSender().getHtmlUrl());
        sb.append(String.format("%s %s %s%s by %s %n%n%s", repo, pr, action, emoji, sender, pullRequest.getTitle()));
        message.setMessage(sb.toString());
        return message;
    }
}
