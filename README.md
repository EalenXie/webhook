Webhook
=========

这是使用`Java`语言`SpringBoot`框架开发的 webhook v3全新版本，支持多种类型的Webhook扩展开发，事件处理机制

#### 快速开始

##### 1. 配置项目webhook

例如,最简单的配置了一个gitlab 类型的webhook , 事件处理类型为通知,以下配置了钉钉机器人

```yaml

config:
  webhooks:
    - id: gitlab_project
      type: GITLAB
      notify:
        ding-talk:
          access-token: "93a937xxxxxxxxxxxxxxxxxxx872883c"
          sign-key: "SECcxxxxxxxxxxxxxxxxxxxxxxxx56bdd0"

```

##### 启动项目

可在控制台中查看到已经配置好的webhook的地址信息

```text

2023-06-13 11:10:11.282  INFO 5360 --- [           main] io.github.webhook.WebhookApplication     : Started WebhookApplication in 1.575 seconds (JVM running for 2.108)
2023-06-13 11:10:11.343  INFO 5360 --- [           main] i.g.w.core.WebhookCommandLineRunner      : Webhooks are successfully configured.
The following webhooks are available,Please fill in the following address in your system's webhook: 
The webhook[gitlab_project] type is:GITLAB,address: http://192.168.180.198:8787/webhook/actuator/gitlab_project

```

##### 在gitlab后台中填入上面配置好的webhook地址，勾选需要触发的webhook事件。

![](https://img2023.cnblogs.com/blog/994599/202306/994599-20230613112658948-2091790637.png)

##### 配置好与之对应的钉钉群机器人

![](https://img2023.cnblogs.com/blog/994599/202306/994599-20230613113119870-930852617.png)

#### 通知钉钉群机器人实现效果:

推送事件(Push Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5b40cf05991c4e09be7b1a6cc6878bc9~tplv-k3u1fbpfcp-watermark.image?)

议题事件(Issue Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0bd1d11e732e45e7bd99a2e0a5731bdc~tplv-k3u1fbpfcp-watermark.image?)

流水线事件(Pipeline Hook):

![image.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be50a07007fe493c83ecb7e0491625bb~tplv-k3u1fbpfcp-watermark.image?)

合并请求事件(Merge Request Hook):

![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/26ecf69c83b14f7ab53b3ecc974230e3~tplv-k3u1fbpfcp-watermark.image?)

目前webhook类型仅实现了Gitlab类型的基本开发,其它类型正在开发中,支持自定义扩展类型

| webhook类型  | 实现类                  | 完成状态 |
|:-----------|:---------------------|:-----|
| GITLAB     | GitlabWebhookHandler | ✔️   |
| GITHUB     | GithubWebhookHandler | ✔️   |
| JIRA       | 暂无                   | ❌    |
| CONFLUENCE | 暂无                   | ❌    |

目前Gitlab默认支持的事件处理器实现,均支持实现自定义扩展实现

| Gitlab事件类型              | 默认实现类                              | 有默认实现 |
|:------------------------|:-----------------------------------|:------|
| Push Hook               | PushHookNotifyEventHandler         | ✔️    |
| Pipeline Hook           | PipelineHookNotifyEventHandler     | ️✔️   |
| Merge Request Hook      | MergeRequestHookNotifyEventHandler | ✔️    |
| Tag Push Hook           | TagPushHookNotifyEventHandler      | ✔️    |
| Issue Hook              | IssueHookNotifyEventHandler        | ✔️    |
| Releases Hook           | ReleaseHookNotifyEventHandler      | ✔️    |
| Note Hook               | NoteHookNotifyEventHandler         | ✔️    |
| Job Hook                | JobHookNotifyEventHandler          | ✔️    |
| Confidential Issue Hook | 暂无                                 | ❌     |
| Confidential Note Hook  | 暂无                                 | ❌     |
| Wiki Page Hook          | 暂无                                 | ❌     |
| Deployment Hook         | 暂无                                 | ❌     |
| Feature Flag Hook       | 暂无                                 | ❌     |

目前通知型事件处理类型支持 钉钉机器人,飞书机器人,企业微信机器人,支持自定义扩展通知类型

| 通知类型    | 实现类                | 完成状态 |
|:--------|:-------------------|:-----|
| 钉钉机器人   | DingTalkNotifier   | ✔️   |
| 企业微信机器人 | CorpWechatNotifier | ✔️   |
| 飞书机器人   | FeiShuNotifier     | ✔️   |
