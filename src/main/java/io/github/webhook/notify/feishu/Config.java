package io.github.webhook.notify.feishu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Config {
    @JsonProperty("wide_screen_mode")
    private Boolean wideScreenMode;
    @JsonProperty("enable_forward")
    private Boolean enableForward;
}