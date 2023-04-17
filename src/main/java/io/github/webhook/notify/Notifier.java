package io.github.webhook.notify;

import io.github.webhook.meta.Webhook;

/**
 * @author EalenXie created on 2023/4/14 13:57
 */
public interface Notifier {


    /**
     * 消息通知
     *
     * @param webhook webhook信息
     * @param message 通知消息
     */
    void notify(Webhook webhook, NotifyMessage message);


}
