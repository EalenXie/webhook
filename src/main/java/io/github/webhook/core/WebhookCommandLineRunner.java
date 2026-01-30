package io.github.webhook.core;

import io.github.webhook.gitlab.GitlabWebhookRegister;
import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WebhookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by EalenXie on 2022/2/18 9:38
 */
@Slf4j
@Component
public class WebhookCommandLineRunner implements CommandLineRunner {
    @Resource
    private WebhookProperties webhookProperties;
    @Resource
    private WebhookRepository webhookRepository;
    @Resource
    private GitlabWebhookRegister gitlabWebhookRegister;

    @Override
    public void run(String... args) {
        log.info("Webhook Home Address : {}/login.html", webhookProperties.getWebhookHost());
        List<Webhook> webhooks = webhookRepository.getWebhooks();
        if (!webhooks.isEmpty()) {
            log.info("Webhooks are successfully configured. The following webhooks are available:");
            webhooks.forEach(webhook -> {
                StringBuilder sb = new StringBuilder(String.format("Webhook[%s][%s]success!,Url: %s", webhook.getId(), webhook.getType(), webhookProperties.getWebhookUrl(webhook.getId())));
                if (!ObjectUtils.isEmpty(webhook.getGitlabProjectWebUrls())) {
                    List<String> success = gitlabWebhookRegister.register(webhook);
                    if (!ObjectUtils.isEmpty(success)) {
                        sb.append(String.format(" ,Projects:%s", success));
                    }
                }
                if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
                    sb.append(String.format(" ,Branches:%s", webhook.getGitlabOnlyRefs()));
                }
                log.info(sb.toString());
            });
        }

    }
}
