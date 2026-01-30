package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

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
     * 需要注册的gitlab Webhook触发来源，默认为push和pipeline（需配置服务器地址和Token，会对配置项目进行自动注册Webhook，针对固定事件进行触发注册）
     */
    private GitlabWebhookTrigger trigger;
    /**
     * 仅部分分支（事件处理）生效 注：此配置仅对包含有分支信息的消息类型有效（例如：PushHook，PipelinePush，JobHook，TagPushHook）
     */
    private List<String> onlyRefs;


    @SuppressWarnings("unused")
    public void setProjectWebUrls(List<String> projectWebUrls) {
        this.projectWebUrls = projectWebUrls;
        if (!ObjectUtils.isEmpty(this.projectWebUrls) && this.trigger == null) {
            this.trigger = new GitlabWebhookTrigger();
        }
    }

    /**
     * Gitlab webhook 触发来源配置
     */
    @Getter
    @Setter
    public static class GitlabWebhookTrigger {
        /**
         * 问题事件触发钩子
         */
        private Boolean issue;
        /**
         * 作业事件触发钩子
         */
        private Boolean job;
        /**
         * 推送事件触发钩子
         */
        private Boolean push = true;
        /**
         * 标签推送事件
         */
        private Boolean tagPush;
        /**
         * 合并请求事件触发钩子
         */
        private Boolean mergeRequest;
        /**
         * 笔记事件触发钩子
         */
        private Boolean note;
        /**
         * 流水线事件触发钩子
         */
        private Boolean pipeline = true;
        /**
         * 发布事件触发钩子
         */
        private Boolean release;

    }
}
