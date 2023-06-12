package io.github.webhook.gitlab.event;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.webhook.job.JobHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface JobEventHandler extends EventHandler<JobHook, Object> {
    default Class<JobHook> getDataType() {
        return JobHook.class;
    }

}
