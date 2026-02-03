package io.github.webhook.core;

import io.github.webhook.config.meta.Webhook;

import java.util.List;

/**
 * 消息生成器
 *
 * @author EalenXie created on 2023/4/14 14:22
 */
public interface MessageGenerator<D> {

    /**
     * 生成 webhook消息
     *
     * @param webhook webhook信息
     * @param data    输入数据对象
     * @return 消息
     */
    WebhookMessage generate(Webhook webhook, D data);

    static String getUserHomePage(String projectUrl, String username) {
        return String.format("%s/%s", getHostSchema(projectUrl), username);
    }

    static String getHostSchema(String projectUrl) {
        String schema = projectUrl.substring(0, projectUrl.indexOf("//"));
        String body = projectUrl.substring(projectUrl.indexOf("//") + 2);
        String host = body.substring(0, body.indexOf("/"));
        return schema + host;
    }


    static boolean onlyRefs(List<String> onlyRefs, String ref) {
        // 获取到分支
        String branch = ref;
        if (branch.startsWith("refs/heads/")) {
            branch = branch.replace("refs/heads/", "");
        }
        return onlyRefs.contains(branch);
    }
}
