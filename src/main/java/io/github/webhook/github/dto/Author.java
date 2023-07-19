package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@NoArgsConstructor
@Data
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
