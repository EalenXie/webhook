package io.github.webhook.config.meta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * 消息通知配置
 *
 * @author EalenXie created on 2023/4/17 10:04
 */
@Getter
@Setter
public class NotifyConf {

    /**
     * 钉钉
     */
    private DingTalk dingTalk;
    /**
     * 飞书
     */
    private FeiShu feiShu;
    /**
     * 企业微信
     */
    private CorpWechat wechat;

    @Getter
    @Setter
    @ConfigurationPropertiesBinding
    public static class DingTalk {
        private String url = "https://oapi.dingtalk.com/robot/send";
        private String accessToken;
        private String signKey;
    }

    @Getter
    @Setter
    @ConfigurationPropertiesBinding
    public static class FeiShu {
        private String url;
        private String signKey;
    }


    @Getter
    @Setter
    @ConfigurationPropertiesBinding
    public static class CorpWechat {
        private String key;
    }


}
