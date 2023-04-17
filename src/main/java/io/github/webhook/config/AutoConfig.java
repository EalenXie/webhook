package io.github.webhook.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.core.PropertiesWebhookRepository;
import io.github.webhook.core.WebhookHandlerFactory;
import io.github.webhook.core.WebhookRepository;
import io.github.webhook.meta.WebhookProperties;
import io.github.webhook.zentao.ZentaoWebhookHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author EalenXie created on 2023/4/14 17:55
 */
@Configuration
public class AutoConfig {

    @Bean
    public WebhookHandlerFactory webhookHandlerFactory() {
        return new WebhookHandlerFactory();
    }

    @Bean
    public ZentaoWebhookHandler zentaoWebhookHandler() {
        return new ZentaoWebhookHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean
    public WebhookRepository webhookRepository(WebhookProperties webhookProperties) {
        return new PropertiesWebhookRepository(webhookProperties);
    }


    @Bean
    public SpringEnvHelper springEnvHelper() {
        return new SpringEnvHelper();
    }


}
