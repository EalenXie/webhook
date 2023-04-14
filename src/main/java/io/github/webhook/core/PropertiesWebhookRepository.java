package io.github.webhook.core;

import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WebhookProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 11:24
 */
public class PropertiesWebhookRepository implements WebhookRepository {
    private final WebhookProperties properties;
    private final Map<String, Webhook> webhooks = new HashMap<>();

    public PropertiesWebhookRepository(WebhookProperties properties) {
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
