package io.github.webhook.gitlab.vo;

/**
 * Created by EalenXie on 2022/1/27 17:17
 */
public class Emoji {

    /**
     * 是否发送emoji标签
     */
    private static boolean enable = true;

    private String code = "";

    public Emoji() {
    }

    public Emoji(String code) {
        this.code = code;
    }


    public static void enableEmoji(boolean enableEmoji) {
        enable = enableEmoji;
    }


    @Override
    public String toString() {
        if (enable) {
            return code;
        } else {
            return "";
        }
    }


    public void setCode(String code) {
        this.code = code;
    }
}
