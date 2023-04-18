package io.github.webhook.gitlab.rest.vo;

/**
 * Created by EalenXie on 2022/3/23 11:35
 */
@SuppressWarnings("all")
public enum JobScope {
    created, pending, running, failed, success, canceled, skipped, manual
}
