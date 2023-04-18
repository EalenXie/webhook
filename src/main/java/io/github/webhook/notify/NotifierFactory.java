package io.github.webhook.notify;

import io.github.webhook.meta.NotifyConf;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 16:37
 */
public class NotifierFactory implements ApplicationContextAware {
    private final Map<String, List<Notifier>> webhookNotifies = new HashMap<>();
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Notifier getNotifier(Class<? extends Notifier> clz) {
        return applicationContext.getBean(clz);
    }


    public List<Notifier> getNotifies(Webhook webhook) {
        List<Notifier> notifiers = webhookNotifies.get(webhook.getId());
        if (notifiers == null) {
            notifiers = new ArrayList<>();
            NotifyConf notify = webhook.getNotify();
            if (notify.getDingTalk() != null) {
                notifiers.add(getNotifier(DingTalkNotifier.class));
            }
            if (notify.getWechat() != null) {
                notifiers.add(getNotifier(CorpWechatNotifier.class));
            }
            if (notify.getFeiShu() != null) {
                notifiers.add(getNotifier(FeiShuNotifier.class));
            }
            webhookNotifies.put(webhook.getId(), notifiers);
        }
        return notifiers;
    }

}
