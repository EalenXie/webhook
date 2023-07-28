package io.github.webhook.github.event;

import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.github.event.notify.PullRequestNotifyEventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author EalenXie created on 2023/4/14 12:57
 * Github 事件处理器工厂
 */
public class GithubEventFactory extends DefaultEventHandlerFactory {
    private final NotifierFactory notifierFactory;

    public GithubEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        super(applicationContext);
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Github所有事件
     */
    @Override
    public void registerEvents() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
        // Github 通知类 事件处理器 xxxNotifyEventHandler
        beanFactory.registerSingleton("pushNotifyEventHandler", new PushNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("starNotifyEventHandler", new StarNotifyEventHandler(notifierFactory));
        beanFactory.registerSingleton("pullRequestNotifyEventHandler", new PullRequestNotifyEventHandler(notifierFactory));
    }
}
