package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.meta.Webhook;

/**
 * @author EalenXie created on 2023/4/14 12:23
 */
public interface WebhookHandler<R> {


    /**
     * 处理webhook请求信息
     *
     * @param params 请求参数
     * @return 返回对象
     */
    R handleWebhook(Webhook webhook, JsonNode params);

}
