package io.github.webhook.notify.feishu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/2/15 13:10
 */
@NoArgsConstructor
public class MarkdownElement implements Element {


    @JsonProperty("tag")
    private String tag = "markdown";
    @Getter
    @Setter
    @JsonProperty("content")
    private String content;
    @Getter
    @Setter
    @JsonProperty("href")
    private Href href;

    public MarkdownElement(String content) {
        this.content = content;
    }

    @Override
    public String getTag() {
        return tag;
    }


}
