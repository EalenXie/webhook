package io.github.webhook.core;

import io.github.webhook.config.WebhookConfig;
import io.github.webhook.config.meta.Webhook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置型 WebhookRepository
 *
 * @author EalenXie created on 2023/4/14 11:24
 */
public class PropertiesWebhookRepository implements WebhookRepository {
    private final WebhookConfig properties;
    private final Map<String, Webhook> webhooks = new HashMap<>();

    public PropertiesWebhookRepository(WebhookConfig properties) {
        this.properties = properties;
        List<Webhook> webhookList = properties.getWebhooks();
        for (Webhook webhook : webhookList) {
            webhooks.put(webhook.getId(), webhook);
        }
    }

    @Override
    public List<Webhook> getWebhooks() {
        return properties.getWebhooks();
    }

    @Override
    public Webhook findById(String id) {
        return webhooks.get(id);
    }

    @Override
    public void remove(String id) {
        webhooks.remove(id);
    }
}
