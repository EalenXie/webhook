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
public class Repo {
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
     * name
     */
    @JsonProperty("name")
    private String name;
    /**
     * fullName
     */
    @JsonProperty("full_name")
    private String fullName;
    /**
     * privateX
     */
    @JsonProperty("private")
    private Boolean privateX;
    /**
     * owner
     */
    @JsonProperty("owner")
    private User owner;
    /**
     * htmlUrl
     */
    @JsonProperty("html_url")
    private String htmlUrl;
    /**
     * description
     */
    @JsonProperty("description")
    private String description;
    /**
     * fork
     */
    @JsonProperty("fork")
    private Boolean fork;
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
    /**
     * forksUrl
     */
    @JsonProperty("forks_url")
    private String forksUrl;
    /**
     * keysUrl
     */
    @JsonProperty("keys_url")
    private String keysUrl;
    /**
     * collaboratorsUrl
     */
    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;
    /**
     * teamsUrl
     */
    @JsonProperty("teams_url")
    private String teamsUrl;
    /**
     * hooksUrl
     */
    @JsonProperty("hooks_url")
    private String hooksUrl;
    /**
     * issueEventsUrl
     */
    @JsonProperty("issue_events_url")
    private String issueEventsUrl;
    /**
     * eventsUrl
     */
    @JsonProperty("events_url")
    private String eventsUrl;
    /**
     * assigneesUrl
     */
    @JsonProperty("assignees_url")
    private String assigneesUrl;
    /**
     * branchesUrl
     */
    @JsonProperty("branches_url")
    private String branchesUrl;
    /**
     * tagsUrl
     */
    @JsonProperty("tags_url")
    private String tagsUrl;
    /**
     * blobsUrl
     */
    @JsonProperty("blobs_url")
    private String blobsUrl;
    /**
     * gitTagsUrl
     */
    @JsonProperty("git_tags_url")
    private String gitTagsUrl;
    /**
     * gitRefsUrl
     */
    @JsonProperty("git_refs_url")
    private String gitRefsUrl;
    /**
     * treesUrl
     */
    @JsonProperty("trees_url")
    private String treesUrl;
    /**
     * statusesUrl
     */
    @JsonProperty("statuses_url")
    private String statusesUrl;
    /**
     * languagesUrl
     */
    @JsonProperty("languages_url")
    private String languagesUrl;
    /**
     * stargazersUrl
     */
    @JsonProperty("stargazers_url")
    private String stargazersUrl;
    /**
     * contributorsUrl
     */
    @JsonProperty("contributors_url")
    private String contributorsUrl;
    /**
     * subscribersUrl
     */
    @JsonProperty("subscribers_url")
    private String subscribersUrl;
    /**
     * subscriptionUrl
     */
    @JsonProperty("subscription_url")
    private String subscriptionUrl;
    /**
     * commitsUrl
     */
    @JsonProperty("commits_url")
    private String commitsUrl;
    /**
     * gitCommitsUrl
     */
    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;
    /**
     * commentsUrl
     */
    @JsonProperty("comments_url")
    private String commentsUrl;
    /**
     * issueCommentUrl
     */
    @JsonProperty("issue_comment_url")
    private String issueCommentUrl;
    /**
     * contentsUrl
     */
    @JsonProperty("contents_url")
    private String contentsUrl;
    /**
     * compareUrl
     */
    @JsonProperty("compare_url")
    private String compareUrl;
    /**
     * mergesUrl
     */
    @JsonProperty("merges_url")
    private String mergesUrl;
    /**
     * archiveUrl
     */
    @JsonProperty("archive_url")
    private String archiveUrl;
    /**
     * downloadsUrl
     */
    @JsonProperty("downloads_url")
    private String downloadsUrl;
    /**
     * issuesUrl
     */
    @JsonProperty("issues_url")
    private String issuesUrl;
    /**
     * pullsUrl
     */
    @JsonProperty("pulls_url")
    private String pullsUrl;
    /**
     * milestonesUrl
     */
    @JsonProperty("milestones_url")
    private String milestonesUrl;
    /**
     * notificationsUrl
     */
    @JsonProperty("notifications_url")
    private String notificationsUrl;
    /**
     * labelsUrl
     */
    @JsonProperty("labels_url")
    private String labelsUrl;
    /**
     * releasesUrl
     */
    @JsonProperty("releases_url")
    private String releasesUrl;
    /**
     * deploymentsUrl
     */
    @JsonProperty("deployments_url")
    private String deploymentsUrl;
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
     * pushedAt
     */
    @JsonProperty("pushed_at")
    private String pushedAt;
    /**
     * gitUrl
     */
    @JsonProperty("git_url")
    private String gitUrl;
    /**
     * sshUrl
     */
    @JsonProperty("ssh_url")
    private String sshUrl;
    /**
     * cloneUrl
     */
    @JsonProperty("clone_url")
    private String cloneUrl;
    /**
     * svnUrl
     */
    @JsonProperty("svn_url")
    private String svnUrl;
    /**
     * homepage
     */
    @JsonProperty("homepage")
    private Object homepage;
    /**
     * size
     */
    @JsonProperty("size")
    private Integer size;
    /**
     * stargazersCount
     */
    @JsonProperty("stargazers_count")
    private Integer stargazersCount;
    /**
     * watchersCount
     */
    @JsonProperty("watchers_count")
    private Integer watchersCount;
    /**
     * language
     */
    @JsonProperty("language")
    private String language;
    /**
     * hasIssues
     */
    @JsonProperty("has_issues")
    private Boolean hasIssues;
    /**
     * hasProjects
     */
    @JsonProperty("has_projects")
    private Boolean hasProjects;
    /**
     * hasDownloads
     */
    @JsonProperty("has_downloads")
    private Boolean hasDownloads;
    /**
     * hasWiki
     */
    @JsonProperty("has_wiki")
    private Boolean hasWiki;
    /**
     * hasPages
     */
    @JsonProperty("has_pages")
    private Boolean hasPages;
    /**
     * hasDiscussions
     */
    @JsonProperty("has_discussions")
    private Boolean hasDiscussions;
    /**
     * forksCount
     */
    @JsonProperty("forks_count")
    private Integer forksCount;
    /**
     * mirrorUrl
     */
    @JsonProperty("mirror_url")
    private Object mirrorUrl;
    /**
     * archived
     */
    @JsonProperty("archived")
    private Boolean archived;
    /**
     * disabled
     */
    @JsonProperty("disabled")
    private Boolean disabled;
    /**
     * openIssuesCount
     */
    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;
    /**
     * license
     */
    @JsonProperty("license")
    private License license;
    /**
     * allowForking
     */
    @JsonProperty("allow_forking")
    private Boolean allowForking;
    /**
     * isTemplate
     */
    @JsonProperty("is_template")
    private Boolean isTemplate;
    /**
     * webCommitSignoffRequired
     */
    @JsonProperty("web_commit_signoff_required")
    private Boolean webCommitSignoffRequired;
    /**
     * topics
     */
    @JsonProperty("topics")
    private List<?> topics;
    /**
     * visibility
     */
    @JsonProperty("visibility")
    private String visibility;
    /**
     * forks
     */
    @JsonProperty("forks")
    private Integer forks;
    /**
     * openIssues
     */
    @JsonProperty("open_issues")
    private Integer openIssues;
    /**
     * watchers
     */
    @JsonProperty("watchers")
    private Integer watchers;
    /**
     * defaultBranch
     */
    @JsonProperty("default_branch")
    private String defaultBranch;
    /**
     * allowSquashMerge
     */
    @JsonProperty("allow_squash_merge")
    private Boolean allowSquashMerge;
    /**
     * allowMergeCommit
     */
    @JsonProperty("allow_merge_commit")
    private Boolean allowMergeCommit;
    /**
     * allowRebaseMerge
     */
    @JsonProperty("allow_rebase_merge")
    private Boolean allowRebaseMerge;
    /**
     * allowAutoMerge
     */
    @JsonProperty("allow_auto_merge")
    private Boolean allowAutoMerge;
    /**
     * deleteBranchOnMerge
     */
    @JsonProperty("delete_branch_on_merge")
    private Boolean deleteBranchOnMerge;
    /**
     * allowUpdateBranch
     */
    @JsonProperty("allow_update_branch")
    private Boolean allowUpdateBranch;
    /**
     * useSquashPrTitleAsDefault
     */
    @JsonProperty("use_squash_pr_title_as_default")
    private Boolean useSquashPrTitleAsDefault;
    /**
     * squashMergeCommitMessage
     */
    @JsonProperty("squash_merge_commit_message")
    private String squashMergeCommitMessage;
    /**
     * squashMergeCommitTitle
     */
    @JsonProperty("squash_merge_commit_title")
    private String squashMergeCommitTitle;
    /**
     * mergeCommitMessage
     */
    @JsonProperty("merge_commit_message")
    private String mergeCommitMessage;
    /**
     * mergeCommitTitle
     */
    @JsonProperty("merge_commit_title")
    private String mergeCommitTitle;











}
