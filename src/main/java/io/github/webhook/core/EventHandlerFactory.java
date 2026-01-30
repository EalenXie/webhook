package io.github.webhook.core;

import io.github.webhook.meta.Webhook;

import java.util.List;

/**
 * 事件处理器工厂
 *
 * @author EalenXie created on 2023/7/27 11:32
 */
public interface EventHandlerFactory {


    /**
     * 根据事件获取事件处理器
     *
     * @param event   事件
     * @param webhook webhook
     * @return 一个事件有多个事件处理器
     */
    List<EventHandler<Object, Object>> getEventHandlers(String event, Webhook webhook);
}
