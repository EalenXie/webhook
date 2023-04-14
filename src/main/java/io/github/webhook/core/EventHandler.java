package io.github.webhook.core;

import io.github.webhook.meta.Webhook;

/**
 * @author EalenXie created on 2023/4/14 12:47
 * 事件处理器
 */
public interface EventHandler<D> {


    Class<D> getDataType();


    /**
     * 处理请求事件
     *
     * @param data 事件数据
     */
    void handleEvent(Webhook webhook, D data);


}
