package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/3/23 13:22
 */
@Getter
@Setter
public class EraseJob {

    @JsonProperty("commit")
    private Commit commit;
    @JsonProperty("coverage")
    private Object coverage;
    @JsonProperty("allow_failure")
    private Boolean allowFailure;
    @JsonProperty("download_url")
    private Object downloadUrl;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("artifacts")
    private List<?> artifacts;
    @JsonProperty("runner")
    private Object runner;
    @JsonProperty("stage")
    private String stage;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    @JsonProperty("duration")
    private Double duration;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tag")
    private Boolean tag;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("user")
    private Object user;

    @NoArgsConstructor
    @Data
    public static class Commit {
        @JsonProperty("author_email")
        private String authorEmail;
        @JsonProperty("author_name")
        private String authorName;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("id")
        private String id;
        @JsonProperty("message")
        private String message;
        @JsonProperty("short_id")
        private String shortId;
        @JsonProperty("title")
        private String title;
    }
}
