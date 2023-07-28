package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/28 13:27
 */
@Getter
@Setter
public class Links {
    /**
     * self
     */
    @JsonProperty("self")
    private HrefPayload self;
    /**
     * html
     */
    @JsonProperty("html")
    private HrefPayload html;
    /**
     * issue
     */
    @JsonProperty("issue")
    private HrefPayload issue;
    /**
     * comments
     */
    @JsonProperty("comments")
    private HrefPayload comments;
    /**
     * reviewComments
     */
    @JsonProperty("review_comments")
    private HrefPayload reviewComments;
    /**
     * reviewComment
     */
    @JsonProperty("review_comment")
    private HrefPayload reviewComment;
    /**
     * commits
     */
    @JsonProperty("commits")
    private HrefPayload commits;
    /**
     * statuses
     */
    @JsonProperty("statuses")
    private HrefPayload statuses;
}
