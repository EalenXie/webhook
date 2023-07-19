package io.github.webhook.github.event.notify;

import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/7/19 16:14
 */
public abstract class GithubNotifyEventHandler<D> extends NotifyEventHandler<D> {

    protected GithubNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }
}
