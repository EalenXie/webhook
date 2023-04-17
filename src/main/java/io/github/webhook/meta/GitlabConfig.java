package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/7/15 22:46
 */
@Getter
@Setter
public class GitlabConfig {
    private String host;
    private String privateToken;
}
