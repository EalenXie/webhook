package io.github.webhook.core;

import io.github.webhook.meta.Webhook;

/**
 * 事件处理器 针对某类事件的事件处理器
 *
 * @param <D> 事件输入
 * @param <R> 事件输出
 * @author EalenXie created on 2023/4/14 12:47
 */
public interface EventHandler<D, R> {

    /**
     * 获取事件输入数据类型
     */
    Class<D> getDataType();

    /**
     * 是否执行 处理事情请求
     *
     * @param webhook webhook
     * @param data    事件输入数据
     * @return 是否执行事件处理
     */
    boolean shouldHandleEvent(Webhook webhook, D data);

    /**
     * 处理请求事件
     *
     * @param webhook webhook
     * @param data    事件输入数据
     * @return 事件输出
     */
    R handleEvent(Webhook webhook, D data);


}
