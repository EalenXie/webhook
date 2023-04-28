package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.zentao.ZentaoWebhookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author EalenXie created on 2023/4/28 13:12
 */
@Configuration
public class ZentaoEventHandlerConfig {

    @Bean
    public ZentaoWebhookHandler zentaoWebhookHandler(ObjectMapper objectMapper) {
        return new ZentaoWebhookHandler(objectMapper);
    }


}
