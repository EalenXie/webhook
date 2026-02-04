package io.github.webhook.notify;

import io.github.webhook.config.SpringEnvHelper;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 16:37
 */
public class NotifierFactory {

    /**
     * 维护Webhook的通知器工厂 Key为WebhookId
     */
    private final Map<String, List<Notifier<Object, Object>>> webhookNotifies = new HashMap<>();


    @SuppressWarnings("unchecked")
    public List<Notifier<Object, Object>> getNotifies(Webhook webhook) {
        List<Notifier<Object, Object>> notifiers = webhookNotifies.get(webhook.getId());
        if (notifiers == null) {
            notifiers = new ArrayList<>();
            if (webhook.getNotify().getDingTalk() != null) {
                Notifier<?, ?> bean = SpringEnvHelper.getBean(DingTalkNotifier.class);
                notifiers.add((Notifier<Object, Object>) bean);
            }
            if (webhook.getNotify().getWechat() != null) {
                Notifier<?, ?> bean = SpringEnvHelper.getBean(CorpWechatNotifier.class);
                notifiers.add((Notifier<Object, Object>) bean);
            }
            if (webhook.getNotify().getFeiShu() != null) {
                Notifier<?, ?> bean = SpringEnvHelper.getBean(FeiShuNotifier.class);
                notifiers.add((Notifier<Object, Object>) bean);
            }
            webhookNotifies.put(webhook.getId(), notifiers);
        }
        return notifiers;
    }

}
