package io.github.webhook.meta;

import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/4/17 10:04
 * 消息通知配置
 */
@Getter
@Setter
public class NotifyConf {
    private DingTalkConf dingTalk;
    private FeiShuConf feiShu;
    private WechatConf wechat;

}
