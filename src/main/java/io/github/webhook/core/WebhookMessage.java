package io.github.webhook.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 14:25
 */
@Getter
@Setter
public class WebhookMessage {
    /**
     * 消息Title
     */
    private String title;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 通知人
     */
    private List<String> notifies;

}
