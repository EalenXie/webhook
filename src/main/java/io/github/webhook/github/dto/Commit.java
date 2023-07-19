package io.github.webhook.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author EalenXie created on 2023/7/19 16:08
 */
@NoArgsConstructor
@Data
public class Commit implements Comparable<Commit>{
    /**
     * id
     */
    @JsonProperty("id")
    private String id;
    /**
     * treeId
     */
    @JsonProperty("tree_id")
    private String treeId;
    /**
     * distinct
     */
    @JsonProperty("distinct")
    private Boolean distinct;
    /**
     * message
     */
    @JsonProperty("message")
    private String message;
    /**
     * timestamp
     */
    @JsonProperty("timestamp")
    private Date timestamp;
    /**
     * url
     */
    @JsonProperty("url")
    private String url;
    /**
     * author
     */
    @JsonProperty("author")
    private Author author;
    /**
     * committer
     */
    @JsonProperty("committer")
    private Author committer;
    /**
     * added
     */
    @JsonProperty("added")
    private List<?> added;
    /**
     * removed
     */
    @JsonProperty("removed")
    private List<?> removed;
    /**
     * modified
     */
    @JsonProperty("modified")
    private List<String> modified;

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
}
