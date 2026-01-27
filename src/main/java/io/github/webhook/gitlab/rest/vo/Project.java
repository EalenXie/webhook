package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class Project {

    /**
     * 项目 ID
     */
    private String id;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 带命名空间的名称
     */
    @JsonProperty("name_with_namespace")
    private String nameWithNamespace;

    /**
     * 项目 path
     */
    private String path;

    /**
     * 带命名空间的 path
     */
    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    /**
     * 默认分支
     */
    @JsonProperty("default_branch")
    private String defaultBranch;

    /**
     * 标签列表
     */
    @JsonProperty("tag_list")
    private List<String> tagList;

    /**
     * SSH 克隆地址
     */
    @JsonProperty("ssh_url_to_repo")
    private String sshUrlToRepo;

    /**
     * HTTP 克隆地址
     */
    @JsonProperty("http_url_to_repo")
    private String httpUrlToRepo;

    /**
     * Web 页面地址
     */
    @JsonProperty("web_url")
    private String webUrl;

    /**
     * README 地址
     */
    @JsonProperty("readme_url")
    private String readmeUrl;

    /**
     * 头像地址
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * Fork 数
     */
    @JsonProperty("forks_count")
    private Integer forksCount;

    /**
     * Star 数
     */
    @JsonProperty("star_count")
    private Integer starCount;

    /**
     * 最近活跃时间
     */
    @JsonProperty("last_activity_at")
    private OffsetDateTime lastActivityAt;
    /**
     * 命名空间
     */
    private Namespace namespace;
}
