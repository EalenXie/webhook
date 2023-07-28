package io.github.webhook.gitlab.event;

import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author EalenXie created on 2023/4/14 12:57
 * Gitlab 事件工厂
 */
public class GitlabEventFactory extends DefaultEventHandlerFactory {
    private final NotifierFactory notifierFactory;

    public GitlabEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        super(applicationContext);
        this.notifierFactory = notifierFactory;
    }

    /**
     * 初始化注册Gitlab所有事件
     */
    @PostConstruct
    public void registerEvents() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
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


}
