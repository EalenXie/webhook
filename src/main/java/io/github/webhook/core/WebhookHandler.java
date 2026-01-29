package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.meta.Webhook;

/**
 * webhook处理器
 *
 * @author EalenXie created on 2023/4/14 12:23
 */
public interface WebhookHandler<R> {


    /**
     * 处理webhook
     *
     * @param params 输入信息
     * @return 输出信息
     */
    R handleWebhook(Webhook webhook, JsonNode params);

}
