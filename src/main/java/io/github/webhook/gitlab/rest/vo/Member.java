package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EalenXie on 2022/3/23 10:54
 */
@Getter
@Setter
public class Member {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("expires_at")
    private String expiresAt;
    @JsonProperty("access_level")
    private Integer accessLevel;
    @JsonProperty("email")
    private String email;
    @JsonProperty("group_saml_identity")
    private GroupSamlIdentity groupSamlIdentity;

    @NoArgsConstructor
    @Data
    public static class GroupSamlIdentity {
        @JsonProperty("extern_uid")
        private String externUid;
        @JsonProperty("provider")
        private String provider;
        @JsonProperty("saml_provider_id")
        private Integer samlProviderId;
    }
}
