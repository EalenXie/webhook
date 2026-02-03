package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.github.event.message.ForkMessageGenerator;
import io.github.webhook.github.event.message.PullRequestMessageGenerator;
import io.github.webhook.github.event.message.PushMessageGenerator;
import io.github.webhook.github.event.message.StarMessageGenerator;
import io.github.webhook.github.event.notify.ForkNotifyEventHandler;
import io.github.webhook.github.event.notify.PullRequestNotifyEventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {


    @Bean
    public DefaultEventHandlerFactory githubEventHandlerFactory(ApplicationContext applicationContext, NotifierFactory notifierFactory) {
        DefaultEventHandlerFactory factory = new DefaultEventHandlerFactory(applicationContext);
        // Github 通知类 事件处理器 xxxNotifyEventHandler
        factory.regEventHandler(new PushNotifyEventHandler(notifierFactory, new PushMessageGenerator()));
        factory.regEventHandler(new StarNotifyEventHandler(notifierFactory, new StarMessageGenerator()));
        factory.regEventHandler(new PullRequestNotifyEventHandler(notifierFactory, new PullRequestMessageGenerator()));
        factory.regEventHandler(new ForkNotifyEventHandler(notifierFactory, new ForkMessageGenerator()));
        return factory;
    }

    /**
     * Github Webhook 处理器
     */
    @Bean
    public GithubWebhookHandler githubWebhookHandler(DefaultEventHandlerFactory githubEventHandlerFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(githubEventHandlerFactory, objectMapper);
    }


}
