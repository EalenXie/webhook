package io.github.webhook.gitlab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author EalenXie created on 2023/4/18 14:59
 */
@Getter
@Setter
public class PipelinePayload {
    @NotNull(message = "projectId不允许为null")
    private Long projectId;
    @NotNull(message = "pipelineId不允许为null")
    private Long pipelineId;
}
