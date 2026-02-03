package io.github.webhook.gitlab.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.config.meta.GitlabConf;
import io.github.webhook.config.meta.Webhook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestOperations;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/18 15:06
 */
public class GitlabRestClientFactory {
    private final Map<String, GitlabRestClient> gitlabRestClients = new HashMap<>();
    private final ObjectMapper objectMapper;
    private final RestOperations restOperations;

    public GitlabRestClientFactory(ObjectMapper objectMapper, RestOperations restOperations) {
        this.objectMapper = objectMapper;
        this.restOperations = restOperations;
    }

    public GitlabRestClient getGitlabRestClient(Webhook webhook) {
        GitlabRestClient gitlabRestClient = gitlabRestClients.get(webhook.getId());
        if (gitlabRestClient == null) {
            Webhook.Conf conf = webhook.getConf();
            if (!ObjectUtils.isEmpty(conf)) {
                GitlabConf gitlab = conf.getGitlab();
                if (!ObjectUtils.isEmpty(gitlab)) {
                    String host = gitlab.getHost();
                    if (host.endsWith("/")) {
                        host = host.substring(0, host.lastIndexOf("/"));
                    }
                    String privateToken = gitlab.getPrivateToken();
                    if (!ObjectUtils.isEmpty(host) && !ObjectUtils.isEmpty(privateToken)) {
                        gitlabRestClient = new GitlabRestClient(host, privateToken, objectMapper, restOperations);
                        gitlabRestClients.put(webhook.getId(), gitlabRestClient);
                    }
                }
            }
        }
        return gitlabRestClient;
    }
}
