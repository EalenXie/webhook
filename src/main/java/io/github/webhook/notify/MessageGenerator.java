package io.github.webhook.notify;

/**
 * @author EalenXie created on 2023/4/14 14:22
 */
public interface MessageGenerator<D> {


    NotifyMessage generate(D data);


}
