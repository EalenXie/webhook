package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.config.meta.Webhook;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂事件类型的 处理器
 *
 * @author EalenXie created on 2023/7/27 11:31
 */
@Slf4j
public abstract class FactoryEventHandler implements WebhookHandler<Object> {
    private final DefaultEventHandlerFactory eventHandlerFactory;
    private final ObjectMapper objectMapper;

    public DefaultEventHandlerFactory getEventHandlerFactory() {
        return eventHandlerFactory;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    protected FactoryEventHandler(DefaultEventHandlerFactory eventHandlerFactory, ObjectMapper objectMapper) {
        this.eventHandlerFactory = eventHandlerFactory;
        this.objectMapper = objectMapper;
    }

    /**
     * 事件处理器执行
     *
     * @param event   事件
     * @param webhook webhook
     * @param params  请求信息
     */
    public Object handlersExecute(String event, Webhook webhook, JsonNode params) {
        List<Object> resp = new ArrayList<>();
        // 事件处理器(针对该事件的事件处理器)执行
        eventHandlerFactory.getEventHandlers(event, webhook).forEach(handler -> {
            Object response = handlerExecute(webhook, params, handler);
            if (response != null) {
                resp.add(response);
            }
        });
        // 公共事件处理器执行
        eventHandlerFactory.getCommonHandlers().forEach(handler -> {
            Object response = handlerExecute(webhook, params, handler);
            // 获取事件处理结果
            if (response != null) {
                resp.add(response);
            }
        });
        return resp.size() == 1 ? resp.get(0) : resp;
    }

    /**
     * 执行事件处理
     *
     * @param webhook webhook
     * @param params  请求信息
     * @param handler 事件处理器
     * @return 事件处理结果
     */
    protected Object handlerExecute(Webhook webhook, JsonNode params, EventHandler<Object, Object> handler) {
        try {
            // 获取事件输入对象
            Object value;
            if (JsonNode.class == handler.getDataType()) {
                value = params;
            } else {
                value = objectMapper.convertValue(params, handler.getDataType());
            }
            // 处理事件
            return handler.handleEvent(webhook, value);
        } catch (Exception e) {
            log.error("Webhook[{}]事件[{}],处理失败:", webhook.getId(), handler.getClass(), e);
        }
        return null;
    }

}
