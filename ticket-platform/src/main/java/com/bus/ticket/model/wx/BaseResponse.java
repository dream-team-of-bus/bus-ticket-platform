package com.bus.ticket.model.wx;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
@Data
public class BaseResponse {

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;
}
