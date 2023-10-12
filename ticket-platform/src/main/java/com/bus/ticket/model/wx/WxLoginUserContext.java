package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
@Data
public class WxLoginUserContext {

    private String sessionKey;

    private String openId;

    private String unionId;

    private String iv;

    private String rawData;

    private UserInfo userInfo;

    private String signature;

    private String encryptedData;

    @NoArgsConstructor
    @Data
    public static class UserInfo {

        @JsonProperty("nickName")
        private String nickName;

        @JsonProperty("gender")
        private Integer gender;

        @JsonProperty("language")
        private String language;

        @JsonProperty("city")
        private String city;

        @JsonProperty("province")
        private String province;

        @JsonProperty("country")
        private String country;

        @JsonProperty("avatarUrl")
        private String avatarUrl;

        @JsonProperty("is_demote")
        private Boolean isDemote;
    }
}
