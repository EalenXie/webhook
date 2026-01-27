package io.github.webhook.gitlab.event.notify;

import io.github.webhook.gitlab.event.JobEventHandler;
import io.github.webhook.gitlab.webhook.Repository;
import io.github.webhook.gitlab.webhook.job.JobHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.util.Collections;
import java.util.Objects;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class JobHookNotifyEventHandler extends GitlabNotifyEventHandler<JobHook> implements JobEventHandler {

    public JobHookNotifyEventHandler(NotifierFactory notifierFactory) {
        super(notifierFactory);
    }

    @Override
    public NotifyMessage generate(Webhook webhook, JobHook jobHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(jobHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(jobHook.getUser().getId())));
        Repository repository = jobHook.getRepository();
        Long pipelineId = jobHook.getPipelineId();
        String buildStatus = jobHook.getBuildStatus();
        String project = String.format("[[%s]](%s)", repository.getName(), repository.getHomepage());
        String pipeline = String.format("pipeline[#%s](%s/-/pipelines/%s)", pipelineId, repository.getHomepage(), pipelineId);
        String costTime = String.format("%.0f", jobHook.getBuildDuration());
        if (costTime.isEmpty()) {
            costTime = "0";
        }
        String emoji = "";
        String color = "#000000";
        if (Objects.equals(buildStatus, "success")) {
            color = "#00b140";
            emoji = "✔️";
        } else if (Objects.equals(buildStatus, "failed")) {
            color = "#ff0000";
            emoji = "❌";
        } else if (Objects.equals(buildStatus, "canceled")) {
            color = "#FFDAC8";
            emoji = "⏹️";
        } else if (Objects.equals(buildStatus, "skipped")) {
            color = "#8E8E8E";
            emoji = "⏭️";
        }
        String build = String.format("<font color='%s'> [%s](%s/-/jobs/%s) %s%s</font>",
                color, jobHook.getBuildStage(), repository.getHomepage(),
                jobHook.getBuildId(), buildStatus, emoji);
        message.setMessage(String.format("<font color='#000000'>%s %s %s %s%ss</font>", project, pipeline, build, "\uD83D\uDD57", costTime));
        return message;
    }

}
