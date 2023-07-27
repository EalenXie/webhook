package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/27 10:14
 */
@Getter
@Setter
public class StarPayload {
    /**
     * action
     */
    @JsonProperty("action")
    private String action;
    /**
     * starredAt
     */
    @JsonProperty("starred_at")
    private String starredAt;
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
