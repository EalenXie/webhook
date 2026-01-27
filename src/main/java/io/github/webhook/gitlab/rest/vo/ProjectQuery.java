package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectQuery {
    /**
     * 是否归档
     */
    private Boolean archived;
    /**
     * 搜索项目（按 name / path / path_with_namespace）
     */
    private String search;

    @JsonProperty("id_after")
    private Long idAfter;
    /**
     * 返回 id 小于该值的项目（较少用）
     */
    @JsonProperty("id_before")
    private Long idBefore;
    /**
     * 只返回当前用户是成员的项目
     */
    private Boolean membership;

    /**
     * 只返回当前用户拥有的项目
     */
    private Boolean owned;
    /**
     * 是否包含子组的项目
     */
    @JsonProperty("include_subgroups")
    private Boolean includeSubgroups;
    /**
     * 可见性：public / internal / private
     */
    private String visibility;
    /**
     * 排序字段：id / name / path / created_at / updated_at / last_activity_at
     */
    @JsonProperty("order_by")
    private String orderBy;
    /**
     * 排序方式：asc / desc
     */
    private String sort;
    /**
     * 是否使用 simple 模式（返回字段更少）
     */
    private Boolean simple;
    /**
     * 返回统计信息（star、fork 等）
     */
    private Boolean statistics;
    /**
     * 是否只返回 starred 项目
     */
    private Boolean starred;
    /**
     * 是否返回自定义属性（较新版本 GitLab）
     */
    @JsonProperty("with_custom_attributes")
    private Boolean withCustomAttributes;
}
