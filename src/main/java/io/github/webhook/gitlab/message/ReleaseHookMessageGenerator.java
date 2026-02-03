package io.github.webhook.gitlab.message;

import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.release.ReleaseHook;
import io.github.webhook.meta.Webhook;

public class ReleaseHookMessageGenerator implements MessageGenerator<ReleaseHook> {

    @Override
    public WebhookMessage generate(Webhook webhook, ReleaseHook releaseHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(releaseHook.getObjectKind());
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
