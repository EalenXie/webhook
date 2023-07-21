package io.github.webhook.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.EventHandler;
import io.github.webhook.core.WebhookHandler;
import io.github.webhook.github.event.GithubEventFactory;
import io.github.webhook.meta.Webhook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/7/19 16:05
 */
public class GithubWebhookHandler implements WebhookHandler<Object> {


    private final GithubEventFactory githubEventFactory;
    private final ObjectMapper objectMapper;

    public GithubWebhookHandler(GithubEventFactory githubEventFactory, ObjectMapper objectMapper) {
        this.githubEventFactory = githubEventFactory;
        this.objectMapper = objectMapper;
    }

    @Override
    public Object handleWebhook(Webhook webhook, JsonNode params) {
        // 1. 获取请求事件
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String event = request.getHeader("X-GitHub-Event");
        if (ObjectUtils.isEmpty(event)) {
            throw new UnsupportedOperationException("Unable to get the GitHub event type, please check that your webhook configuration is correct");
        }
        // 针对某一事件可能有多个事件处理器
        List<EventHandler<Object, Object>> handlers = githubEventFactory.getEventHandlers(event, webhook);
        List<Object> resp = new ArrayList<>();
        for (EventHandler<Object, Object> handler : handlers) {
            // 处理事件
            resp.add(handler.handleEvent(webhook, objectMapper.convertValue(params, handler.getDataType())));
        }
        return resp.size() == 1 ? resp.get(0) : resp;
    }
}
