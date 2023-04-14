package io.github.webhook.gitlab.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/1 9:31
 */
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String username;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String email;
}
