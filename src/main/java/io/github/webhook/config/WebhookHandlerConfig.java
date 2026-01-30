package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.github.event.GithubEventHandlerFactory;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.event.GitlabEventHandlerFactory;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author EalenXie created on 2023/7/27 14:26
 * Webhook 处理器配置
 */
@Configuration
public class WebhookHandlerConfig {

    /**
     * Gitlab 事件处理器工厂
     */
    @Bean
    public GitlabEventHandlerFactory gitlabEventHandlerFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        return new GitlabEventHandlerFactory(applicationContext, notifierFactory);
    }

    /**
     * Gitlab Webhook 处理器
     *
     * @param gitlabEventHandlerFactory Gitlab事件工厂
     */
    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(GitlabEventHandlerFactory gitlabEventHandlerFactory, ObjectMapper objectMapper) {
        return new GitlabWebhookHandler(gitlabEventHandlerFactory, objectMapper);
    }

    /**
     * Github 事件处理器工厂
     */
    @Bean
    public GithubEventHandlerFactory githubEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        return new GithubEventHandlerFactory(applicationContext, notifierFactory);
    }

    /**
     * Github Webhook 处理器
     *
     * @param githubEventHandlerFactory Github事件工厂
     */
    @Bean
    public GithubWebhookHandler githubWebhookHandler(GithubEventHandlerFactory githubEventHandlerFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(githubEventHandlerFactory, objectMapper);
    }


}
