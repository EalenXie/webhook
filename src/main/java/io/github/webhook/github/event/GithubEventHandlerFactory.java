package io.github.webhook.github.event;

import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.github.event.notify.ForkNotifyEventHandler;
import io.github.webhook.github.event.notify.PullRequestNotifyEventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.ApplicationContext;

/**
 * @author EalenXie created on 2023/4/14 12:57
 * Github 事件处理器工厂
 */
public class GithubEventHandlerFactory extends DefaultEventHandlerFactory {
    private final NotifierFactory notifierFactory;

    public GithubEventHandlerFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        super(applicationContext);
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Github所有事件处理器
     */
    @Override
    public void registerEvents() {
        // Github 通知类 事件处理器 xxxNotifyEventHandler
        regEventHandler(new PushNotifyEventHandler(notifierFactory));
        regEventHandler(new StarNotifyEventHandler(notifierFactory));
        regEventHandler(new PullRequestNotifyEventHandler(notifierFactory));
        regEventHandler(new ForkNotifyEventHandler(notifierFactory));
    }
}
