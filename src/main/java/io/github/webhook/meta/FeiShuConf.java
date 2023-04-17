package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@Getter
@Setter
@ConfigurationPropertiesBinding
public class FeiShuConf {
    private String url;
    private String signKey;

}
