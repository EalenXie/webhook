package io.github.webhook.core;

import io.github.webhook.config.meta.WebhookType;

import java.util.EnumMap;

/**
 * @author EalenXie created on 2023/4/14 12:36
 * Webhook 处理器工厂
 */
public class WebhookHandlerFactory {

    private final EnumMap<WebhookType, WebhookHandler<?>> webhookHandlers = new EnumMap<>(WebhookType.class);

    public void registerHandler(WebhookType webhookType, WebhookHandler<?> webhookHandler) {
        webhookHandlers.put(webhookType, webhookHandler);
    }

    @SuppressWarnings("unchecked")
    public <H> WebhookHandler<H> getWebhookHandler(WebhookType webhookType) {
        return (WebhookHandler<H>) webhookHandlers.get(webhookType);
    }


}
