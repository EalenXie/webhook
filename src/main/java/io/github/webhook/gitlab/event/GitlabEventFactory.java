package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.meta.NotifyConfig;
import io.github.webhook.meta.Webhook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 12:57
 */
public class GitlabEventFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final Map<String, List<EventHandler<Object>>> handlers = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取Gitlab事件处理器
     *
     * @param gitlabEvent Gitlab webhook 事件
     * @return 事件处理器
     */
    @SuppressWarnings("unchecked")
    public List<EventHandler<Object>> getEventHandlers(String gitlabEvent, Webhook webhook) {
        List<EventHandler<Object>> eventHandlers = handlers.get(gitlabEvent);
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            String hookName = gitlabEvent.replace(" ", "");
            NotifyConfig notify = webhook.getNotify();
            if (notify != null) {
                String beanName = String.format("%sNotifyEventHandler", hookName);
                eventHandlers.add((EventHandler<Object>) applicationContext.getBean(beanName));
                try {
                    handlers.put(gitlabEvent, eventHandlers);
                } catch (BeansException e) {
                    throw new UnsupportedOperationException(String.format("Can not get EventHandler[%s]", beanName));
                }
            }
        }
        return eventHandlers;
    }
}
