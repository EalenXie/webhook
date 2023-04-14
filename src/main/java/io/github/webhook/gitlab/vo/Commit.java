package io.github.webhook.gitlab.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by EalenXie on 2021/12/1 9:40
 */
@Setter
@Getter
public class Commit implements Comparable<Commit> {
    private String id;
    private String message;
    private String title;
    private Date timestamp;
    private String url;
    private Author author;
    private String[] added;
    private String[] modified;
    private String[] removed;

    @Setter
    @Getter
    public static class Author {
        private String name;
        private String email;
    }

    @Override
    public int compareTo(Commit o) {
        if (timestamp.after(o.timestamp)) {
            return -1;
        }
        if (timestamp.before(o.timestamp)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
