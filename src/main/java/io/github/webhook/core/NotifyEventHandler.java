package io.github.webhook.core;

import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;
import io.github.webhook.notify.NotifyMessageGenerator;

import java.util.List;

/**
 * @author EalenXie created on 2023/4/17 13:07
 * 通知事件处理器
 */
public abstract class NotifyEventHandler<D> implements NotifyMessageGenerator<D>, EventHandler<D> {

    private final NotifierFactory notifierFactory;

    protected NotifyEventHandler(NotifierFactory notifierFactory) {
        this.notifierFactory = notifierFactory;
    }

    /**
     * 是否执行通知
     *
     * @param webhook webhook信息
     * @param data    请求数据
     */
    protected boolean shouldNotify(Webhook webhook, D data) {
        return true;
    }

    @Override
    public void handleEvent(Webhook webhook, D data) {
        if (shouldNotify(webhook, data)) {
            NotifyMessage message = generate(webhook, data);
            // 根据webhook 获取 Notifier
            List<Notifier<Object>> notifies = notifierFactory.getNotifies(webhook);
            for (Notifier<Object> notifier : notifies) {
                // Notifier 发起通知
                notifier.notify(webhook, notifier.process(message));
            }
        }
    }
}
