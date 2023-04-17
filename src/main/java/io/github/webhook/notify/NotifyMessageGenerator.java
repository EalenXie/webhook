package io.github.webhook.notify;

/**
 * @author EalenXie created on 2023/4/14 14:22
 * 通知消息生成器
 */
public interface NotifyMessageGenerator<D> {


    /**
     * 生成 通知消息
     *
     * @param data 原始数据对象
     * @return 通知消息
     */
    NotifyMessage generate(D data);


}
