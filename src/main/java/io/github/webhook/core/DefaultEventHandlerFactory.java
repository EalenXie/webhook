package io.github.webhook.core;

import io.github.webhook.config.SpringEnvHelper;
import io.github.webhook.config.meta.Webhook;
import org.springframework.beans.BeansException;

import java.util.*;

/**
 * 默认的事件处理器工厂
 *
 * @author EalenXie created on 2023/7/27 11:32
 */
public class DefaultEventHandlerFactory {
    /**
     * 特殊事件处理器 对应事件KEY
     */
    private final Map<String, List<EventHandler<Object, Object>>> handlers = new HashMap<>();
    /**
     * 公共事件处理器
     */
    private final Set<EventHandler<Object, Object>> commonHandlers = new HashSet<>();

    /**
     * 获取公共事件处理器
     */
    public Set<EventHandler<Object, Object>> getCommonHandlers() {
        return commonHandlers;
    }

    /**
     * 根据事件获取事件处理器
     *
     * @param event   事件
     * @param webhook webhook
     * @return 一个事件有多个事件处理器
     */
    @SuppressWarnings("unchecked")
    public List<EventHandler<Object, Object>> getEventHandlers(String event, Webhook webhook) {
        List<EventHandler<Object, Object>> eventHandlers = handlers.get(event);
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            // 查找通知类 事件处理器
            if (webhook.getNotify() != null) {
                // 根据事件名称获取到通知类型的BeanName -> xxxNotifyEventHandler
                String beanName = getHandlerBeanName(event, "Notify");
                try {
                    eventHandlers.add((EventHandler<Object, Object>) SpringEnvHelper.getBean(beanName));
                    handlers.put(event, eventHandlers);
                } catch (BeansException e) {
                    throw new UnsupportedOperationException(String.format("Can not get EventHandler[%s]", beanName));
                }
            }
        }
        return eventHandlers;
    }

    /**
     * 默认方法 根据事件类型和名称获取到通知类型的BeanName -> (event) (eventType) EventHandler
     *
     * @param event     事件名称
     * @param eventType 事件类型
     * @return 获取到BeanName
     */
    protected String getHandlerBeanName(String event, String eventType) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < event.length(); i++) {
            char currentChar = event.charAt(i);
            if (currentChar == '_' || currentChar == ' ') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(currentChar));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return String.format("%s%sEventHandler", sb, eventType);
    }

}
