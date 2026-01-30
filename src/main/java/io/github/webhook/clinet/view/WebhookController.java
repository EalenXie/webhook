package io.github.webhook.clinet.view;

import io.github.webhook.clinet.view.vo.WebhookInfo;
import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WebhookProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actuator/webhooks")
public class WebhookController {


    @Resource
    private WebhookProperties webhookProperties;


    @GetMapping("/configs")
    public List<WebhookInfo> listConfigs() {
        List<Webhook> webhooks = webhookProperties.getWebhooks();
        List<WebhookInfo> resp = new ArrayList<>();
        for (Webhook webhook : webhooks) {
            WebhookInfo webhookInfo = new WebhookInfo(webhook);
            webhookInfo.setWebhookUrl(webhookProperties.getWebhookUrl(webhook.getId()));
            resp.add(webhookInfo);
        }
        return resp;
    }
}
