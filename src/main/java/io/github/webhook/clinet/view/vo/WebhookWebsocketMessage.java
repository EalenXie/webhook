package io.github.webhook.clinet.view.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.webhook.core.WebhookMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WebhookWebsocketMessage {
    /**
     * Websocket 消息类型  Gitlab / Github
     */
    private String type;
    /**
     * 消息标题 Push / Pipeline / MergeRequest
     */
    private String title;
    /**
     * 消息的核心内容 （markdown格式）
     */
    private String message;
    /**
     * 通知人（多个）
     */
    private List<String> notifies;
    /**
     * websocket消息生成时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeStamp;


    public WebhookWebsocketMessage(String type, WebhookMessage webhookMessage) {
        this.type = type;
        this.title = webhookMessage.getTitle();
        this.message = webhookMessage.getMessage();
        this.notifies = webhookMessage.getNotifies();
        this.timeStamp = new Date();
    }
}
