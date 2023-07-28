package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/28 17:14
 */
@Getter
@Setter
public class ForkPayload {

    /**
     * forkee
     */
    @JsonProperty("forkee")
    private Forkee forkee;
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
