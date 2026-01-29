package io.github.webhook.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.FactoryEventHandler;
import io.github.webhook.gitlab.event.GitlabEventFactory;
import io.github.webhook.meta.Webhook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EalenXie created on 2023/4/14 12:39
 */
public class GitlabWebhookHandler extends FactoryEventHandler {
    public GitlabWebhookHandler(GitlabEventFactory gitlabEventFactory, ObjectMapper objectMapper) {
        super(gitlabEventFactory, objectMapper);
    }

    @Override
    public Object handleWebhook(Webhook webhook, JsonNode params) {
        // 1. 获取请求事件
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 获取Gitlab请求事件
        String event = request.getHeader("X-Gitlab-Event");
        if (ObjectUtils.isEmpty(event)) {
            throw new UnsupportedOperationException("Unable to get the Gitlab event type, please check that your webhook configuration is correct");
        }
        // 执行事件处理
        return handlerExecute(event, webhook, params);
    }


}
