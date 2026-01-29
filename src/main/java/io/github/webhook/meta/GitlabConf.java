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
     * 配置多个项目地址（需配置服务器地址和Token，会对配置项目进行自动注册Webhook）
     */
    private List<String> projectWebUrls;
    /**
     * 仅部分分支（事件处理）生效 注：此配置仅对包含有分支信息的消息类型有效（例如：PushHook，PipelinePush，JobHook，TagPushHook）
     */
    private List<String> onlyRefs;
}
