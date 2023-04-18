package io.github.webhook.gitlab.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by EalenXie on 2022/2/10 15:50
 */
@Getter
@Setter
public class GitlabUser {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("username")
    private String username;
    @JsonProperty("state")
    private String state;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("bio")
    private String bio;
    @JsonProperty("location")
    private String location;
    @JsonProperty("public_email")
    private String publicEmail;
    @JsonProperty("skype")
    private String skype;
    @JsonProperty("linkedin")
    private String linkedin;
    @JsonProperty("twitter")
    private String twitter;
    @JsonProperty("website_url")
    private String websiteUrl;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("pronouns")
    private String pronouns;
    @JsonProperty("bot")
    private Boolean bot;
    @JsonProperty("work_information")
    private Object workInformation;
    @JsonProperty("followers")
    private Integer followers;
    @JsonProperty("following")
    private Integer following;
    @JsonProperty("local_time")
    private Object localTime;
    @JsonProperty("last_sign_in_at")
    private String lastSignInAt;
    @JsonProperty("confirmed_at")
    private String confirmedAt;
    @JsonProperty("last_activity_on")
    private String lastActivityOn;
    @JsonProperty("email")
    private String email;
    @JsonProperty("theme_id")
    private Integer themeId;
    @JsonProperty("color_scheme_id")
    private Integer colorSchemeId;
    @JsonProperty("projects_limit")
    private Integer projectsLimit;
    @JsonProperty("current_sign_in_at")
    private String currentSignInAt;
    @JsonProperty("identities")
    private List<Object> identities;
    @JsonProperty("can_create_group")
    private Boolean canCreateGroup;
    @JsonProperty("can_create_project")
    private Boolean canCreateProject;
    @JsonProperty("two_factor_enabled")
    private Boolean twoFactorEnabled;
    @JsonProperty("external")
    private Boolean external;
    @JsonProperty("private_profile")
    private Boolean privateProfile;
    @JsonProperty("commit_email")
    private String commitEmail;
    @JsonProperty("shared_runners_minutes_limit")
    private Object sharedRunnersMinutesLimit;
    @JsonProperty("extra_shared_runners_minutes_limit")
    private Object extraSharedRunnersMinutesLimit;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("note")
    private String note;
    @JsonProperty("using_license_seat")
    private Boolean usingLicenseSeat;
    @JsonProperty("highest_role")
    private Integer highestRole;
    @JsonProperty("current_sign_in_ip")
    private String currentSignInIp;
    @JsonProperty("last_sign_in_ip")
    private String lastSignInIp;
    @JsonProperty("sign_in_count")
    private Integer signInCount;
    @JsonProperty("plan")
    private Object plan;
    @JsonProperty("trial")
    private Boolean trial;
}
