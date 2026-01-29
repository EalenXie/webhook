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
     * 仅部分分支（事件处理）生效 注：改配置项目仅只支持包含有分支信息的消息类型有效（例如：{pushHook PushHook，PipelinePush JobHook TagPushHook）
     */
    private List<String> onlyRefs;
    /**
     * 配置多个项目地址
     */
    private List<String> projectWebUrls;
}
