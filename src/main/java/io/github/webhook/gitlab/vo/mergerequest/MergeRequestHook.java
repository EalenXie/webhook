package io.github.webhook.gitlab.vo.mergerequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.vo.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by EalenXie on 2021/12/10 10:12
 */
@Getter
@Setter
public class MergeRequestHook {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private String[] labels;
    private Changes changes;
    private Repository repository;

    @Setter
    @Getter
    public static class ObjectAttributes {
        @JsonProperty("assignee_id")
        private Long assigneeId;
        @JsonProperty("author_id")
        private Long authorId;
        @JsonProperty("created_at")
        private String createdAt;
        private String description;
        @JsonProperty("head_pipeline_id")
        private Long headPipelineId;
        private Long id;
        private Long iid;
        @JsonProperty("last_edited_at")
        private String lastEditedAt;
        @JsonProperty("last_edited_by_id")
        private Long lastEditedById;
        @JsonProperty("merge_commit_sha")
        private String mergeCommitSha;
        @JsonProperty("merge_error")
        private String mergeError;
        @JsonProperty("merge_params")
        private Map<String, Object> mergeParams;
        @JsonProperty("merge_status")
        private String mergeStatus;
        @JsonProperty("merge_user_id")
        private Long mergeUserId;
        @JsonProperty("merge_when_pipeline_succeeds")
        private Boolean mergeWhenPipelineSucceeds;
        @JsonProperty("milestone_id")
        private Long milestoneId;
        @JsonProperty("source_branch")
        private String sourceBranch;
        @JsonProperty("source_project_id")
        private Long sourceProjectId;
        @JsonProperty("state_id")
        private Long stateId;
        @JsonProperty("target_branch")
        private String targetBranch;
        @JsonProperty("target_project_id")
        private String targetProjectId;
        @JsonProperty("time_estimate")
        private Integer timeEstimate;
        private String title;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("updated_by_id")
        private Long updatedById;
        private String url;
        private Project source;
        private Project target;
        @JsonProperty("last_commit")
        private Commit lastCommit;
        @JsonProperty("work_in_progress")
        private Boolean workInProgress;
        @JsonProperty("total_time_spent")
        private Integer totalTimeSpent;
        @JsonProperty("time_change")
        private Integer timeChange;
        @JsonProperty("human_total_time_spent")
        private Integer humanTotalTimeSpent;
        @JsonProperty("human_time_change")
        private Integer humanTimeChange;
        @JsonProperty("human_time_estimate")
        private Integer humanTimeEstimate;
        @JsonProperty("assignee_ids")
        private Integer[] assigneeIds;
        private String state;
        private String action;
    }
}
