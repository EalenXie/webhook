package io.github.webhook.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.config.meta.WebhookType;
import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.core.WebSocketMessageEventHandler;
import io.github.webhook.core.WebhookHandlerFactory;
import io.github.webhook.core.WebsocketMessageInMemoryRepository;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.github.event.message.ForkMessageGenerator;
import io.github.webhook.github.event.message.PullRequestMessageGenerator;
import io.github.webhook.github.event.message.PushMessageGenerator;
import io.github.webhook.github.event.message.StarMessageGenerator;
import io.github.webhook.github.event.notify.ForkNotifyEventHandler;
import io.github.webhook.github.event.notify.PullRequestNotifyEventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.gitlab.message.*;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.client.RestOperations;

import static io.github.webhook.config.SpringEnvHelper.*;

@Configuration
public class HandlersBeanConfig {

    /**
     * Webhook事件及其组件注册器
     */
    @Bean
    @DependsOn("springEnvHelper")
    public HandlersBeanRegister handlersBeanRegister(RestOperations httpClientRestTemplate) {
        return new HandlersBeanRegister(httpClientRestTemplate);
    }

    /**
     * Webhook事件及其组件Bean注册器
     */
    public static class HandlersBeanRegister {

        public HandlersBeanRegister(RestOperations httpClientRestTemplate) {
            // Github事件处理器 （通知型事件处理器，目前支持Push，Star，PullRequest，Fork）
            registerSingleton(new PushNotifyEventHandler(getOrRegisterBean(PushMessageGenerator.class)));
            registerSingleton(new StarNotifyEventHandler(getOrRegisterBean(StarMessageGenerator.class)));
            registerSingleton(new PullRequestNotifyEventHandler(getOrRegisterBean(PullRequestMessageGenerator.class)));
            registerSingleton(new ForkNotifyEventHandler(getOrRegisterBean(ForkMessageGenerator.class)));
            // Gitlab事件处理器注册（通知型事件处理器，目前支持Push，Issue，MergeRequest，）
            registerSingleton(new PushHookNotifyEventHandler(getOrRegisterBean(PushHookMessageGenerator.class)));
            registerSingleton(new IssueHookNotifyEventHandler(getOrRegisterBean(IssueHookMessageGenerator.class)));
            registerSingleton(new MergeRequestHookNotifyEventHandler(getOrRegisterBean(MergeRequestHookMessageGenerator.class)));
            registerSingleton(new JobHookNotifyEventHandler(getOrRegisterBean(JobHookMessageGenerator.class)));
            registerSingleton(new NoteHookNotifyEventHandler(getOrRegisterBean(NoteHookMessageGenerator.class)));
            registerSingleton(new ReleaseHookNotifyEventHandler(getOrRegisterBean(ReleaseHookMessageGenerator.class)));
            registerSingleton(new TagPushHookNotifyEventHandler(getOrRegisterBean(TagPushHookMessageGenerator.class)));
            registerSingleton(new PipelineHookNotifyEventHandler(getOrRegisterBean(new PipelineHookMessageGenerator(getBean(WebhookConfig.class)))));
            // 通知器注册（目前支持 钉钉，飞书，企业微信）
            registerSingleton(new DingTalkNotifier(httpClientRestTemplate));
            registerSingleton(new CorpWechatNotifier(httpClientRestTemplate));
            registerSingleton(new FeiShuNotifier(httpClientRestTemplate));
        }
    }


    @Bean
    public WebsocketMessageInMemoryRepository websocketMessageInMemoryRepository() {
        return new WebsocketMessageInMemoryRepository();
    }

    /**
     * 公共事件处理器 Bean注册 （WebSocketMessage）
     */
    @Bean
    public WebSocketMessageEventHandler webSocketMessageEventHandler(ObjectMapper objectMapper, SimpMessagingTemplate messagingTemplate, WebsocketMessageInMemoryRepository websocketMessageInMemoryRepository) {
        return new WebSocketMessageEventHandler(objectMapper, messagingTemplate, websocketMessageInMemoryRepository);
    }

    /**
     * 默认的事件处理器工厂
     */
    @Bean
    @DependsOn("springEnvHelper")
    public DefaultEventHandlerFactory defaultEventHandlerFactory() {
        DefaultEventHandlerFactory factory = new DefaultEventHandlerFactory();
        // 添加一个公共事件处理器
        factory.addCommonHandlers(SpringEnvHelper.getBean(WebSocketMessageEventHandler.class));
        return factory;
    }

    /**
     * Github Webhook 处理器
     */
    @Bean
    public GithubWebhookHandler githubWebhookHandler(DefaultEventHandlerFactory defaultEventHandlerFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(defaultEventHandlerFactory, objectMapper);
    }

    /**
     * Gitlab Webhook 处理器
     */
    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(DefaultEventHandlerFactory defaultEventHandlerFactory, ObjectMapper objectMapper) {
        return new GitlabWebhookHandler(defaultEventHandlerFactory, objectMapper);
    }


    /**
     * Webhook 处理器 工厂
     */
    @Bean
    @DependsOn("springEnvHelper")
    public WebhookHandlerFactory webhookHandlerFactory() {
        WebhookHandlerFactory factory = new WebhookHandlerFactory();
        factory.addHandler(WebhookType.GITHUB, SpringEnvHelper.getBean(GithubWebhookHandler.class));
        factory.addHandler(WebhookType.GITLAB, SpringEnvHelper.getBean(GitlabWebhookHandler.class));
        return factory;
    }

}
