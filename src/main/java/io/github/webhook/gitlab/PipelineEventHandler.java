package io.github.webhook.gitlab;

import io.github.webhook.core.EventHandler;
import io.github.webhook.gitlab.vo.pipeline.PipelineHook;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public interface PipelineEventHandler extends EventHandler<PipelineHook> {
    default Class<PipelineHook> getDataType() {
        return PipelineHook.class;
    }

}
