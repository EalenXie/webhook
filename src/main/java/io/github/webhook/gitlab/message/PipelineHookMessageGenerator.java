package io.github.webhook.gitlab.message;


import io.github.webhook.config.FileConvert;
import io.github.webhook.config.WebhookConfig;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.core.MessageGenerator;
import io.github.webhook.core.WebhookMessage;
import io.github.webhook.gitlab.GitlabEndpoint;
import io.github.webhook.gitlab.webhook.Build;
import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.pipeline.PipelineHook;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PipelineHookMessageGenerator implements MessageGenerator<PipelineHook> {

    private final WebhookConfig webhookConfig;

    public PipelineHookMessageGenerator(WebhookConfig webhookConfig) {
        this.webhookConfig = webhookConfig;
    }

    @SuppressWarnings("all")
    @Override
    public WebhookMessage generate(Webhook webhook, PipelineHook pipelineHook) {
        WebhookMessage message = new WebhookMessage();
        message.setTitle(pipelineHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(pipelineHook.getUser().getId())));
        StringBuilder sb = new StringBuilder();
        PipelineHook.ObjectAttributes attributes = pipelineHook.getObjectAttributes();
        Project project = pipelineHook.getProject();
        Commit commit = pipelineHook.getCommit();
        List<Build> builds = pipelineHook.getBuilds();
        String status = attributes.getStatus();
        String pipeline = String.format("%s [#%s %s](%s/-/pipelines/%s)", pipelineHook.getObjectKind(), attributes.getId(), "\uD83D\uDE80", project.getWebUrl(), attributes.getId());
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), attributes.getRef(), project.getWebUrl(), attributes.getRef(), pipeline, status));
        if (!"running".equals(status)) {
            int totalTime = 0;
            if (attributes.getDuration() != null) {
                totalTime += attributes.getDuration();
            }
            if (attributes.getQueuedDuration() != null) {
                totalTime += attributes.getQueuedDuration();
            }
            sb.append(String.format(">[%s](%s) %s - %s%n%n", commit.getId().substring(0, 8), commit.getUrl(), commit.getAuthor().getName(), commit.getTitle()));
            String statusEmoji = "";
            String statusColor = "";
            switch (status) {
                case "success":
                    statusEmoji = "✅";
                    statusColor = "#00b140";
                    break;
                case "failed":
                    statusEmoji = "❌";
                    statusColor = "#ff0000";
                    break;
                case "canceled":
                    statusEmoji = "⏹️";
                    statusColor = "#FFDAC8";
                    break;
                case "skipped":
                    statusEmoji = "⏭️";
                    statusColor = "#8E8E8E";
                    break;
                default:
                    break;
            }
            sb.append(String.format("%s%s : <font color='%s'>%s</font> %s %ss%n%n", statusEmoji, pipeline, statusColor, attributes.getDetailedStatus(), "\uD83D\uDD57", totalTime));
            Collections.sort(builds);
            for (Build build : builds) {
                String costTime = String.format("%.0f", build.getDuration());
                if (costTime.equals("")) {
                    if (build.getFinishedAt() != null && build.getStartedAt() != null) {
                        Date finishedAt = Date.from(LocalDateTime.parse(build.getFinishedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        Date start = Date.from(LocalDateTime.parse(build.getStartedAt().substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).atZone(ZoneId.systemDefault()).toInstant());
                        costTime = String.valueOf((finishedAt.getTime() - start.getTime()) / 1000);
                    } else {
                        costTime = "0";
                    }
                }
                String color = "";
                String emoji = "";
                switch (build.getStatus()) {
                    case "success":
                        color = "#00b140";
                        emoji = "✔️";
                        break;
                    case "failed":
                        color = "#ff0000";
                        emoji = "❌";
                        break;
                    case "canceled":
                        color = "#FFDAC8";
                        emoji = "⏹️";
                        break;
                    case "skipped":
                        color = "#8E8E8E";
                        emoji = "⏭️";
                        break;
                    case "manual":
                        color = "#8E8E8E";
                        emoji = "\uD83D\uDD04";
                        break;
                    default:
                        break;
                }

                String fileName = "";
                if (build.getArtifactFile().getFilename() != null && build.getArtifactFile().getSize() != null) {
                    fileName = String.format("[%s](%s/-/jobs/%s/artifacts/download) %s", build.getArtifactFile().getFilename(), project.getWebUrl(), build.getId(), FileConvert.getFormatFileSize(build.getArtifactFile().getSize()));
                }
                sb.append(String.format(">%s [%s](%s/-/jobs/%s) : <font color='%s'>%s</font> %s %s %ss%n%n", emoji, build.getStage(), project.getWebUrl(), build.getId(), color, build.getStatus(), fileName, "\uD83D\uDD57", costTime));
            }
        } else {
            Long projectId = project.getId();
            Long pipelineId = attributes.getId();
            String hostSchema = String.format("%s%s/%s/gitlab/pipeline", webhookConfig.getWebhookHost(), GitlabEndpoint.ENDPOINT_URL, webhook.getId());
            String query = String.format("projectId=%s&pipelineId=%s", projectId, pipelineId);
            sb.append(String.format("[\uD83D\uDEAB取消运行](%s/cancel?%s) ", hostSchema, query));
            sb.append(String.format("[♻️重新运行](%s/retry?%s) ", hostSchema, query));
            sb.append(String.format("[⛔删除](%s/delete?%s) %n%n", hostSchema, query));
        }
        message.setMessage(sb.toString());
        return message;
    }
}
