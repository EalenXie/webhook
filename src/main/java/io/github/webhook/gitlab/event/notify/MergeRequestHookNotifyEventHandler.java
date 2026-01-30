package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.User;
import io.github.webhook.gitlab.webhook.mergerequest.MergeRequestHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class MergeRequestHookNotifyEventHandler extends GitlabNotifyEventHandler<MergeRequestHook> {

    @Override
    public Class<MergeRequestHook> getDataType() {
        return MergeRequestHook.class;
    }

    public MergeRequestHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }


    @Override
    protected boolean shouldNotify(Webhook webhook, MergeRequestHook data) {
        String action = data.getObjectAttributes().getAction();
        return action != null && !"update".equals(action);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, MergeRequestHook mergeRequestHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(mergeRequestHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(mergeRequestHook.getUser().getId())));
        User user = mergeRequestHook.getUser();
        Project project = mergeRequestHook.getProject();
        MergeRequestHook.ObjectAttributes attributes = mergeRequestHook.getObjectAttributes();
        StringBuilder sb = new StringBuilder();
        String p = String.format("[[%s]](%s)", project.getName(), project.getWebUrl());
        String sources = String.format("[%s](%s/-/tree/%s)", attributes.getSourceBranch(), project.getWebUrl(), attributes.getSourceBranch());
        String targets = String.format("[%s](%s/-/tree/%s)", attributes.getTargetBranch(), project.getWebUrl(), attributes.getTargetBranch());
        String u = String.format("[%s](%s)", user.getUsername(), getUserHomePage(project.getWebUrl(), user.getUsername()));
        String merge = String.format(" [#%s](%s)(%s)", attributes.getId(), attributes.getUrl(), attributes.getTitle());
        sb.append(String.format("<font color='#000000'>%s %s %s %s %s </font>%n%n", p, u, attributes.getState(), mergeRequestHook.getObjectKind(), merge));
        switch (attributes.getState()) {
            case "opened":
                Commit lastCommit = attributes.getLastCommit();
                sb.append(String.format("%s %s  wants to merge %s ➔➔ %s %n", " \uD83D\uDE00 ", user.getUsername(), sources, targets));
                String c = String.format(" %s - %s%n", lastCommit.getAuthor().getName(), lastCommit.getMessage());
                sb.append(String.format(">[%s](%s)%s", lastCommit.getId().substring(0, 8), lastCommit.getUrl(), c));
                break;
            case "merged":
                sb.append(String.format(" %s %s has completed the merge %s➔➔%s%s%n", " \uD83D\uDE00 ", user.getUsername(), sources, targets, "✔️"));
                break;
            case "closed":
                sb.append(String.format(" %s %s has closed the merge %s➔➔%s%s %n", " \uD83D\uDE36 ", user.getUsername(), sources, targets, "\uD83D\uDEAB"));
                break;
            default:
                break;
        }
        message.setMessage(sb.toString());
        return message;
    }

}
