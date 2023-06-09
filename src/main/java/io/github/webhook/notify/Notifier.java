package io.github.webhook.notify;

import io.github.webhook.meta.Webhook;

import java.util.regex.Pattern;

/**
 * @author EalenXie created on 2023/4/14 13:57
 */
public interface Notifier<M> {

    /**
     * 手机号正则
     */
    Pattern PHONE_PATTERN = Pattern.compile("(13\\d|14[579]|15[0-3,5-9]|166|17[0135678]|18\\d|19[89])\\d{8}");

    /**
     * 处理 通知消息 得到真正的请求消息对象
     *
     * @param message 通知消息
     * @return 请求消息对象
     */
    M process(NotifyMessage message);


    /**
     * 消息通知
     *
     * @param webhook webhook信息
     * @param message 请求消息对象
     */
    void notify(Webhook webhook, M message);


}
