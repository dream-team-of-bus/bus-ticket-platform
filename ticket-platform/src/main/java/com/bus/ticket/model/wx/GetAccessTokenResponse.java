package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
@Data
public class GetAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresInSecond;
}
