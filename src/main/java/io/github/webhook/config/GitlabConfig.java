package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.gitlab.GitlabWebhookRegister;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.meta.WebhookProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * @author EalenXie created on 2023/4/17 13:24
 * Gitlab 配置
 */
@Configuration
public class GitlabConfig {
    @Bean
    public GitlabRestClientFactory gitlabRestClientFactory(ObjectMapper objectMapper, RestOperations httpClientRestTemplate) {
        return new GitlabRestClientFactory(objectMapper, httpClientRestTemplate);
    }

    @Bean
    public GitlabWebhookRegister gitlabWebhookRegister(WebhookProperties webhookProperties, GitlabRestClientFactory gitlabRestClientFactory) {
        return new GitlabWebhookRegister(webhookProperties, gitlabRestClientFactory);
    }


}
