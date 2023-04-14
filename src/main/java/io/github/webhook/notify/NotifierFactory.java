package io.github.webhook.notify;

import io.github.webhook.meta.HandlerType;
import io.github.webhook.meta.Webhook;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EalenXie created on 2023/4/14 16:37
 */
@Component
public class NotifierFactory implements ApplicationContextAware {

    private final Map<String, Notifier> notifies = new HashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @PostConstruct
    public void init() {
        notifies.put("_DING_TALK", getNotifier(DingTalkNotifier.class));
        notifies.put("_CORP_WECHAT", getNotifier(CorpWechatNotifier.class));
    }

    private Notifier getNotifier(Class<? extends Notifier> clz) {
        return applicationContext.getBean(clz);
    }

    public Notifier getNotifier(Webhook webhook) {
        HandlerType handlerType = webhook.getHandlerType();
        String name = handlerType.name();
        String regex = "_";
        String notifierKey = name.contains(regex) ? name.split(regex, 2)[1] : name;
        return notifies.get(notifierKey);
    }


}
