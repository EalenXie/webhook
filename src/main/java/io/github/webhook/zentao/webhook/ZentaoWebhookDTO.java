package io.github.webhook.zentao.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author EalenXie created on 2023/4/28 13:06
 */
@Getter
@Setter
public class ZentaoWebhookDTO {


    /**
     * 对象类型
     */
    @JsonProperty("objectType")
    private String objectType;
    /**
     * 对象ID
     */
    @JsonProperty("objectID")
    private String objectID;
    /**
     * 所属产品
     */
    @JsonProperty("product")
    private String product;
    /**
     * 所属执行
     */
    @JsonProperty("execution")
    private String execution;
    /**
     * 动作
     */
    @JsonProperty("action")
    private String action;
    /**
     * 操作者
     */
    @JsonProperty("actor")
    private String actor;
    /**
     * 操作日期
     */
    @JsonProperty("date")
    private String date;
    /**
     * 备注
     */
    @JsonProperty("comment")
    private String comment;
    /**
     * 操作内容
     */
    @JsonProperty("text")
    private String text;
}
