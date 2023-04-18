package io.github.webhook.gitlab.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by EalenXie on 2021/12/10 10:43
 */
@Getter
@Setter
public class Changes {

    @JsonProperty("merge_status")
    private Map<String, Object> mergeStatus;
    private Map<String, Object> confidential;
    @JsonProperty("updated_at")
    private Map<String, Object> updatedAt;


}
