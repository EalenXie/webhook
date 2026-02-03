package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.NotifyEventHandler;
import io.github.webhook.gitlab.webhook.issue.IssueHook;
import io.github.webhook.notify.NotifierFactory;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class IssueHookNotifyEventHandler extends NotifyEventHandler<IssueHook> {


    public IssueHookNotifyEventHandler(NotifierFactory notifierFactory, MessageGenerator<IssueHook> messageGenerator) {
        super(notifierFactory, messageGenerator);
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, IssueHook data) {
        String issueAction = data.getObjectAttributes().getAction();
        return !"update".equals(issueAction);
    }


}
