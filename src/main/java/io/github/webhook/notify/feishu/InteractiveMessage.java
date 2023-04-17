package io.github.webhook.notify.feishu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/2/15 12:56
 */
@NoArgsConstructor
public class InteractiveMessage {

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sign")
    private String sign;
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private String timestamp;
    @Getter
    @JsonProperty("msg_type")
    private String msgType = "interactive";
    @Getter
    @Setter
    private Interactive card;

    public InteractiveMessage(Interactive card) {
        this.card = card;
    }

}
