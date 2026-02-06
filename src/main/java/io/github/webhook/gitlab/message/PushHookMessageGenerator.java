package io.github.webhook.gitlab.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.push.PushHook;

import java.util.Collections;
import java.util.List;

public class PushHookMessageGenerator implements MessageGenerator<PushHook> {
    @Override
    public WebhookMessage generate(Webhook webhook, PushHook pushHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(pushHook.getObjectKind());
        message.setNotifies(Collections.singletonList(pushHook.getUserName()));
        List<Commit> commits = pushHook.getCommits();
        Project project = pushHook.getProject();
        String userUsername = pushHook.getUserUsername();
        Collections.sort(commits);
        StringBuilder sb = new StringBuilder();
        String branch = pushHook.getRef().replace("refs/heads/", "");
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) ", project.getName(), branch, project.getWebUrl(), branch));
        String c = commits.size() > 1 ? "commits" : "commit";
        String user = userUsername == null ? pushHook.getUserName() : String.format("[%s](%s)", userUsername, MessageGenerator.getUserHomePage(project.getWebUrl(), userUsername));
        sb.append(String.format("<font color='#000000'>%s %s new %s by %s %s </font>%n%n", pushHook.getEventName(), pushHook.getTotalCommitsCount(), c, "\uD83D\uDE00", user));
        for (Commit vo : commits) {
            sb.append(String.format("> [%s](%s) %s - %s%n%n", vo.getId().substring(0, 8), vo.getUrl(), vo.getAuthor().getName(), vo.getTitle()));
        }
        message.setMessage(sb.toString());
        return message;
    }
}
