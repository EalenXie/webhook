package io.github.webhook.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.WebhookHandler;
import io.github.webhook.core.WebhookHandlerFactory;
import io.github.webhook.core.WebhookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author EalenXie created on 2023/4/14 11:18
 */
@Slf4j
@RestController
public class WebhookEndpoint {
    public static final String ENDPOINT_URL = "/actuator";
    @Resource
    private WebhookRepository webhookRepository;
    @Resource
    private WebhookHandlerFactory webhookHandlerFactory;

    @PostMapping(ENDPOINT_URL + "/{id}")
    public ResponseEntity<Object> webhook(@PathVariable String id, @RequestBody JsonNode requestBody) {
        Webhook webhook = webhookRepository.findById(id);
        if (webhook == null) {
            return ResponseEntity.notFound().build();
        }
        WebhookHandler<?> handler = webhookHandlerFactory.getWebhookHandler(webhook.getType());
        if (handler == null) {
            throw new UnsupportedOperationException("unsupported webhook handler");
        }
        return ResponseEntity.ok(handler.handleWebhook(webhook, requestBody));
    }


}
