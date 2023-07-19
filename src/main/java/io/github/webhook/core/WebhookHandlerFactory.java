package io.github.webhook.core;

import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.meta.WebhookType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.EnumMap;

/**
 * @author EalenXie created on 2023/4/14 12:36
 */
public class WebhookHandlerFactory implements ApplicationContextAware {

    private final EnumMap<WebhookType, WebhookHandler<?>> webhookHandlers = new EnumMap<>(WebhookType.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        webhookHandlers.put(WebhookType.GITLAB, getWebhookHandler(GitlabWebhookHandler.class));
        webhookHandlers.put(WebhookType.GITHUB, getWebhookHandler(GithubWebhookHandler.class));
    }

    private WebhookHandler<?> getWebhookHandler(Class<?> clz) {
        return (WebhookHandler<?>) applicationContext.getBean(clz);
    }


    @SuppressWarnings("unchecked")
    public <H> WebhookHandler<H> getWebhookHandler(WebhookType webhookType) {
        return (WebhookHandler<H>) webhookHandlers.get(webhookType);
    }


}
