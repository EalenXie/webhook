package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/5/27 14:35
 */
@Getter
@Setter
public class CancelPipeline {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("sha")
    private String sha;
    @JsonProperty("before_sha")
    private String beforeSha;
    @JsonProperty("tag")
    private Boolean tag;
    @JsonProperty("yaml_errors")
    private Object yamlErrors;
    @JsonProperty("user")
    private User user;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("started_at")
    private Object startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    @JsonProperty("committed_at")
    private Object committedAt;
    @JsonProperty("duration")
    private Object duration;
    @JsonProperty("coverage")
    private Object coverage;
    @JsonProperty("web_url")
    private String webUrl;
}
