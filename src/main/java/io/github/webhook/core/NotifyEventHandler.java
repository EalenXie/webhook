package io.github.webhook.core;

import io.github.webhook.config.SpringEnvHelper;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知事件处理器
 *
 * @author EalenXie created on 2023/4/17 13:07
 */
public abstract class NotifyEventHandler<D> implements EventHandler<D, Object> {

    /**
     * 消息生成器
     */
    private final MessageGenerator<D> messageGenerator;

    protected NotifyEventHandler(MessageGenerator<D> messageGenerator) {
        this.messageGenerator = messageGenerator;
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
    @Nullable
    public Object handleEvent(Webhook webhook, String event, D data) {
        if (shouldNotify(webhook, data)) {
            List<Object> resp = new ArrayList<>();
            // 消息生成器生成消息
            WebhookMessage message = messageGenerator.generate(webhook, data);
            // 根据webhook 获取 Notifier
            List<Notifier<Object, Object>> notifies = SpringEnvHelper.getBean(NotifierFactory.class).getNotifies(webhook);
            // Notifier 发起通知
            notifies.forEach(notifier -> resp.add(notifier.notify(webhook, notifier.process(message))));

            return resp.size() == 1 ? resp.get(0) : resp;
        }
        return null;
    }
}
