package io.github.webhook.gitlab.vo.release;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.webhook.gitlab.vo.Commit;
import io.github.webhook.gitlab.vo.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/1/20 9:57
 */
@Setter
@Getter
public class ReleaseHook   {

    private String id;
    @JsonProperty("created_at")
    private String createAt;
    @JsonProperty("object_kind")
    private String objectKind;
    private String name;
    private String tag;
    private String description;
    private Project project;
    private String url;
    private String action;
    private Assets assets;
    private Commit commit;

    @Setter
    @Getter
    public static class Assets {
        private Integer count;
        private String[] links;
        private List<Source> sources;

        @Getter
        @Setter
        public static class Source {
            private String format;
            private String url;
        }
    }


}
