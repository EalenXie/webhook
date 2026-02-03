package io.github.webhook.gitlab.event.notify;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.job.JobHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class JobHookNotifyEventHandler extends NotifyEventHandler<JobHook> {

    public JobHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<JobHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    public Class<JobHook> getDataType() {
        return JobHook.class;
    }

    @Override
    public boolean shouldHandleEvent(Webhook webhook, JobHook data) {
        if (!ObjectUtils.isEmpty(webhook.getGitlabOnlyRefs())) {
            return MessageGenerator.onlyRefs(webhook.getGitlabOnlyRefs(), data.getRef());
        }
        return super.shouldHandleEvent(webhook, data);
    }


}
