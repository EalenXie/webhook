package io.github.webhook.core;

import io.github.webhook.config.WebhookConfig;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.gitlab.GitlabHookClient;
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
    private WebhookConfig webhookConfig;
    @Resource
    private WebhookRepository webhookRepository;
    @Resource
    private GitlabHookClient gitlabHookClient;

    @Override
    public void run(String... args) {
        log.info("Webhook Home Address : {}/login.html", webhookConfig.getWebhookHost());
        List<Webhook> webhooks = webhookRepository.getWebhooks();
        if (!webhooks.isEmpty()) {
            log.info("Webhooks are successfully configured. The following webhooks are available:");
            webhooks.forEach(webhook -> {
                StringBuilder sb = new StringBuilder(String.format("[%s]Webhook[%s]success!,Url: %s", webhook.getType(), webhook.getId(), webhookConfig.getWebhookUrl(webhook.getId())));
                if (!ObjectUtils.isEmpty(webhook.getGitlabProjectWebUrls())) {
                    List<String> success = gitlabHookClient.addHook(webhook);
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
