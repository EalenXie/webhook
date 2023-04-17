package io.github.webhook.notify.wechat;

import io.github.webhook.meta.NotifyConfig;
import io.github.webhook.meta.WeChatConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifyMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 17:52
 */
public class CorpWechatNotifier implements Notifier {


    private final RestOperations restOperations;

    public CorpWechatNotifier(RestOperations restOperations) {
        this.restOperations = restOperations;
    }


    @Override
    public void notify(Webhook webhook, NotifyMessage message) {
        Markdown markdown = new Markdown();
        StringBuilder sb = new StringBuilder();
        if (!message.getNotifies().isEmpty()) {
            List<String> atMobiles = new ArrayList<>(message.getNotifies());
            markdown.setMentionedMobileList(atMobiles.toArray(new String[0]));
        }
        sb.append(message.getMessage());
        markdown.setContent(sb.toString());
        MarkdownMessage markdownMessage = new MarkdownMessage(markdown);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MarkdownMessage> entity = new HttpEntity<>(markdownMessage, httpHeaders);
        NotifyConfig notify = webhook.getNotify();
        WeChatConf weChat = notify.getWeChat();
        restOperations.postForEntity(String.format("https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=%s", weChat.getKey()), entity, Object.class);
    }
}
