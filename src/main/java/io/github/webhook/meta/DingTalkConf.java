package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

/**
 * @author EalenXie created on 2023/4/17 10:01
 */
@Getter
@Setter
@ConfigurationPropertiesBinding
public class DingTalkConf {
    private String url = "https://oapi.dingtalk.com/robot/send";
    private String accessToken;
    private String signKey;

}
