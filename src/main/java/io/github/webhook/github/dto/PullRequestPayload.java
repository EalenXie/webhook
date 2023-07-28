package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/28 13:27
 */
@Getter
@Setter
public class PullRequestPayload {
    /**
     * action
     */
    @JsonProperty("action")
    private String action;
    /**
     * number
     */
    @JsonProperty("number")
    private Integer number;
    /**
     * pullRequest
     */
    @JsonProperty("pull_request")
    private PullRequest pullRequest;
    /**
     * repository
     */
    @JsonProperty("repository")
    private Repository repository;
    /**
     * sender
     */
    @JsonProperty("sender")
    private User sender;
}
