package io.github.webhook.notify.dingtalk;

import io.github.webhook.config.meta.NotifyConf;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.notify.Notifier;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestOperations;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 16:17
 */
public class DingTalkNotifier implements Notifier<MarkdownMessage, Object> {
    private final RestOperations restOperations;

    public DingTalkNotifier(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public MarkdownMessage process(WebhookMessage message) {
        MarkdownMessage markdownMessage = new MarkdownMessage();
        StringBuilder sb = new StringBuilder();
        if (!ObjectUtils.isEmpty(message.getNotifies())) {
            List<String> notifies = message.getNotifies();
            List<String> atMobiles = new ArrayList<>();
            for (String notifier : notifies) {
                if (!ObjectUtils.isEmpty(notifier) && PHONE_PATTERN.matcher(notifier).matches()) {
                    sb.append("@").append(notifier);
                    atMobiles.add(notifier);
                }
            }
            if (sb.length() > 0) {
                sb.append("\n\n");
            }
            DingRobotAt at = new DingRobotAt();
            at.setAtMobiles(atMobiles);
            markdownMessage.setAt(at);
        }
        sb.append(message.getMessage());
        Markdown markdown = new Markdown(message.getTitle(), sb.toString());
        markdownMessage.setMarkdown(markdown);
        return markdownMessage;
    }

    @Override
    public Object notify(Webhook webhook, MarkdownMessage markdownMessage) {
        NotifyConf notify = webhook.getNotify();
        NotifyConf.DingTalk dingTalk = notify.getDingTalk();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MarkdownMessage> entity = new HttpEntity<>(markdownMessage, httpHeaders);
        long timeStamp = System.currentTimeMillis();
        return restOperations.postForEntity(String.format("https://oapi.dingtalk.com/robot/send?access_token=%s&timestamp=%s&sign=%s",
                dingTalk.getAccessToken(), timeStamp, sign(timeStamp, dingTalk.getSignKey())), entity, Object.class).getBody();
    }

    /**
     * 钉钉接口签名
     *
     * @param timestamp 时间戳
     * @param secret    签名密钥
     * @return 签名
     */
    public static String sign(long timestamp, String secret) {
        String stringToSign = timestamp + "\n" + secret;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8.name());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}