package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/28 13:27
 */
@Getter
@Setter
public class BranchRef {
    /**
     * label
     */
    @JsonProperty("label")
    private String label;
    /**
     * ref
     */
    @JsonProperty("ref")
    private String ref;
    /**
     * sha
     */
    @JsonProperty("sha")
    private String sha;
    /**
     * user
     */
    @JsonProperty("user")
    private User user;
    /**
     * repo
     */
    @JsonProperty("repo")
    private Repo repo;
}
