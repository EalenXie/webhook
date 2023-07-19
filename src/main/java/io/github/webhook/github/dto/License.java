package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@NoArgsConstructor
@Data
public class License {
    /**
     * key
     */
    @JsonProperty("key")
    private String key;
    /**
     * name
     */
    @JsonProperty("name")
    private String name;
    /**
     * spdxId
     */
    @JsonProperty("spdx_id")
    private String spdxId;
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
    /**
     * nodeId
     */
    @JsonProperty("node_id")
    private String nodeId;
}
