package io.github.webhook.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.EventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class WebSocketMessageEventHandler implements EventHandler<JsonNode, Object> {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketMessageEventHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public Object handleEvent(Webhook webhook, String event, JsonNode params) {
        // 1. 按照事件根据规则 查找消息生成器

        // 2. 消息生成器 生成消息

        // 3. 发送消息到 Websocket消息客户端


        return null;
    }

    /**
     * 默认方法 根据事件类型和名称获取到通知类型的BeanName -> (event) (eventType) EventHandler
     *
     * @param event 事件名称
     * @return 获取到BeanName
     */
    protected String getHandlerBeanName(String event) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < event.length(); i++) {
            char currentChar = event.charAt(i);
            if (currentChar == '_' || currentChar == ' ') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(currentChar));
                    nextUpperCase = false;
                } else {
                    sb.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return String.format("%s%sEventHandler", sb);
    }

}
