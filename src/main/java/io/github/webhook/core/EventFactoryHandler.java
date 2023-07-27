package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.meta.Webhook;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/7/27 11:31
 */
public abstract class EventFactoryHandler implements WebhookHandler<Object> {
    private final EventFactory eventFactory;
    private final ObjectMapper objectMapper;

    public EventFactory getEventFactory() {
        return eventFactory;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected EventFactoryHandler(EventFactory eventFactory, ObjectMapper objectMapper) {
        this.eventFactory = eventFactory;
        this.objectMapper = objectMapper;
    }


    public Object handlerExecute(String event, Webhook webhook, JsonNode params) {
        List<EventHandler<Object, Object>> handlers = eventFactory.getEventHandlers(event, webhook);
        List<Object> resp = new ArrayList<>();
        for (EventHandler<Object, Object> handler : handlers) {
            // 处理事件
            resp.add(handler.handleEvent(webhook, objectMapper.convertValue(params, handler.getDataType())));
        }
        return resp.size() == 1 ? resp.get(0) : resp;

    }

}
