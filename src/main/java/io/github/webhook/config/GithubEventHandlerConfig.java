package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.github.event.GithubEventFactory;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author EalenXie created on 2023/4/17 13:24
 * Github 事件配置
 */
@Configuration
public class GithubEventHandlerConfig {
    @Bean
    public GithubEventFactory githubEventFactory() {
        return new GithubEventFactory();
    }


    @Bean
    public GithubWebhookHandler githubWebhookHandler(GithubEventFactory githubEventFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(githubEventFactory, objectMapper);
    }

    @Bean
    public PushNotifyEventHandler pushNotifyEventHandler(NotifierFactory notifierFactory) {
        return new PushNotifyEventHandler(notifierFactory);
    }

    @Bean
    public StarNotifyEventHandler starNotifyEventHandler(NotifierFactory notifierFactory) {
        return new StarNotifyEventHandler(notifierFactory);
    }

}
