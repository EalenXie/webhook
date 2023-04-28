package io.github.webhook.zentao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.WebhookHandler;
import io.github.webhook.meta.Webhook;
import io.github.webhook.zentao.webhook.ZentaoWebhookDTO;

/**
 * @author EalenXie created on 2023/4/14 13:42
 */
public class ZentaoWebhookHandler implements WebhookHandler<Void> {

    private final ObjectMapper objectMapper;

    public ZentaoWebhookHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Void handleWebhook(Webhook webhook, JsonNode params) {
        // 处理事件
        ZentaoWebhookDTO dto = objectMapper.convertValue(params, ZentaoWebhookDTO.class);


        return null;
    }
}
