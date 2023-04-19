package io.github.webhook.gitlab;

import io.github.webhook.core.WebhookRepository;
import io.github.webhook.gitlab.dto.PipelineDTO;
import io.github.webhook.gitlab.rest.GitlabRestClient;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.gitlab.rest.vo.CancelPipeline;
import io.github.webhook.meta.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * @author EalenXie created on 2023/4/18 14:54
 */
@Slf4j
@Controller
public class GitlabEndpoint {
    @Resource
    private WebhookRepository webhookRepository;

    public static final String ENDPOINT_URL = "/actuator/webhook";
    /**
     * 默认的404页面 😄
     */
    public static final String NOT_FOUND_URL = "https://github.com/EalenXie/webhook";
    @Resource
    private GitlabRestClientFactory gitlabRestClientFactory;

    private static final Pattern PATTERN = Pattern.compile("^cancel$|^delete$|^retry$");

    @GetMapping(ENDPOINT_URL + "/{webhookId}/gitlab/pipeline/{action}")
    public String pipelineAction(@PathVariable String webhookId, @PathVariable String action, PipelineDTO dto) {
        Webhook webhook = webhookRepository.findById(webhookId);
        if (webhook != null && PATTERN.matcher(action).find()) {
            GitlabRestClient gitlabRestClient = gitlabRestClientFactory.getGitlabRestClient(webhook);
            if (gitlabRestClient != null) {
                try {
                    CancelPipeline cancelResp = gitlabRestClient.cancelPipeline(dto.getProjectId(), dto.getPipelineId());
                    String webUrl = cancelResp.getWebUrl();
                    if (action.equals("retry")) {
                        CancelPipeline retryResp = gitlabRestClient.retryPipeline(dto.getProjectId(), dto.getPipelineId());
                        webUrl = retryResp.getWebUrl();
                    } else if (action.equals("delete")) {
                        gitlabRestClient.deletePipeline(dto.getProjectId(), dto.getPipelineId());
                        webUrl = webUrl.substring(0, webUrl.lastIndexOf("/"));
                    }
                    return String.format("redirect:%s", webUrl);
                } catch (Exception e) {
                    log.warn(e.getMessage());
                    // ig
                }
            }
        }
        return String.format("redirect:%s", NOT_FOUND_URL);
    }
}
