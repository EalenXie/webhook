package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.meta.Webhook;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/7/27 11:31
 * 工厂事件类型的 处理器
 */
@Slf4j
public abstract class FactoryEventHandler implements WebhookHandler<Object> {
    private final EventHandlerFactory eventHandlerFactory;
    private final ObjectMapper objectMapper;

    public EventHandlerFactory getEventHandlerFactory() {
        return eventHandlerFactory;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected FactoryEventHandler(EventHandlerFactory eventHandlerFactory, ObjectMapper objectMapper) {
        this.eventHandlerFactory = eventHandlerFactory;
        this.objectMapper = objectMapper;
    }

    /**
     * 执行事件处理
     *
     * @param event   事件
     * @param webhook webhook
     * @param params  请求信息
     */
    public Object handlerExecute(String event, Webhook webhook, JsonNode params) {
        List<EventHandler<Object, Object>> handlers = eventHandlerFactory.getEventHandlers(event, webhook);
        List<Object> resp = new ArrayList<>();
        for (EventHandler<Object, Object> handler : handlers) {
            // 获取请求信息
            Object value = objectMapper.convertValue(params, handler.getDataType());
            // 是否处理事件(不满足条件的事件处理将被丢弃)
            if (handler.shouldHandleEvent(webhook, value)) {
                // 处理事件
                resp.add(handler.handleEvent(webhook, value));
            } else {
                log.warn("不满足事件条件，消息被丢弃:{}", value);
            }
        }
        return resp.size() == 1 ? resp.get(0) : resp;

    }

}
