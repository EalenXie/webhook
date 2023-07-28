package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/7/28 13:27
 */
@Getter
@Setter
public class HrefPayload {
    /**
     * href
     */
    @JsonProperty("href")
    private String href;
}
