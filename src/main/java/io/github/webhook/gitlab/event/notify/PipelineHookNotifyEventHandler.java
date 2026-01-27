package io.github.webhook.gitlab.event.notify;

import io.github.webhook.config.FileConvert;
import io.github.webhook.gitlab.GitlabEndpoint;
import io.github.webhook.gitlab.event.PipelineEventHandler;
import io.github.webhook.gitlab.webhook.Build;
import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.pipeline.PipelineHook;
import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WebhookProperties;
import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.NotifyMessage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 12:53
 */
public class PipelineHookNotifyEventHandler extends GitlabNotifyEventHandler<PipelineHook> implements PipelineEventHandler {

    private final WebhookProperties webhookProperties;

    public PipelineHookNotifyEventHandler(WebhookProperties webhookProperties, NotifierFactory notifierFactory) {
        super(notifierFactory);
        this.webhookProperties = webhookProperties;
    }

    @Override
    protected boolean shouldNotify(Webhook webhook, PipelineHook data) {
        PipelineHook.ObjectAttributes objectAttributes = data.getObjectAttributes();
        return objectAttributes != null && !"pending".equals(objectAttributes.getStatus());
    }

    @SuppressWarnings("all")
    @Override
    public NotifyMessage generate(Webhook webhook, PipelineHook pipelineHook) {
        NotifyMessage message = new NotifyMessage();
        message.setTitle(pipelineHook.getObjectKind());
        message.setNotifies(Collections.singletonList(String.valueOf(pipelineHook.getUser().getId())));
        StringBuilder sb = new StringBuilder();
        PipelineHook.ObjectAttributes objectAttributes = pipelineHook.getObjectAttributes();
        Project project = pipelineHook.getProject();
        Commit commit = pipelineHook.getCommit();
        List<Build> builds = pipelineHook.getBuilds();
        String status = objectAttributes.getStatus();
        String pipeline = String.format("%s [#%s %s](%s/-/pipelines/%s)", pipelineHook.getObjectKind(), objectAttributes.getId(), "\uD83D\uDE80", project.getWebUrl(), objectAttributes.getId());
        sb.append(String.format("[[%s:%s]](%s/-/tree/%s) <font color='#000000'>%s %s</font>%n%n", project.getName(), objectAttributes.getRef(), project.getWebUrl(), objectAttributes.getRef(), pipeline, status));
        if (!"running".equals(status)) {
            int totalTime = 0;
            if (objectAttributes.getDuration() != null) {
                totalTime += objectAttributes.getDuration();
            }
            if (objectAttributes.getQueuedDuration() != null) {
                totalTime += objectAttributes.getQueuedDuration();
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
            sb.append(String.format("%s%s : <font color='%s'>%s</font> %s %ss%n%n", statusEmoji, pipeline, statusColor, objectAttributes.getDetailedStatus(), "\uD83D\uDD57", totalTime));
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
            Long pipelineId = objectAttributes.getId();
            String hostSchema = String.format("%s%s/%s/gitlab/pipeline", webhookProperties.getWebhookHost(), GitlabEndpoint.ENDPOINT_URL, webhook.getId());
            sb.append(String.format("[\uD83D\uDEAB取消运行](%s/cancel?projectId=%s&pipelineId=%s) ", hostSchema, projectId, pipelineId));
            sb.append(String.format("[♻️重新运行](%s/retry?projectId=%s&pipelineId=%s) ", hostSchema, projectId, pipelineId));
            sb.append(String.format("[⛔删除](%s/delete?projectId=%s&pipelineId=%s) %n%n", hostSchema, projectId, pipelineId));
        }
        message.setMessage(sb.toString());
        return message;
    }

}
