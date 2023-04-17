package io.github.webhook.config;

import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
 * @author EalenXie created on 2023/4/17 12:54
 * <p>
 * 通知 配置
 */
@Configuration
public class NotifierConfig {
    @Bean
    public NotifierFactory notifierFactory() {
        return new NotifierFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public DingTalkNotifier dingTalkNotifier(RestOperations httpClientRestTemplate) {
        return new DingTalkNotifier(httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public CorpWechatNotifier corpWechatNotifier(RestOperations httpClientRestTemplate) {
        return new CorpWechatNotifier(httpClientRestTemplate);
    }


    @Bean
    @ConditionalOnMissingBean
    public FeiShuNotifier feiShuNotifier(RestOperations httpClientRestTemplate) {
        return new FeiShuNotifier(httpClientRestTemplate);
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
