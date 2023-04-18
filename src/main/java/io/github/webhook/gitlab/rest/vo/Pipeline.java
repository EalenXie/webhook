package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/3/23 13:27
 */
@Getter
@Setter
public class Pipeline {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("sha")
    private String sha;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("status")
    private String status;
    @JsonProperty("source")
    private String source;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("web_url")
    private String webUrl;
}
