package io.github.webhook.gitlab.webhook.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.webhook.Commit;
import io.github.webhook.gitlab.webhook.Project;
import io.github.webhook.gitlab.webhook.Repository;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2021/12/1 11:21
 */
@Getter
@Setter
public class PushHook {
    @JsonProperty("object_kind")
    private String objectKind;
    @JsonProperty("event_name")
    private String eventName;
    private String before;
    private String after;
    private String ref;
    @JsonProperty("checkout_sha")
    private String checkoutSha;
    private String message;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_username")
    private String userUsername;
    @JsonProperty("user_email")
    private String userEmail;
    @JsonProperty("user_avatar")
    private String userAvatar;
    @JsonProperty("project_id")
    private Long projectId;
    private Project project;
    private List<Commit> commits;
    @JsonProperty("total_commits_count")
    private Integer totalCommitsCount;
    private Repository repository;
}
