package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.github.event.GithubEventFactory;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.event.GitlabEventFactory;
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
     * Gitlab 事件工厂
     */
    @Bean
    public GitlabEventFactory gitlabEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        return new GitlabEventFactory(applicationContext, notifierFactory);
    }

    /**
     * Gitlab Webhook 处理器
     *
     * @param gitlabEventFactory Gitlab事件工厂
     */
    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(GitlabEventFactory gitlabEventFactory, ObjectMapper objectMapper) {
        return new GitlabWebhookHandler(gitlabEventFactory, objectMapper);
    }

    /**
     * Github 事件工厂
     */
    @Bean
    public GithubEventFactory githubEventFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        return new GithubEventFactory(applicationContext, notifierFactory);
    }

    /**
     * Github Webhook 处理器
     *
     * @param githubEventFactory Github事件工厂
     */
    @Bean
    public GithubWebhookHandler githubWebhookHandler(GithubEventFactory githubEventFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(githubEventFactory, objectMapper);
    }


}
