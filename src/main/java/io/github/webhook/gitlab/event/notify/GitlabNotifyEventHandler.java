package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/4/17 13:12
 */
public abstract class GitlabNotifyEventHandler<D> extends NotifyEventHandler<D> {

    protected GitlabNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    static String getUserHomePage(String projectUrl, String username) {
        return String.format("%s/%s", getHostSchema(projectUrl), username);
    }

    static String getHostSchema(String projectUrl) {
        String schema = projectUrl.substring(0, projectUrl.indexOf("//"));
        String body = projectUrl.substring(projectUrl.indexOf("//") + 2);
        String host = body.substring(0, body.indexOf("/"));
        return schema + host;
    }


}
