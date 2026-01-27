package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/7/15 22:46
 */
@Getter
@Setter
public class GitlabConf {
    /**
     * Gitlab服务器地址
     */
    private String host;
    /**
     * 私有Token
     */
    private String privateToken;
    /**
     * 配置多个项目地址
     */
    private List<String> projectWebUrls;
}
