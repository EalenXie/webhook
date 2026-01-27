package io.github.webhook.core;

import io.github.webhook.meta.Webhook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/7/27 11:32
 * 默认的事件处理器工厂
 */
public abstract class DefaultEventHandlerFactory implements EventHandlerFactory {
    private final ApplicationContext applicationContext;
    private final Map<String, List<EventHandler<Object, Object>>> handlers = new HashMap<>();

    protected DefaultEventHandlerFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 所有的事件处理器工厂需注册事件
     */
    @PostConstruct
    public abstract void registerEvents();

    /**
     * 根据事件获取事件处理器
     *
     * @param event   事件
     * @param webhook webhook
     * @return 一个事件有多个事件处理器
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<EventHandler<Object, Object>> getEventHandlers(String event, Webhook webhook) {
        List<EventHandler<Object, Object>> eventHandlers = handlers.get(event);
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            // 查找通知类 事件处理器
            if (webhook.getNotify() != null) {
                String beanName = getNotifyHandlerBeanName(event);
                try {
                    eventHandlers.add((EventHandler<Object, Object>) applicationContext.getBean(beanName));
                    handlers.put(event, eventHandlers);
                } catch (BeansException e) {
                    throw new UnsupportedOperationException(String.format("Can not get EventHandler[%s]", beanName));
                }
            }
        }
        return eventHandlers;
    }

    /**
     * 默认方法 根据事件名称获取到通知类型的BeanName -> xxxNotifyEventHandler
     *
     * @param event 事件名称
     * @return 获取到BeanName
     */
    protected String getNotifyHandlerBeanName(String event) {
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
        return String.format("%sNotifyEventHandler", sb);
    }
}
