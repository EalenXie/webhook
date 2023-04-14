package io.github.webhook.gitlab.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/1 11:35
 */
@Getter
@Setter
public class Repository {
    private String name;
    private String url;
    private String homepage;
    private String description;
    @JsonProperty("git_ssh_url")
    private String gitSshUrl;
    @JsonProperty("git_http_url")
    private String gitHttpUrl;
    @JsonProperty("visibility_level")
    private Integer visibilityLevel;
}
