package io.github.webhook.github.event.message;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.github.dto.Repository;
import io.github.webhook.github.dto.StarPayload;
import io.github.webhook.github.dto.User;
import io.github.webhook.meta.Webhook;

import java.util.Collections;

public class StarMessageGenerator implements MessageGenerator<StarPayload> {
    @Override
    public WebhookMessage generate(Webhook webhook, StarPayload payload) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle("star");
        Repository repository = payload.getRepository();
        message.setNotifies(Collections.singletonList(String.valueOf(repository.getOwner().getEmail())));
        String action = payload.getAction();
        String emoji = "";
        if ("created".equals(action)) {
            emoji = "☺️";
            action = "star";
        } else if ("deleted".equals(action)) {
            emoji = "\uD83D\uDE14";
            action = "cancelled the star ";
        }
        User sender = payload.getSender();
        message.setMessage(String.format("[%s](%s) %s project [%s](%s) %s %n%n current star⭐ is `%s` ", sender.getLogin(), sender.getHtmlUrl(), action, repository.getName(), repository.getHtmlUrl(), emoji, repository.getStargazersCount()));
        return message;
    }
}
