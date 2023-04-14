package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/4/14 11:21
 */
@Getter
@Setter
public class Webhook {
    /**
     * webhook ID
     */
    private String id;
    /**
     * webhook 类型
     */
    private WebhookType type;

    /**
     * webhook 处理器类型
     */
    private HandlerType handlerType;


}
