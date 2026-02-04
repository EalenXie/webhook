package io.github.webhook.gitlab.message;

import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.webhook.Repository;
import io.github.webhook.gitlab.webhook.job.JobHook;

import java.util.Collections;
import java.util.Objects;

public class JobHookMessageGenerator implements MessageGenerator<JobHook> {
    @Override
    public WebhookMessage generate(Webhook webhook, JobHook jobHook) {
        WebhookMessage message = new WebhookMessage();
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
        String build = String.format("<font color='%s'> [%s](%s/-/jobs/%s) %s%s</font>", color, jobHook.getBuildStage(), repository.getHomepage(), jobHook.getBuildId(), buildStatus, emoji);
        message.setMessage(String.format("<font color='#000000'>%s %s %s %s%ss</font>", project, pipeline, build, "\uD83D\uDD57", costTime));
        return message;
    }
}
