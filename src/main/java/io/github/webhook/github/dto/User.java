package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@Getter
@Setter
public class User {
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
     * login
     */
    @JsonProperty("login")
    private String login;
    /**
     * id
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * nodeId
     */
    @JsonProperty("node_id")
    private String nodeId;
    /**
     * avatarUrl
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;
    /**
     * gravatarId
     */
    @JsonProperty("gravatar_id")
    private String gravatarId;
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
    /**
     * htmlUrl
     */
    @JsonProperty("html_url")
    private String htmlUrl;
    /**
     * followersUrl
     */
    @JsonProperty("followers_url")
    private String followersUrl;
    /**
     * followingUrl
     */
    @JsonProperty("following_url")
    private String followingUrl;
    /**
     * gistsUrl
     */
    @JsonProperty("gists_url")
    private String gistsUrl;
    /**
     * starredUrl
     */
    @JsonProperty("starred_url")
    private String starredUrl;
    /**
     * subscriptionsUrl
     */
    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;
    /**
     * organizationsUrl
     */
    @JsonProperty("organizations_url")
    private String organizationsUrl;
    /**
     * reposUrl
     */
    @JsonProperty("repos_url")
    private String reposUrl;
    /**
     * eventsUrl
     */
    @JsonProperty("events_url")
    private String eventsUrl;
    /**
     * receivedEventsUrl
     */
    @JsonProperty("received_events_url")
    private String receivedEventsUrl;
    /**
     * type
     */
    @JsonProperty("type")
    private String type;
    /**
     * siteAdmin
     */
    @JsonProperty("site_admin")
    private Boolean siteAdmin;
}
