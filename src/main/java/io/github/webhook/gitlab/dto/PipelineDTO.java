package io.github.webhook.gitlab.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/4/18 14:59
 */
@Getter
@Setter
public class PipelineDTO {
    private Long projectId;
    private Long pipelineId;

}
