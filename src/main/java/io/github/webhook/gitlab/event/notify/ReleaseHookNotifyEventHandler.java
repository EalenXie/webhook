package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.ReleaseEventHandler;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.release.ReleaseHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class ReleaseHookNotifyEventHandler extends GitlabNotifyEventHandler<ReleaseHook> implements ReleaseEventHandler {

    public ReleaseHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }


    @Override
    protected boolean shouldNotify(Webhook webhook, ReleaseHook data) {
        String releaseAction = data.getAction();
        return !"update".equals(releaseAction);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, ReleaseHook releaseHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(releaseHook.getObjectKind());
        // TODO
        message.setNotifies(null);
        String tag = releaseHook.getTag();
        Project project = releaseHook.getProject();
        ReleaseHook.Assets assets = releaseHook.getAssets();
        String tags = String.format("[%s](%s/-/tags/%s)", tag, project.getWebUrl(), tag);
        String head = String.format("<font color='#000000'>[%s](%s) %s new %s %s by tag%s(%s)</font> %s%n%n",
                project.getName(), project.getWebUrl(), releaseHook.getAction(), releaseHook.getObjectKind(),
                String.format("[%s](%s)", releaseHook.getName(), releaseHook.getUrl()), "\uD83D\uDCCC", tags, "\uD83D\uDE80\uD83D\uDE80\uD83D\uDE80");
        StringBuilder context = new StringBuilder(head);
        context.append(releaseHook.getDescription()).append("\n\n");
        context.append("<font color='#000000'>Assets</font> \n");
        for (ReleaseHook.Assets.Source source : assets.getSources()) {
            context.append(String.format("> - [%s Source code (%s)](%s) %n", "\uD83D\uDCC1", source.getFormat(), source.getUrl()));
        }
        message.setMessage(context.toString());
        return message;
    }

}
