package io.github.webhook.config;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.endpoint.WebhookEndpoint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * webhook 配置
 *
 * @author EalenXie created on 2023/4/14 11:28
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(WebhookConfig.PREFIX)
@Validated
public class WebhookConfig {
    public static final String PREFIX = "config";
    /**
     * 默认为本机HOST 当本机需要进行域名或外部映射地址配置时需配置该值
     */
    private String webhookHost;
    @Resource
    private Environment environment;

    @NotEmpty(message = "请配置webhooks")
    private List<Webhook> webhooks = new ArrayList<>();

    /**
     * 客户端配置
     */
    private Admin admin;


    public String getWebhookHost() {
        if (webhookHost == null) {
            String ip = SpringEnvHelper.getLocalhostIp();
            int port = SpringEnvHelper.getPort();
            String contextPath = environment.getProperty("server.servlet.context-path");
            if (contextPath != null) {
                webhookHost = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
            } else {
                webhookHost = String.format("%s://%s:%s", "http", ip, port);
            }
        }
        return webhookHost;
    }

    public String getWebhookUrl(String webhookId) {
        return String.format("%s%s/%s", getWebhookHost(), WebhookEndpoint.ENDPOINT_URL, webhookId);
    }

    @Getter
    @Setter
    public static class Admin {
        private String username;
        private String password;
    }

}