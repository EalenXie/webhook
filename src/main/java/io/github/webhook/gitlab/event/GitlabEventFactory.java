package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandlerFactory;
import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.event.notify.*;
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
 * Gitlab 事件工厂
 */
public class GitlabEventFactory implements EventHandlerFactory {
    private final ApplicationContext applicationContext;
    private final NotifierFactory notifierFactory;

    private final Map<String, List<EventHandler<Object, Object>>> handlers = new HashMap<>();

    public GitlabEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        this.applicationContext = applicationContext;
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Gitlab所有事件
     */
    @PostConstruct
    public void registerEvents() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // Gitlab 通知类 事件处理器 xxxHookNotifyEventHandler
        beanFactory.registerSingleton("pushHookNotifyEventHandler", new PushHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("issueHookNotifyEventHandler", new IssueHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("mergeRequestHookNotifyEventHandler", new MergeRequestHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("jobHookNotifyEventHandler", new JobHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("noteHookNotifyEventHandler", new NoteHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("releaseHookNotifyEventHandler", new ReleaseHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("tagPushHookNotifyEventHandler", new TagPushHookNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("pipelineHookNotifyEventHandler", new PipelineHookNotifyEventHandler(notifierFactory));
    }

    /**
     * 获取Gitlab事件处理器
     *
     * @param event Gitlab webhook 事件
     * @return 事件处理器
     */
    @Override
    @SuppressWarnings("all")
    public List<EventHandler<Object, Object>> getEventHandlers(String event, Webhook webhook) {
        List<EventHandler<Object, Object>> eventHandlers = handlers.get(event);
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
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
