package io.github.webhook.core;

import io.github.webhook.config.SpringEnvHelper;
import io.github.webhook.endpoint.WebhookEndpoint;
import io.github.webhook.meta.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class WebhookCommandLineRunner implements CommandLineRunner {


    @Resource
    private Environment environment;

    @Resource
    private WebhookRepository webhookRepository;

    @Override
    public void run(String... args) {
        String ip = SpringEnvHelper.getLocalhostIp();
        int port = SpringEnvHelper.getPort();
        String contextPath = environment.getProperty("server.servlet.context-path");
        String contentPath;
        if (contextPath != null) {
            contentPath = String.format("%s://%s:%s%s", "http", ip, port, contextPath);
        } else {
            contentPath = String.format("%s://%s:%s", "http", ip, port);
        }
        List<Webhook> webhooks = webhookRepository.getWebhooks();
        if (!webhooks.isEmpty()) {
            StringBuilder sb = new StringBuilder("Webhooks are successfully configured.\n");
            sb.append("The following webhooks are available,Please fill in the following address in your system's webhook: \n");
            for (Webhook webhook : webhooks) {
                sb.append(String.format("Webhook[%s] of type %s is configured,Address: %s%s/%s%n", webhook.getId(), webhook.getType(), contentPath, WebhookEndpoint.ENDPOINT_URL, webhook.getId()));
            }
            log.info(sb.toString());
        }

    }
}
