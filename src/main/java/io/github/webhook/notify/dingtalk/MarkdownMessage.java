package io.github.webhook.notify.dingtalk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by EalenXie on 2021/12/27 10:58
 */

public class MarkdownMessage {


    @JsonProperty("msgtype")
    private String msgType = "markdown";
    @Setter
    @Getter
    private Markdown markdown;
    private DingRobotAt at;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public DingRobotAt getAt() {
        return at;
    }

    public void setAt(DingRobotAt at) {
        this.at = at;
    }
}
