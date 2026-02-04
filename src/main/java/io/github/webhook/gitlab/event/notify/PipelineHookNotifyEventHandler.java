package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.pipeline.PipelineHook;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class PipelineHookNotifyEventHandler extends NotifyEventHandler<PipelineHook> {

    public PipelineHookNotifyEventHandler(MessageGenerator<PipelineHook> messageGenerator) {
        super(messageGenerator);
    }


    @Override
    protected boolean shouldNotify(Webhook webhook, PipelineHook data) {
        boolean shouldNotify = true;
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            shouldNotify = MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getObjectAttributes().getRef());
        }
        if (shouldNotify) {
            PipelineHook.ObjectAttributes objectAttributes = data.getObjectAttributes();
            return objectAttributes != null && !"pending".equals(objectAttributes.getStatus());
        }
        return false;
    }

}
