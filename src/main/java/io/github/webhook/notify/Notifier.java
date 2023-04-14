package io.github.webhook.notify;

import io.github.webhook.meta.Webhook;

/**
 * @author EalenXie created on 2023/4/14 13:57
 */
public interface Notifier {


    void notify(Webhook webhook, NotifyMessage message);



}
