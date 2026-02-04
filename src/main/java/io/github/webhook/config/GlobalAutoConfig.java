package io.github.webhook.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.config.meta.WebhookType;
import io.github.webhook.core.DefaultEventHandlerFactory;
import io.github.webhook.core.PropertiesWebhookRepository;
import io.github.webhook.core.WebhookHandlerFactory;
import io.github.webhook.core.WebhookRepository;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.gitlab.GitlabHookClient;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.notify.NotifierFactory;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 全局配置
 *
 * @author EalenXie created on 2023/4/14 17:55
 */
@Configuration
public class GlobalAutoConfig {

    @Bean
    public SpringEnvHelper springEnvHelper() {
        return new SpringEnvHelper();
    }

    /**
     * 默认的事件处理器工厂
     */
    @Bean
    public DefaultEventHandlerFactory defaultEventHandlerFactory() {
        return new DefaultEventHandlerFactory();
    }

    /**
     * Webhook事件及其组件注册器
     */
    @Bean
    @DependsOn("springEnvHelper")
    public WebhookBeanRegister webhookBeanRegister(RestOperations httpClientRestTemplate) {
        return new WebhookBeanRegister(httpClientRestTemplate);
    }

    /**
     * Github Webhook 处理器
     */
    @Bean
    public GithubWebhookHandler githubWebhookHandler(DefaultEventHandlerFactory defaultEventHandlerFactory, ObjectMapper objectMapper) {
        return new GithubWebhookHandler(defaultEventHandlerFactory, objectMapper);
    }

    /**
     * Gitlab Webhook 处理器
     */
    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(DefaultEventHandlerFactory defaultEventHandlerFactory, ObjectMapper objectMapper) {
        return new GitlabWebhookHandler(defaultEventHandlerFactory, objectMapper);
    }

    @Bean
    public GitlabRestClientFactory gitlabRestClientFactory(ObjectMapper objectMapper, RestOperations httpClientRestTemplate) {
        return new GitlabRestClientFactory(objectMapper, httpClientRestTemplate);
    }

    @Bean
    public GitlabHookClient gitlabHookClient(WebhookConfig webhookConfig, GitlabRestClientFactory gitlabRestClientFactory) {
        return new GitlabHookClient(webhookConfig, gitlabRestClientFactory);
    }

    /**
     * Webhook 处理器 工厂
     */
    @Bean
    @DependsOn("springEnvHelper")
    public WebhookHandlerFactory webhookHandlerFactory() {
        WebhookHandlerFactory factory = new WebhookHandlerFactory();
        factory.addHandler(WebhookType.GITHUB, SpringEnvHelper.getBean(GithubWebhookHandler.class));
        factory.addHandler(WebhookType.GITLAB, SpringEnvHelper.getBean(GitlabWebhookHandler.class));
        return factory;
    }

    /**
     * 通知器 工厂
     */
    @Bean
    public NotifierFactory notifierFactory() {
        return new NotifierFactory();
    }


    @Bean
    @ConditionalOnMissingBean
    public WebhookRepository webhookRepository(WebhookConfig webhookConfig) {
        return new PropertiesWebhookRepository(webhookConfig);
    }


    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    @Bean
    @ConditionalOnMissingBean(name = "httpClientRestTemplate")
    public RestTemplate httpClientRestTemplate() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setMaxTotal(50);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5 * 1000);
        factory.setReadTimeout(10 * 1000);
        factory.setHttpClient(closeableHttpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (int i = 0; i < converters.size(); i++) {
            if (converters.get(i) instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter converter = (StringHttpMessageConverter) converters.get(i);
                converter.setDefaultCharset(StandardCharsets.UTF_8);
                converters.set(i, converter);
                break;
            }
        }
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }


}
