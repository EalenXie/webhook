package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@Getter
@Setter
public class Author {
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
    /**
     * username
     */
    @JsonProperty("username")
    private String username;
}
