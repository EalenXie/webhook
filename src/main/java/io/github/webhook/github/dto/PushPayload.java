package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@Getter
@Setter
public class PushPayload {
    /**
     * ref
     */
    @JsonProperty("ref")
    private String ref;
    /**
     * before
     */
    @JsonProperty("before")
    private String before;
    /**
     * after
     */
    @JsonProperty("after")
    private String after;
    /**
     * repository
     */
    @JsonProperty("repository")
    private Repository repository;
    /**
     * pusher
     */
    @JsonProperty("pusher")
    private Pusher pusher;
    /**
     * sender
     */
    @JsonProperty("sender")
    private User sender;
    /**
     * created
     */
    @JsonProperty("created")
    private Boolean created;
    /**
     * deleted
     */
    @JsonProperty("deleted")
    private Boolean deleted;
    /**
     * forced
     */
    @JsonProperty("forced")
    private Boolean forced;
    /**
     * baseRef
     */
    @JsonProperty("base_ref")
    private String baseRef;
    /**
     * compare
     */
    @JsonProperty("compare")
    private String compare;
    /**
     * commits
     */
    @JsonProperty("commits")
    private List<Commit> commits;
    /**
     * headCommit
     */
    @JsonProperty("head_commit")
    private HeadCommit headCommit;
}
