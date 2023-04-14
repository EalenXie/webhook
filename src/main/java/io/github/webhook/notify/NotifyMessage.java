package io.github.webhook.notify;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author EalenXie created on 2023/4/14 14:25
 */
@Getter
@Setter
public class NotifyMessage {
    private String title;
    private String message;
    private List<String> notifies;

}
