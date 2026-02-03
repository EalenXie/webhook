package io.github.webhook.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.config.meta.WebhookType;
import io.github.webhook.core.PropertiesWebhookRepository;
import io.github.webhook.core.WebhookHandlerFactory;
import io.github.webhook.core.WebhookRepository;
import io.github.webhook.github.GithubWebhookHandler;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    /**
     * Webhook 处理器 工厂
     */
    @Bean
    public WebhookHandlerFactory webhookHandlerFactory(ApplicationContext applicationContext) {
        WebhookHandlerFactory factory = new WebhookHandlerFactory();
        factory.registerHandler(WebhookType.GITHUB, applicationContext.getBean(GithubWebhookHandler.class));
        factory.registerHandler(WebhookType.GITLAB, applicationContext.getBean(GitlabWebhookHandler.class));
        return factory;
    }

    /**
     * 通知器 工厂
     */
    @Bean
    public NotifierFactory notifierFactory(ApplicationContext applicationContext, RestOperations httpClientRestTemplate) {
        NotifierFactory factory = new NotifierFactory(applicationContext);
        factory.registerNotifier(new DingTalkNotifier(httpClientRestTemplate));
        factory.registerNotifier(new CorpWechatNotifier(httpClientRestTemplate));
        factory.registerNotifier(new FeiShuNotifier(httpClientRestTemplate));
        return factory;
    }


    @Bean
    @ConditionalOnMissingBean
    public WebhookRepository webhookRepository(WebhookConfig webhookConfig) {
        return new PropertiesWebhookRepository(webhookConfig);
    }

    @Bean
    public SpringEnvHelper springEnvHelper() {
        return new SpringEnvHelper();
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
