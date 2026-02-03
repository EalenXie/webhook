package io.github.webhook.clinet.view.vo;

import io.github.webhook.config.meta.GitlabConf;
import io.github.webhook.config.meta.Webhook;
import io.github.webhook.config.meta.WebhookType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WebhookInfo {
    private String type;
    private String id;
    private String host;
    private String webhookUrl;
    private List<String> onlyRefs;
    private List<String> webUrls;
    private List<String> triggers;


    public WebhookInfo(Webhook webhook) {
        this.id = webhook.getId();
        this.type = webhook.getType().name();
        if (WebhookType.GITLAB.name().equals(this.type)) {
            this.host = webhook.getGitlabHost();
            this.onlyRefs = webhook.getGitlabOnlyRefs();
            this.webUrls = webhook.getGitlabProjectWebUrls();
            GitlabConf.Trigger trigger = webhook.getGitlabTrigger();
            if (trigger != null) {
                this.triggers = new ArrayList<>();
                try {
                    BeanInfo beanInfo = Introspector.getBeanInfo(trigger.getClass(), Object.class);
                    for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                        if (pd.getPropertyType() == boolean.class || pd.getPropertyType() == Boolean.class) {
                            Object value = pd.getReadMethod().invoke(trigger);
                            if (Boolean.TRUE.equals(value)) {
                                triggers.add(pd.getName());
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new UnsupportedOperationException("读取Bean属性失败", e);
                }
            }
        }
    }
}
