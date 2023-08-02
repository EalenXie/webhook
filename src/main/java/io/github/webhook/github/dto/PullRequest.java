package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author EalenXie created on 2023/7/28 13:27
 */
@Getter
@Setter
public class PullRequest {
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
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
     * htmlUrl
     */
    @JsonProperty("html_url")
    private String htmlUrl;
    /**
     * diffUrl
     */
    @JsonProperty("diff_url")
    private String diffUrl;
    /**
     * patchUrl
     */
    @JsonProperty("patch_url")
    private String patchUrl;
    /**
     * issueUrl
     */
    @JsonProperty("issue_url")
    private String issueUrl;
    /**
     * number
     */
    @JsonProperty("number")
    private Integer number;
    /**
     * state
     */
    @JsonProperty("state")
    private String state;
    /**
     * locked
     */
    @JsonProperty("locked")
    private Boolean locked;
    /**
     * title
     */
    @JsonProperty("title")
    private String title;
    /**
     * user
     */
    @JsonProperty("user")
    private User user;
    /**
     * body
     */
    @JsonProperty("body")
    private String body;
    /**
     * createdAt
     */
    @JsonProperty("created_at")
    private String createdAt;
    /**
     * updatedAt
     */
    @JsonProperty("updated_at")
    private String updatedAt;
    /**
     * closedAt
     */
    @JsonProperty("closed_at")
    private String closedAt;
    /**
     * mergedAt
     */
    @JsonProperty("merged_at")
    private String mergedAt;
    /**
     * mergeCommitSha
     */
    @JsonProperty("merge_commit_sha")
    private String mergeCommitSha;
    /**
     * assignee
     */
    @JsonProperty("assignee")
    private String assignee;
    /**
     * assignees
     */
    @JsonProperty("assignees")
    private List<?> assignees;
    /**
     * requestedReviewers
     */
    @JsonProperty("requested_reviewers")
    private List<?> requestedReviewers;
    /**
     * requestedTeams
     */
    @JsonProperty("requested_teams")
    private List<?> requestedTeams;
    /**
     * labels
     */
    @JsonProperty("labels")
    private List<?> labels;
    /**
     * milestone
     */
    @JsonProperty("milestone")
    private String milestone;
    /**
     * draft
     */
    @JsonProperty("draft")
    private Boolean draft;
    /**
     * commitsUrl
     */
    @JsonProperty("commits_url")
    private String commitsUrl;
    /**
     * reviewCommentsUrl
     */
    @JsonProperty("review_comments_url")
    private String reviewCommentsUrl;
    /**
     * reviewCommentUrl
     */
    @JsonProperty("review_comment_url")
    private String reviewCommentUrl;
    /**
     * commentsUrl
     */
    @JsonProperty("comments_url")
    private String commentsUrl;
    /**
     * statusesUrl
     */
    @JsonProperty("statuses_url")
    private String statusesUrl;
    /**
     * head
     */
    @JsonProperty("head")
    private BranchRef head;
    /**
     * base
     */
    @JsonProperty("base")
    private BranchRef base;
    /**
     * links
     */
    @JsonProperty("_links")
    private Links links;
    /**
     * authorAssociation
     */
    @JsonProperty("author_association")
    private String authorAssociation;
    /**
     * autoMerge
     */
    @JsonProperty("auto_merge")
    private String autoMerge;
    /**
     * activeLockReason
     */
    @JsonProperty("active_lock_reason")
    private String activeLockReason;
    /**
     * merged
     */
    @JsonProperty("merged")
    private Boolean merged;
    /**
     * mergeable
     */
    @JsonProperty("mergeable")
    private String mergeable;
    /**
     * rebaseable
     */
    @JsonProperty("rebaseable")
    private String rebaseable;
    /**
     * mergeableState
     */
    @JsonProperty("mergeable_state")
    private String mergeableState;
    /**
     * mergedBy
     */
    @JsonProperty("merged_by")
    private String mergedBy;
    /**
     * comments
     */
    @JsonProperty("comments")
    private Integer comments;
    /**
     * reviewComments
     */
    @JsonProperty("review_comments")
    private Integer reviewComments;
    /**
     * maintainerCanModify
     */
    @JsonProperty("maintainer_can_modify")
    private Boolean maintainerCanModify;
    /**
     * commits
     */
    @JsonProperty("commits")
    private Integer commits;
    /**
     * additions
     */
    @JsonProperty("additions")
    private Integer additions;
    /**
     * deletions
     */
    @JsonProperty("deletions")
    private Integer deletions;
    /**
     * changedFiles
     */
    @JsonProperty("changed_files")
    private Integer changedFiles;
}
