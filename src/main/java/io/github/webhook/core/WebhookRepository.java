package io.github.webhook.core;

import io.github.webhook.config.meta.Webhook;

import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 11:24
 */
public interface WebhookRepository {


    /**
     * 获取所有的webhook
     */
    List<Webhook> getWebhooks();


    /**
     * 根据id 查询webhook
     */
    Webhook findById(String id);


    /**
     * 删除webhook
     */
    void remove(String id);


}
