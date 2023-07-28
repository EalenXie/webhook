package io.github.webhook.github.event.notify;

import io.github.webhook.github.dto.ForkPayload;
import io.github.webhook.github.dto.Repository;
import io.github.webhook.github.dto.User;
import io.github.webhook.github.event.ForkEventHandler;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/7/27 10:19
 */
public class ForkNotifyEventHandler extends GithubNotifyEventHandler<ForkPayload> implements ForkEventHandler {
    public ForkNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, ForkPayload payload) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle("fork");
        Repository repository = payload.getRepository();
        message.setNotifies(Collections.singletonList(String.valueOf(repository.getOwner().getEmail())));
        User sender = payload.getSender();
        message.setMessage(String.format("[%s](%s) fork project [%s](%s) ☺️ %n%n current fork↙️ is `%s` ", sender.getLogin(), sender.getHtmlUrl(), repository.getName(), repository.getHtmlUrl(), repository.getForksCount()));
        return message;
    }
}
