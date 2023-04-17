package io.github.webhook.notify.feishu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlVal {
    @JsonProperty("url")
    private String url;
    @JsonProperty("android_url")
    private String androidUrl;
    @JsonProperty("ios_url")
    private String iosUrl;
    @JsonProperty("pc_url")
    private String pcUrl;
}