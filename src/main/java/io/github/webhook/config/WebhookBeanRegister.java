package io.github.webhook.config;


import io.github.webhook.github.event.message.ForkMessageGenerator;
import io.github.webhook.github.event.message.PullRequestMessageGenerator;
import io.github.webhook.github.event.message.PushMessageGenerator;
import io.github.webhook.github.event.message.StarMessageGenerator;
import io.github.webhook.github.event.notify.ForkNotifyEventHandler;
import io.github.webhook.github.event.notify.PullRequestNotifyEventHandler;
import io.github.webhook.github.event.notify.PushNotifyEventHandler;
import io.github.webhook.github.event.notify.StarNotifyEventHandler;
import io.github.webhook.gitlab.event.notify.*;
import io.github.webhook.gitlab.message.*;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.springframework.web.client.RestOperations;

import static io.github.webhook.config.SpringEnvHelper.*;

/**
 * Webhook Bean 注册器 （注册基础Bean，包括事件处理器，消息生成器，通知器等等）
 */
public class WebhookBeanRegister {

    public WebhookBeanRegister(RestOperations httpClientRestTemplate) {
        // Github事件处理器 （通知型事件处理器，目前支持Push，Star，PullRequest，Fork）
        registerSingleton(new PushNotifyEventHandler(getOrRegisterBean(PushMessageGenerator.class)));
        registerSingleton(new StarNotifyEventHandler(getOrRegisterBean(StarMessageGenerator.class)));
        registerSingleton(new PullRequestNotifyEventHandler(getOrRegisterBean(PullRequestMessageGenerator.class)));
        registerSingleton(new ForkNotifyEventHandler(getOrRegisterBean(ForkMessageGenerator.class)));
        // Gitlab事件处理器注册（通知型事件处理器，目前支持Push，Issue，MergeRequest，）
        registerSingleton(new PushHookNotifyEventHandler(getOrRegisterBean(PushHookMessageGenerator.class)));
        registerSingleton(new IssueHookNotifyEventHandler(getOrRegisterBean(IssueHookMessageGenerator.class)));
        registerSingleton(new MergeRequestHookNotifyEventHandler(getOrRegisterBean(MergeRequestHookMessageGenerator.class)));
        registerSingleton(new JobHookNotifyEventHandler(getOrRegisterBean(JobHookMessageGenerator.class)));
        registerSingleton(new NoteHookNotifyEventHandler(getOrRegisterBean(NoteHookMessageGenerator.class)));
        registerSingleton(new ReleaseHookNotifyEventHandler(getOrRegisterBean(ReleaseHookMessageGenerator.class)));
        registerSingleton(new TagPushHookNotifyEventHandler(getOrRegisterBean(TagPushHookMessageGenerator.class)));
        registerSingleton(new PipelineHookNotifyEventHandler(getOrRegisterBean(new PipelineHookMessageGenerator(getBean(WebhookConfig.class)))));
        // 通知器注册（目前支持 钉钉，飞书，企业微信）
        registerSingleton(new DingTalkNotifier(httpClientRestTemplate));
        registerSingleton(new CorpWechatNotifier(httpClientRestTemplate));
        registerSingleton(new FeiShuNotifier(httpClientRestTemplate));
    }


}
