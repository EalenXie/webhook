package io.github.webhook.github.event;

import io.github.webhook.core.EventHandlerFactory;
import io.github.webhook.core.EventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 12:57
 * Github 事件处理器工厂
 */
public class GithubEventFactory implements EventHandlerFactory {
    private final ApplicationContext applicationContext;
    private final NotifierFactory notifierFactory;
    private final Map<String, List<EventHandler<Object, Object>>> handlers = new HashMap<>();

    public GithubEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        this.applicationContext = applicationContext;
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Github所有事件
     */
    @PostConstruct
    public void registerEvents() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // Github 通知类 事件处理器 xxxNotifyEventHandler
        beanFactory.registerSingleton("pushNotifyEventHandler", new PushNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("starNotifyEventHandler", new StarNotifyEventHandler(notifierFactory));
    }

    /**
     * 获取Github事件处理器
     *
     * @param event Github webhook 事件
     * @return 事件处理器
     */
    @Override
    @SuppressWarnings("all")
    public List<EventHandler<Object, Object>> getEventHandlers(String event, Webhook webhook) {
        List<EventHandler<Object, Object>> eventHandlers = handlers.get(event);
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            // 查找通知类 事件处理器 xxxNotifyEventHandler
            if (webhook.getNotify() != null) {
                String beanName = String.format("%sNotifyEventHandler", event.replace(" ", ""));
                try {
                    eventHandlers.add((EventHandler<Object, Object>) applicationContext.getBean(capitalize(beanName)));
                    handlers.put(event, eventHandlers);
                } catch (BeansException e) {
                    throw new UnsupportedOperationException(String.format("Can not get EventHandler[%s]", beanName));
                }
            }
        }
        return eventHandlers;
    }

    /**
     * 将字符串的首字母转换为小写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalize(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }
}
