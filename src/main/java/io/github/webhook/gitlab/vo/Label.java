package io.github.webhook.gitlab.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Label {
    private Long id;
    private String title;
    private String color;
    @JsonProperty("project_id")
    private Long projectId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private Boolean template;
    private String description;
    private String type;
    @JsonProperty("group_id")
    private Integer groupId;
}