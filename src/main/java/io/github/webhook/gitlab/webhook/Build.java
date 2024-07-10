package io.github.webhook.gitlab.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Created by EalenXie on 2021/12/1 9:46
 */
@Getter
@Setter
public class Build implements Comparable<Build> {
    private Long id;
    private String stage;
    private String name;
    private String status;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("started_at")
    private String startedAt;
    @JsonProperty("finished_at")
    private String finishedAt;
    private Double duration;
    @JsonProperty("queued_duration")
    private Double queuedDuration;
    private String when;
    private Boolean manual;
    @JsonProperty("allow_failure")
    private Boolean allowFailure;
    private User user;
    private Runner runner;
    @JsonProperty("artifacts_file")
    private ArtifactsFile artifactFile;
    /**
     * 新旧版本不一致,使用Object对象兼容
     */
    private Object environment;

    @Getter
    @Setter
    public static class ArtifactsFile {
        private String filename;
        private Long size;
    }

    @Override
    public int compareTo(Build o) {
        if (Objects.equals(id, o.id)) {
            return 0;
        }
        if (id > o.id) return 1;
        else return -1;
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
