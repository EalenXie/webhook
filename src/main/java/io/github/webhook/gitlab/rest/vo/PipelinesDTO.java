package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/3/23 13:34
 */
@Getter
@Setter
public class PipelinesDTO {
    /**
     * The scope of pipelines, one of: running, pending, finished, branches, tags
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String scope;
    /**
     * created, waiting_for_resource, preparing, pending, running, success, failed, canceled, skipped, manual, scheduled
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ref;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sha;
    @JsonProperty("yaml_errors")
    private String yamlErrors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("updated_after")
    private String updatedAfter;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("updated_before")
    private String updatedBefore;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("order_by")
    private String orderBy;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sort;

}
