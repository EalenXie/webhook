package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * GitLab 添加项目钩子(Project Hook)的请求参数实体
 * 对应接口：POST /projects/:id/hooks
 */
@Getter
@Setter
public class PipelineEditPayload {

    /**
     * 项目ID或URL编码路径（必填）
     */
    @JsonProperty("id")
    private String id;
    /**
     * 钩子Id
     */
    @JsonProperty("hook_id")
    private String hookId;
    /**
     * 钩子接收URL（必填）
     */
    @JsonProperty("url")
    private String url;

    /**
     * 机密问题事件触发钩子
     */
    @JsonProperty("confidential_issues_events")
    private Boolean confidentialIssuesEvents;

    /**
     * 机密笔记事件触发钩子
     */
    @JsonProperty("confidential_note_events")
    private Boolean confidentialNoteEvents;

    /**
     * 部署事件触发钩子
     */
    @JsonProperty("deployment_events")
    private Boolean deploymentEvents;

    /**
     * 触发钩子时是否进行SSL验证
     */
    @JsonProperty("enable_ssl_verification")
    private Boolean enableSslVerification;

    /**
     * 问题事件触发钩子
     */
    @JsonProperty("issues_events")
    private Boolean issuesEvents;

    /**
     * 作业事件触发钩子
     */
    @JsonProperty("job_events")
    private Boolean jobEvents;

    /**
     * 合并请求事件触发钩子
     */
    @JsonProperty("merge_requests_events")
    private Boolean mergeRequestsEvents;

    /**
     * 笔记事件触发钩子
     */
    @JsonProperty("note_events")
    private Boolean noteEvents;

    /**
     * 流水线事件触发钩子
     */
    @JsonProperty("pipeline_events")
    private Boolean pipelineEvents;

    /**
     * 推送事件分支过滤（仅匹配的分支触发推送事件钩子）
     */
    @JsonProperty("push_events_branch_filter")
    private String pushEventsBranchFilter;

    /**
     * 推送事件触发钩子
     */
    @JsonProperty("push_events")
    private Boolean pushEvents;

    /**
     * 标签推送事件触发钩子
     */
    @JsonProperty("tag_push_events")
    private Boolean tagPushEvents;

    /**
     * 验证接收负载的秘密令牌（响应中不返回）
     */
    @JsonProperty("token")
    private String token;

    /**
     * Wiki页面事件触发钩子
     */
    @JsonProperty("wiki_page_events")
    private Boolean wikiPageEvents;
}