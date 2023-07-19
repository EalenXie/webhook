package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/19 16:33
 */
@Getter
@Setter
public class Pusher {
    /**
     * name
     */
    @JsonProperty("name")
    private String name;
    /**
     * email
     */
    @JsonProperty("email")
    private String email;
}
