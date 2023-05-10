package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.meta.Webhook;

/**
 * @author EalenXie created on 2023/4/14 13:36
 */
public class WebhookExecutor {

    private final WebhookHandlerFactory webhookHandlerFactory;

    public WebhookExecutor(WebhookHandlerFactory webhookHandlerFactory) {
        this.webhookHandlerFactory = webhookHandlerFactory;
    }

    public void handleWebhook(Webhook webhook, JsonNode json) {
        WebhookHandler<?> webhookHandler = webhookHandlerFactory.getWebhookHandler(webhook.getType());
        if (webhookHandler == null) {
            throw new UnsupportedOperationException("unsupported webhook handler");
        }
        webhookHandler.handleWebhook(webhook, json);
    }
}