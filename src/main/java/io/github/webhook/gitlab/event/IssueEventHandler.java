package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.issue.IssueHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface IssueEventHandler extends EventHandler<IssueHook> {
    default Class<IssueHook> getDataType() {
        return IssueHook.class;
    }

}
