package io.github.webhook.gitlab.webhook.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.webhook.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/16 14:15
 */
@Getter
@Setter
public class IssueHook {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private List<Label> labels;
    private Changes changes;
    private Repository repository;


    @Getter
    @Setter
    public static class ObjectAttributes {
        @JsonProperty("author_id")
        private Long authorId;
        @JsonProperty("closed_at")
        private String closedAt;
        private Boolean confidential;
        @JsonProperty("created_at")
        private String createdAt;
        private String description;
        @JsonProperty("discussion_locked")
        private Boolean discussionLocked;
        @JsonProperty("due_date")
        private String dueDate;
        private Long id;
        private Long iid;
        @JsonProperty("last_edited_at")
        private String lastEditedAt;
        @JsonProperty("last_edited_by_id")
        private Long lastEditedById;
        @JsonProperty("milestone_id")
        private Long milestoneId;
        @JsonProperty("moved_to_id")
        private Long movedToId;
        @JsonProperty("duplicated_to_id")
        private Long duplicatedToId;
        @JsonProperty("project_id")
        private Long projectId;
        @JsonProperty("relative_position")
        private Integer relativePosition;
        @JsonProperty("state_id")
        private Long stateId;
        @JsonProperty("time_estimate")
        private Integer timeEstimate;
        private String title;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("updated_by_id")
        private Long updatedById;
        private Double weight;
        private String url;
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
        @JsonProperty("assignee_id")
        private Long assigneeId;
        private String state;
        private String action;
        private String severity;
        private List<Label> labels;
    }

}


