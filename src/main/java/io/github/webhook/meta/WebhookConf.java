package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/4/17 15:51
 */
@Getter
@Setter
public class WebhookConf {
    /**
     * 开启此配置 可启用 gitlab rest api
     */
    private GitlabConf gitlab;
}
