package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.GitlabWebhookRegister;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.gitlab.message.*;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.meta.WebhookProperties;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * Gitlab 配置
 *
 * @author EalenXie created on 2023/4/17 13:24
 */
@Configuration
public class GitlabConfig {


    /**
     * Gitlab Webhook 处理器
     *
     * @param gitlabEventHandlerFactory Gitlab事件工厂
     */
    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(ApplicationContext applicationContext, NotifierFactory notifierFactory, ObjectMapper objectMapper) {
        // 事件处理器工厂
        DefaultEventHandlerFactory factory = new DefaultEventHandlerFactory(applicationContext);
        // Gitlab 通知类 事件处理器 xxxNotifyEventHandler
        factory.regEventHandler(new PushHookNotifyEventHandler(notifierFactory, new PushHookMessageGenerator()));
        factory.regEventHandler(new IssueHookNotifyEventHandler(notifierFactory, new IssueHookMessageGenerator()));
        factory.regEventHandler(new MergeRequestHookNotifyEventHandler(notifierFactory, new MergeRequestHookMessageGenerator()));
        factory.regEventHandler(new JobHookNotifyEventHandler(notifierFactory, new JobHookMessageGenerator()));
        factory.regEventHandler(new NoteHookNotifyEventHandler(notifierFactory, new NoteHookMessageGenerator()));
        factory.regEventHandler(new ReleaseHookNotifyEventHandler(notifierFactory, new ReleaseHookMessageGenerator()));
        factory.regEventHandler(new TagPushHookNotifyEventHandler(notifierFactory, new TagPushHookMessageGenerator()));
        factory.regEventHandler(new PipelineHookNotifyEventHandler(notifierFactory, new PipelineHookMessageGenerator(applicationContext.getBean(WebhookProperties.class))));
        return new GitlabWebhookHandler(factory, objectMapper);
    }

    @Bean
    public GitlabRestClientFactory gitlabRestClientFactory(ObjectMapper objectMapper, RestOperations httpClientRestTemplate) {
        return new GitlabRestClientFactory(objectMapper, httpClientRestTemplate);
    }

    @Bean
    public GitlabWebhookRegister gitlabWebhookRegister(WebhookProperties webhookProperties, GitlabRestClientFactory gitlabRestClientFactory) {
        return new GitlabWebhookRegister(webhookProperties, gitlabRestClientFactory);
    }

}
