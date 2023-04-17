package io.github.webhook.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.EventHandler;
import io.github.webhook.core.WebhookHandler;
import io.github.webhook.gitlab.event.GitlabEventFactory;
import io.github.webhook.meta.Webhook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        // 针对某一事件可能有多个事件处理器
        List<EventHandler<Object>> handlers = gitlabEventFactory.getEventHandlers(event, webhook);
        for (EventHandler<Object> handler : handlers) {
            // 处理事件
            handler.handleEvent(webhook, objectMapper.convertValue(params, handler.getDataType()));
        }
        return null;
    }


}
