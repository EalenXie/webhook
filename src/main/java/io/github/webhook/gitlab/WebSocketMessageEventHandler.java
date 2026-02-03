package io.github.webhook.gitlab;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.EventHandler;

public class WebSocketMessageEventHandler implements EventHandler<JsonNode, Object> {



    @Override
    public Object handleEvent(Webhook webhook, JsonNode params) {


        return null;
    }

}
