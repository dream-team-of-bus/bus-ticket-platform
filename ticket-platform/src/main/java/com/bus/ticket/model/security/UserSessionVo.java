package com.bus.ticket.model.security;

import java.util.List;

import com.bus.ticket.model.UserBaseVo;

import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Data
public class UserSessionVo extends UserBaseVo {

    private String sessionId;

    private List<String> permissions;
}
