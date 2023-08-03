package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Data
public class WechatLoginResponse {

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("errcode")
    private String errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @JsonProperty("unionid")
    private String unionId;
}
