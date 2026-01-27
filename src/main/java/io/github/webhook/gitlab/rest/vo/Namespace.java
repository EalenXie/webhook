package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Namespace {

    /**
     * 命名空间 ID
     */
    private Long id;

    /**
     * 命名空间名称
     */
    private String name;

    /**
     * 命名空间 path
     */
    private String path;

    /**
     * 类型：group / user
     */
    private String kind;

    /**
     * 完整 path
     */
    @JsonProperty("full_path")
    private String fullPath;

    /**
     * 父级 ID
     */
    @JsonProperty("parent_id")
    private Long parentId;

    /**
     * 头像地址
     */
    @JsonProperty("avatar_url")
    private String avatarUrl;

    /**
     * Web 页面地址
     */
    @JsonProperty("web_url")
    private String webUrl;
}
