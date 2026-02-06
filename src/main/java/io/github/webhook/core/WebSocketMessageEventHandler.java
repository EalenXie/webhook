package io.github.webhook.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.clinet.view.vo.WebhookWebsocketMessage;
import io.github.webhook.config.SpringEnvHelper;
import io.github.webhook.config.meta.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Websocket消息 事件处理器
 */
@Slf4j
public class WebSocketMessageEventHandler implements EventHandler<JsonNode, Object> {

    private final ObjectMapper objectMapper;

    private final SimpMessagingTemplate messagingTemplate;

    private final WebsocketMessageInMemoryRepository websocketMessageInMemoryRepository;


    public WebSocketMessageEventHandler(ObjectMapper objectMapper, SimpMessagingTemplate messagingTemplate, WebsocketMessageInMemoryRepository websocketMessageInMemoryRepository) {
        this.objectMapper = objectMapper;
        this.messagingTemplate = messagingTemplate;
        this.websocketMessageInMemoryRepository = websocketMessageInMemoryRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object handleEvent(Webhook webhook, String event, JsonNode params) {
        try {
            // 1. 按照事件根据规则 查找消息生成器
            String messageGeneratorName = getMessageGeneratorName(event);
            MessageGenerator<Object> messageGenerator = (MessageGenerator<Object>) SpringEnvHelper.getBean(messageGeneratorName);
            Object data = objectMapper.convertValue(params, ResolvableType.forClass(messageGenerator.getClass()).as(MessageGenerator.class).getGeneric(0).resolve());
            log.info("[{}]Webhook[{}]接收事件[{}]请求信息:{}", webhook.getType(), webhook.getId(), event, objectMapper.writeValueAsString(data));
            // 2. 消息生成器 生成消息
            WebhookWebsocketMessage message = new WebhookWebsocketMessage(webhook.getType().name(), messageGenerator.generate(webhook, data));
            // 3. 保存消息(内存级)
            websocketMessageInMemoryRepository.save(message);
            // 3. 发送消息到 Websocket消息客户端
            messagingTemplate.convertAndSend("/topic/messages", message);
        } catch (Exception e) {
            log.error("WebSocketMessageEventHandler handle webhook[{}] event[{}] exception:", webhook.getId(), event, e);
        }
        return null;
    }

    /**
     * 默认方法 根据事件类型和名称获取到通知类型的BeanName -> (event) (eventType) EventHandler
     *
     * @param event 事件名称
     * @return 获取到BeanName
     */
    protected String getMessageGeneratorName(String event) {
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
        return String.format("%sMessageGenerator", sb);
    }

}
