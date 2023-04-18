package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/3/23 11:32
 */
@Getter
@Setter
public class Job {

    @JsonProperty("commit")
    private Commit commit;
    @JsonProperty("coverage")
    private Object coverage;
    @JsonProperty("allow_failure")
    private Boolean allowFailure;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    @JsonProperty("duration")
    private Double duration;
    @JsonProperty("artifacts_expire_at")
    private String artifactsExpireAt;
    @JsonProperty("tag_list")
    private List<String> tagList;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pipeline")
    private Pipeline pipeline;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("artifacts")
    private List<?> artifacts;
    @JsonProperty("runner")
    private Object runner;
    @JsonProperty("stage")
    private String stage;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tag")
    private Boolean tag;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("user")
    private User user;

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

    @NoArgsConstructor
    @Data
    public static class Pipeline {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("ref")
        private String ref;
        @JsonProperty("sha")
        private String sha;
        @JsonProperty("status")
        private String status;
    }

    @NoArgsConstructor
    @Data
    public static class User {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("username")
        private String username;
        @JsonProperty("state")
        private String state;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("web_url")
        private String webUrl;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("bio")
        private Object bio;
        @JsonProperty("location")
        private Object location;
        @JsonProperty("public_email")
        private String publicEmail;
        @JsonProperty("skype")
        private String skype;
        @JsonProperty("linkedin")
        private String linkedin;
        @JsonProperty("twitter")
        private String twitter;
        @JsonProperty("website_url")
        private String websiteUrl;
        @JsonProperty("organization")
        private String organization;
    }
}
