package io.github.webhook.notify.dingtalk;

import io.github.webhook.meta.DingTalkConf;
import io.github.webhook.meta.NotifyConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifyMessage;
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
public class DingTalkNotifier implements Notifier {
    private final RestOperations restOperations;

    public DingTalkNotifier(RestOperations restOperations) {
        this.restOperations = restOperations;
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

    @Override
    public void notify(Webhook webhook, NotifyMessage message) {
        MarkdownMessage markdownMessage = new MarkdownMessage();
        StringBuilder sb = new StringBuilder();
        if (!ObjectUtils.isEmpty(message.getNotifies())) {
            List<String> notifies = message.getNotifies();
            List<String> atMobiles = new ArrayList<>();
            for (String notifier : notifies) {
                if (!ObjectUtils.isEmpty(notifier)) {
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
        NotifyConf notify = webhook.getNotify();
        DingTalkConf dingTalk = notify.getDingTalk();
        sendMessage(dingTalk.getAccessToken(), dingTalk.getSignKey(), markdownMessage);
    }

    public void sendMessage(String accessToken, String signKey, MarkdownMessage message) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MarkdownMessage> entity = new HttpEntity<>(message, httpHeaders);
        long timeStamp = System.currentTimeMillis();
        restOperations.postForEntity(String.format("https://oapi.dingtalk.com/robot/send?access_token=%s&timestamp=%s&sign=%s", accessToken, timeStamp, sign(timeStamp, signKey)), entity, Object.class);
    }
}
