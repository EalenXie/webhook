package io.github.webhook.notify.wechat;

import io.github.webhook.meta.NotifyConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WechatConf;
import io.github.webhook.notify.Notifier;
import io.github.webhook.core.WebhookMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 17:52
 */
public class CorpWechatNotifier implements Notifier<MarkdownMessage, Object> {

    private final RestOperations restOperations;

    public CorpWechatNotifier(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public MarkdownMessage process(WebhookMessage message) {
        Markdown markdown = new Markdown();
        StringBuilder sb = new StringBuilder();
        if (!ObjectUtils.isEmpty(message.getNotifies())) {
            List<String> atMobiles = new ArrayList<>();
            for (String notifier : message.getNotifies()) {
                if (!ObjectUtils.isEmpty(notifier) && PHONE_PATTERN.matcher(notifier).matches()) {
                    atMobiles.add(notifier);
                }
            }
            markdown.setMentionedMobileList(atMobiles.toArray(new String[0]));
        }
        sb.append(message.getMessage());
        markdown.setContent(sb.toString());
        return new MarkdownMessage(markdown);
    }

    @Override
    public Object notify(Webhook webhook, MarkdownMessage markdownMessage) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MarkdownMessage> entity = new HttpEntity<>(markdownMessage, httpHeaders);
        NotifyConf notify = webhook.getNotify();
        WechatConf wechat = notify.getWechat();
        return restOperations.postForEntity(String.format("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s", wechat.getKey()), entity, Object.class).getBody();
    }
}
