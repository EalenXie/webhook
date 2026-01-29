package io.github.webhook.core;

import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;
import io.github.webhook.notify.NotifyMessageGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/17 13:07
 * 通知事件处理器
 */
public abstract class NotifyEventHandler<D> implements NotifyMessageGenerator<D>, EventHandler<D, Object> {

    private final NotifierFactory notifierFactory;

    protected NotifyEventHandler(NotifierFactory notifierFactory) {
        this.notifierFactory = notifierFactory;
    }


    @Override
    public boolean shouldHandleEvent(Webhook webhook, D data) {
        return true;
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
    public Object handleEvent(Webhook webhook, D data) {
        if (shouldNotify(webhook, data)) {
            List<Object> resp = new ArrayList<>();
            NotifyMessage message = generate(webhook, data);
            // 根据webhook 获取 Notifier
            List<Notifier<Object, Object>> notifies = notifierFactory.getNotifies(webhook);
            for (Notifier<Object, Object> notifier : notifies) {
                // Notifier 发起通知
                resp.add(notifier.notify(webhook, notifier.process(message)));
            }
            return resp.size() == 1 ? resp.get(0) : resp;
        }
        return Collections.emptyList();
    }
}
