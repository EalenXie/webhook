package io.github.webhook.github.event.message;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.github.dto.ForkPayload;
import io.github.webhook.github.dto.Repository;
import io.github.webhook.github.dto.User;
import io.github.webhook.meta.Webhook;

import java.util.Collections;

public class ForkMessageGenerator implements MessageGenerator<ForkPayload> {

    @Override
    public WebhookMessage generate(Webhook webhook, ForkPayload payload) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle("fork");
        Repository repository = payload.getRepository();
        message.setNotifies(Collections.singletonList(String.valueOf(repository.getOwner().getEmail())));
        User sender = payload.getSender();
        message.setMessage(String.format("[%s](%s) fork project [%s](%s) ☺️ %n%n current fork↙️ is `%s` ", sender.getLogin(), sender.getHtmlUrl(), repository.getName(), repository.getHtmlUrl(), repository.getForksCount()));
        return message;
    }
}
