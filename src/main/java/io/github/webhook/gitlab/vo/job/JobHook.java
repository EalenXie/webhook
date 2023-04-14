package io.github.webhook.gitlab.vo.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.vo.Repository;
import io.github.webhook.gitlab.vo.Runner;
import io.github.webhook.gitlab.vo.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/1/24 13:14
 */
@Getter
@Setter
public class JobHook {
    @JsonProperty("object_kind")
    private String objectKind;
    private String ref;
    private Boolean tag;
    @JsonProperty("before_sha")
    private String beforeSha;
    private String sha;
    @JsonProperty("build_id")
    private Long buildId;
    @JsonProperty("build_name")
    private String buildName;
    @JsonProperty("build_stage")
    private String buildStage;
    @JsonProperty("build_status")
    private String buildStatus;
    @JsonProperty("build_created_at")
    private String buildCreatedAt;
    @JsonProperty("build_started_at")
    private String buildStartedAt;
    @JsonProperty("build_finished_at")
    private String buildFinishedAt;
    @JsonProperty("build_duration")
    private Double buildDuration;
    @JsonProperty("build_queued_duration")
    private Double buildQueuedDuration;
    @JsonProperty("build_allow_failure")
    private Boolean buildAllowFailure;
    @JsonProperty("build_failure_reason")
    private String buildFailureReason;
    @JsonProperty("pipeline_id")
    private Long pipelineId;
    private Runner runner;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("project_name")
    private String projectName;
    private User user;
    private Commit commit;
    private Repository repository;

    @Getter
    @Setter
    public static class Commit {
        private Integer id;
        private String sha;
        private String message;
        @JsonProperty("author_name")
        private String authorName;
        @JsonProperty("author_email")
        private String authorEmail;
        @JsonProperty("author_url")
        private String authorUrl;
        private String status;
        private Integer duration;
        @JsonProperty("started_at")
        private String startedAt;
        @JsonProperty("finished_at")
        private String finishedAt;
    }

}
