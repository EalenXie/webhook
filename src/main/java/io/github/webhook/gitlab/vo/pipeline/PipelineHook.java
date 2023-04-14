package io.github.webhook.gitlab.vo.pipeline;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.vo.Build;
import io.github.webhook.gitlab.vo.Commit;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/1 9:25
 */
@Getter
@Setter
public class PipelineHook {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    @JsonProperty("merge_request")
    private String mergeRequest;
    private User user;
    private Project project;
    private Commit commit;
    private List<Build> builds;

    @Getter
    @Setter
    public static class ObjectAttributes {
        private Long id;
        private String ref;
        private Boolean tag;
        private String sha;
        @JsonProperty("before_sha")
        private String beforeSha;
        private String source;
        private String status;
        @JsonProperty("detailed_status")
        private String detailedStatus;
        private String[] stages;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("finished_at")
        private String finishedAt;
        private Double duration;
        @JsonProperty("queued_duration")
        private Double queuedDuration;
        private String[] variables;
    }

}
