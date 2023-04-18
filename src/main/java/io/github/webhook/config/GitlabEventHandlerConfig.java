package io.github.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.webhook.gitlab.GitlabWebhookHandler;
import io.github.webhook.gitlab.event.GitlabEventFactory;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.gitlab.rest.GitlabRestClientFactory;
import io.github.webhook.notify.NotifierFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * @author EalenXie created on 2023/4/17 13:24
 * Gitlab 事件配置
 */
@Configuration
public class GitlabEventHandlerConfig {
    @Bean
    public GitlabEventFactory gitlabEventFactory() {
        return new GitlabEventFactory();
    }

    @Bean
    public GitlabRestClientFactory gitlabRestClientFactory(ObjectMapper objectMapper, RestOperations httpClientRestTemplate) {
        return new GitlabRestClientFactory(objectMapper, httpClientRestTemplate);
    }

    @Bean
    public GitlabWebhookHandler gitlabWebhookHandler(GitlabEventFactory gitlabEventFactory, ObjectMapper objectMapper) {
        return new GitlabWebhookHandler(gitlabEventFactory, objectMapper);
    }

    @Bean
    public PushHookNotifyEventHandler pushHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new PushHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public IssueHookNotifyEventHandler issueHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new IssueHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public MergeRequestHookNotifyEventHandler mergeRequestHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new MergeRequestHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public JobHookNotifyEventHandler jobHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new JobHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public NoteHookNotifyEventHandler noteHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new NoteHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public ReleaseHookNotifyEventHandler releaseHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new ReleaseHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public TagPushHookNotifyEventHandler tagPushHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new TagPushHookNotifyEventHandler(notifierFactory);
    }

    @Bean
    public PipelineHookNotifyEventHandler pipelineHookNotifyEventHandler(NotifierFactory notifierFactory) {
        return new PipelineHookNotifyEventHandler(notifierFactory);
    }
}
