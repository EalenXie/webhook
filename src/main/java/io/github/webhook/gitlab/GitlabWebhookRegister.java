package io.github.webhook.gitlab;


import io.github.webhook.gitlab.rest.GitlabRestClient;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.gitlab.rest.vo.HookAddPayload;
import io.github.webhook.gitlab.rest.vo.HookInfo;
import io.github.webhook.gitlab.rest.vo.Project;
import io.github.webhook.gitlab.rest.vo.ProjectQuery;
import io.github.webhook.meta.GitlabConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.meta.WebhookProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class GitlabWebhookRegister {


    private final WebhookProperties webhookProperties;

    private final GitlabRestClientFactory gitlabRestClientFactory;

    public GitlabWebhookRegister(WebhookProperties webhookProperties, GitlabRestClientFactory gitlabRestClientFactory) {
        this.gitlabRestClientFactory = gitlabRestClientFactory;
        this.webhookProperties = webhookProperties;
    }

    public List<String> register(Webhook webhook) {
        List<String> success = new ArrayList<>();
        List<String> gitlabProjectWebUrls = webhook.getGitlabProjectWebUrls();
        GitlabRestClient gitlabRestClient = gitlabRestClientFactory.getGitlabRestClient(webhook);
        for (String webUrl : gitlabProjectWebUrls) {
            try {
                ProjectQuery query = new ProjectQuery();
                query.setSimple(true);
                query.setSearchNamespaces(true);
                if (webUrl.endsWith(".git")) {
                    webUrl = webUrl.replace(".git", "");
                }
                String namespace = webUrl.replace(webhook.getGitlabHost(), "");
                if (namespace.startsWith("/")) {
                    namespace = namespace.substring(1);
                }
                query.setSearch(namespace);
                List<Project> projects = gitlabRestClient.getProjects(query);
                Project project = getProjectByWebUrl(webUrl, projects);
                if (project != null) {
                    String webhookUrl = webhookProperties.getWebhookUrl(webhook.getId());
                    if (getWebhookByUrl(webhookUrl, gitlabRestClient.listHooks(project.getId())) == null) {
                        HookAddPayload payload = new HookAddPayload();
                        payload.setId(project.getId());
                        payload.setUrl(webhookUrl);
                        GitlabConf.Trigger trigger = webhook.getGitlabTrigger();
                        payload.setPushEvents(trigger.getPush());
                        payload.setPipelineEvents(trigger.getPipeline());
                        payload.setJobEvents(trigger.getJob());
                        payload.setMergeRequestsEvents(trigger.getMergeRequest());
                        payload.setNoteEvents(trigger.getNote());
                        payload.setIssuesEvents(trigger.getIssue());
                        payload.setTagPushEvents(trigger.getTagPush());
                        payload.setReleasesEvents(trigger.getRelease());
                        gitlabRestClient.addHook(payload);
                    }
                    success.add(project.getWebUrl());
                } else {
                    log.error("register {} fail !!!, not found", webUrl);
                }
            } catch (Exception e) {
                log.error("register {} fail !!! :{}", webUrl, e.getMessage());
            }
        }
        return success;
    }

    private Project getProjectByWebUrl(String webUrl, List<Project> projects) {
        for (Project project : projects) {
            if (Objects.equals(project.getWebUrl(), webUrl)) {
                return project;
            }
        }
        return null;
    }

    private HookInfo getWebhookByUrl(String webhookUrl, List<HookInfo> hookInfos) {
        if (!ObjectUtils.isEmpty(hookInfos)) {
            for (HookInfo hookInfo : hookInfos) {
                if (Objects.equals(hookInfo.getUrl(), webhookUrl)) {
                    return hookInfo;
                }
            }
        }
        return null;
    }

}
