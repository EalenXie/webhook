package io.github.webhook.gitlab.vo.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.vo.Issue;
import io.github.webhook.gitlab.vo.Project;
import io.github.webhook.gitlab.vo.Repository;
import io.github.webhook.gitlab.vo.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/1/21 15:51
 */
@Setter
@Getter
public class NoteHook {

    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_type")
    private String eventType;
    private User user;
    @JsonProperty("project_id")
    private Long projectId;
    private Project project;
    @JsonProperty("object_attributes")
    private ObjectAttributes objectAttributes;
    private Repository repository;
    private Issue issue;


    @Setter
    @Getter
    public static class ObjectAttributes {
        private String attachment;
        @JsonProperty("author_id")
        private Long authorId;
        @JsonProperty("change_position")
        private String changePosition;
        @JsonProperty("commit_id")
        private String commitId;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("discussion_id")
        private String discussionId;
        private Long id;
        @JsonProperty("line_code")
        private String lineCode;
        private String note;
        @JsonProperty("noteable_id")
        private Long noteAbleId;
        @JsonProperty("noteable_type")
        private String noteAbleType;
        @JsonProperty("original_position")
        private String originalPosition;
        private String position;
        private Long projectId;
        @JsonProperty("resolved_at")
        private String resolvedAt;
        @JsonProperty("resolved_by_id")
        private String resolvedById;
        @JsonProperty("resolved_by_push")
        private String resolvedByPush;
        @JsonProperty("st_diff")
        private String stDiff;
        private Boolean system;
        private String type;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("updated_by_id")
        private Long updatedById;
        private String description;
        private String url;
    }
}
