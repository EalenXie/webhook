package io.github.webhook.notify.feishu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/15 12:43
 */
@Getter
@Setter
public class Interactive {
    @JsonProperty("config")
    private Config config;
    @JsonProperty("elements")
    private List<Element> elements;
    @JsonProperty("header")
    private Header header;

}
