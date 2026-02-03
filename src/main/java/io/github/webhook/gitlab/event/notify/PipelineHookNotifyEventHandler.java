package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.pipeline.PipelineHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class PipelineHookNotifyEventHandler extends NotifyEventHandler<PipelineHook> {

    public PipelineHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<PipelineHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    public Class<PipelineHook> getDataType() {
        return PipelineHook.class;
    }

    @Override
    public boolean shouldHandleEvent(Webhook webhook, PipelineHook data) {
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            return MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getObjectAttributes().getRef());
        }
        return super.shouldHandleEvent(webhook, data);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, PipelineHook data) {
        PipelineHook.ObjectAttributes objectAttributes = data.getObjectAttributes();
        return objectAttributes != null && !"pending".equals(objectAttributes.getStatus());
    }


}
