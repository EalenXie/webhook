package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.meta.Webhook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/7/27 11:31
 * 工厂事件类型的 处理器
 */
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
            // 处理事件
            resp.add(handler.handleEvent(webhook, objectMapper.convertValue(params, handler.getDataType())));
        }
        return resp.size() == 1 ? resp.get(0) : resp;

    }

}
