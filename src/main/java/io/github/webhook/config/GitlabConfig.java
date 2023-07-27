package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * @author EalenXie created on 2023/4/17 13:24
 * Gitlab 事件配置
 */
@Configuration
public class GitlabConfig {
    @Bean
    public GitlabRestClientFactory gitlabRestClientFactory(ObjectMapper objectMapper, RestOperations httpClientRestTemplate) {
        return new GitlabRestClientFactory(objectMapper, httpClientRestTemplate);
    }
}
