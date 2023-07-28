package io.github.webhook.github.event.notify;

import io.github.webhook.github.dto.PullRequest;
import io.github.webhook.github.dto.PullRequestPayload;
import io.github.webhook.github.dto.Repository;
import io.github.webhook.github.event.PullRequestEventHandler;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/7/19 16:18
 */
public class PullRequestNotifyEventHandler extends GithubNotifyEventHandler<PullRequestPayload> implements PullRequestEventHandler {
    public PullRequestNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, PullRequestPayload payload) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle("Pull Request");
        Repository repository = payload.getRepository();
        PullRequest pullRequest = payload.getPullRequest();
        message.setNotifies(Collections.singletonList(String.valueOf(repository.getOwner().getEmail())));
        String action = payload.getAction();
        String emoji = "";
        if ("opened".equals(action)) {
            emoji = "\uD83D\uDCAC";
        } else if ("closed".equals(action)) {
            emoji = "\uD83D\uDEAB";
        }
        StringBuilder sb = new StringBuilder();
        String repo = String.format("[[%s]](%s)", repository.getName(), repository.getHtmlUrl());
        String pr = String.format("[[Pull request#%s]](%s)", pullRequest.getNumber(), pullRequest.getHtmlUrl());
        String sender = String.format("[%s](%s)", payload.getSender().getLogin(), payload.getSender().getHtmlUrl());
        sb.append(String.format("%s %s %s%s by %s %n%n%s", repo, pr, action, emoji, sender, pullRequest.getTitle()));
        message.setMessage(sb.toString());
        return message;
    }
}
