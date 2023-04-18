package io.github.webhook.notify.feishu;

import io.github.webhook.meta.FeiShuConf;
import io.github.webhook.meta.NotifyConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.Notifier;
import io.github.webhook.notify.NotifyMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

/**
 * @author EalenXie created on 2023/4/17 12:53
 */
public class FeiShuNotifier implements Notifier {

    private final RestOperations restOperations;

    public FeiShuNotifier(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @Override
    public void notify(Webhook webhook, NotifyMessage message) {
        Interactive interactive = new Interactive();
        interactive.setConfig(new Config(true, true));
        interactive.setHeader(new Header(new Title(message.getTitle(), "plain_text")));
        MarkdownElement markdownElement = new MarkdownElement(message.getMessage());
        interactive.setElements(Collections.singletonList(markdownElement));
        InteractiveMessage interactiveMessage = new InteractiveMessage(interactive);
        NotifyConf notify = webhook.getNotify();
        FeiShuConf feiShu = notify.getFeiShu();
        sendMessage(feiShu.getUrl(), feiShu.getSignKey(), interactiveMessage);

    }

    public void sendMessage(String url, String signKey, InteractiveMessage interactiveMessage) {
        if (signKey != null && !"".equals(signKey.trim())) {
            long timestamp = System.currentTimeMillis();
            String sign = sign(timestamp, signKey);
            interactiveMessage.setTimestamp(String.valueOf(timestamp));
            interactiveMessage.setSign(sign);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InteractiveMessage> entity = new HttpEntity<>(interactiveMessage, httpHeaders);
        restOperations.postForEntity(url, entity, Object.class);
    }


    /**
     * 飞书接口签名
     *
     * @param timestamp 时间戳
     * @param signKey   签名密钥
     * @return 签名
     */
    public static String sign(long timestamp, String signKey) {
        String stringToSign = timestamp + "\n" + signKey;
        try {
            final String ALGORITHM = "HmacSHA256";
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(signKey.getBytes(StandardCharsets.UTF_8), ALGORITHM));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8.name());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
