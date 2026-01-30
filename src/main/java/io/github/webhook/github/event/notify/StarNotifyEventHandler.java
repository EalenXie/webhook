package io.github.webhook.github.event.notify;

import io.github.webhook.github.dto.Repository;
import io.github.webhook.github.dto.StarPayload;
import io.github.webhook.github.dto.User;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class StarNotifyEventHandler extends GithubNotifyEventHandler<StarPayload> {
    public StarNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public Class<StarPayload> getDataType() {
        return StarPayload.class;
    }

    @Override
    public NotifyMessage generate(Webhook webhook, StarPayload payload) {
        NotifyMessage message = new NotifyMessage();
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
