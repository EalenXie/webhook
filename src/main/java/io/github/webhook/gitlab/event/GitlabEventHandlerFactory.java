package io.github.webhook.gitlab.event;

import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.meta.WebhookProperties;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author EalenXie created on 2023/4/14 12:57
 * Gitlab 事件工厂
 */
public class GitlabEventHandlerFactory extends DefaultEventHandlerFactory {
    private final NotifierFactory notifierFactory;

    public GitlabEventHandlerFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        super(applicationContext);
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Gitlab所有事件
     */
    @PostConstruct
    public void registerEvents() {
        // Gitlab 通知类 事件处理器 xxxHookNotifyEventHandler
        regEventHandler(new PushHookNotifyEventHandler(notifierFactory));
        regEventHandler(new IssueHookNotifyEventHandler(notifierFactory));
        regEventHandler(new MergeRequestHookNotifyEventHandler(notifierFactory));
        regEventHandler(new JobHookNotifyEventHandler(notifierFactory));
        regEventHandler(new NoteHookNotifyEventHandler(notifierFactory));
        regEventHandler(new ReleaseHookNotifyEventHandler(notifierFactory));
        regEventHandler(new TagPushHookNotifyEventHandler(notifierFactory));
        regEventHandler(new PipelineHookNotifyEventHandler(getApplicationContext().getBean(WebhookProperties.class), notifierFactory));
    }


}
