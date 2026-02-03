package io.github.webhook.config.meta;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 11:21
 */
@Getter
@Setter
public class Webhook {
    /**
     * webhook ID
     */
    private String id;
    /**
     * webhook 类型 会根据类型处理不同的事件类型
     */
    private WebhookType type;
    /**
     * 通知 配置
     */
    private NotifyConf notify;
    /**
     * webhook 配置
     */
    private Conf conf;

    @Getter
    @Setter
    public static class Conf {
        /**
         * 开启此配置 可启用 gitlab rest api
         */
        private GitlabConf gitlab;
    }


    public String getGitlabHost() {
        if (conf != null && conf.getGitlab() != null) {
            return conf.getGitlab().getHost();
        }
        return null;
    }

    public List<String> getGitlabProjectWebUrls() {
        if (conf != null && conf.getGitlab() != null) {
            return conf.getGitlab().getProjectWebUrls();
        }
        return Collections.emptyList();
    }


    public List<String> getGitlabOnlyRefs() {
        if (conf != null && conf.getGitlab() != null) {
            return conf.getGitlab().getOnlyRefs();
        }
        return Collections.emptyList();
    }

    public GitlabConf.Trigger getGitlabTrigger() {
        if (conf != null && conf.getGitlab() != null) {
            return conf.getGitlab().getTrigger();
        }
        return null;
    }

}
