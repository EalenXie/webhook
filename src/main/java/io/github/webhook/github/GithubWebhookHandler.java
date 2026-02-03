package io.github.webhook.github;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.core.FactoryEventHandler;
import io.github.webhook.config.meta.Webhook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EalenXie created on 2023/7/19 16:05
 */
public class GithubWebhookHandler extends FactoryEventHandler {

    public GithubWebhookHandler(DefaultEventHandlerFactory handlerFactory, ObjectMapper objectMapper) {
        super(handlerFactory, objectMapper);
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
        return handlerExecute(event, webhook, params);
    }
}
