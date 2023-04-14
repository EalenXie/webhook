package io.github.webhook.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.EventHandler;
import io.github.webhook.core.WebhookHandler;
import io.github.webhook.meta.Webhook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EalenXie created on 2023/4/14 12:39
 */
public class GitlabWebhookHandler implements WebhookHandler<Void> {
    private final GitlabEventFactory gitlabEventFactory;
    private final ObjectMapper objectMapper;

    public GitlabWebhookHandler(GitlabEventFactory gitlabEventFactory, ObjectMapper objectMapper) {
        this.gitlabEventFactory = gitlabEventFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public Void handleWebhook(Webhook webhook, JsonNode params) {
        // 1. 获取请求事件
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String event = request.getHeader("X-Gitlab-Event");
        // 获取事件处理器
        EventHandler<Object> eventHandler = gitlabEventFactory.getEventHandler(event, webhook.getHandlerType().name());
        // 处理事件
        eventHandler.handleEvent(webhook, objectMapper.convertValue(params, eventHandler.getDataType()));
        return null;
    }


}
