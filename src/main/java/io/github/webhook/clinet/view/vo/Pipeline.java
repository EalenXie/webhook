package io.github.webhook.clinet.view.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pipeline {
    private String projectUrl;
    private String status;
}
