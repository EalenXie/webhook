package io.github.webhook.core;

import io.github.webhook.config.meta.Webhook;
import org.springframework.core.ResolvableType;

/**
 * 事件处理器 针对某类事件的事件处理器
 *
 * @param <D> 事件输入
 * @param <R> 事件输出
 * @author EalenXie created on 2023/4/14 12:47
 */
public interface EventHandler<D, R> {


    /**
     * 处理请求事件
     *
     * @param webhook webhook
     * @param data    事件输入数据
     * @return 事件输出
     */
    R handleEvent(Webhook webhook, D data);

    /**
     * 获取数据类型
     */
    default Class<?> getDataType() {
        return ResolvableType.forClass(this.getClass()).as(EventHandler.class).getGeneric(0).resolve();
    }
}
