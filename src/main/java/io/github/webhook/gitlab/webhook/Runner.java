package io.github.webhook.gitlab.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Runner {
    private Long id;
    private String description;
    @JsonProperty("runner_type")
    private String runnerType;
    private Boolean active;
    @JsonProperty("is_shared")
    private Boolean isShared;
    private String[] tags;
}