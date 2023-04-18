package io.github.webhook.config;

import io.github.webhook.notify.NotifierFactory;
import io.github.webhook.notify.dingtalk.DingTalkNotifier;
import io.github.webhook.notify.feishu.FeiShuNotifier;
import io.github.webhook.notify.wechat.CorpWechatNotifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * @author EalenXie created on 2023/4/17 12:54
 * <p>
 * 通知 配置
 */
@Configuration
public class NotifierConfig {
    @Bean
    public NotifierFactory notifierFactory() {
        return new NotifierFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public DingTalkNotifier dingTalkNotifier(RestOperations httpClientRestTemplate) {
        return new DingTalkNotifier(httpClientRestTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public CorpWechatNotifier corpWechatNotifier(RestOperations httpClientRestTemplate) {
        return new CorpWechatNotifier(httpClientRestTemplate);
    }


    @Bean
    @ConditionalOnMissingBean
    public FeiShuNotifier feiShuNotifier(RestOperations httpClientRestTemplate) {
        return new FeiShuNotifier(httpClientRestTemplate);
    }


}
