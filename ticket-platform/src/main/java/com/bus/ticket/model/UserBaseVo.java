package com.bus.ticket.model;

import org.springframework.beans.BeanUtils;

import com.bus.ticket.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/31
 */
@ApiModel("用户信息基础实体类")
@Data
public class UserBaseVo {

    private Integer id;

    private Byte type;

    @ApiModelProperty(value = "真实名称")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    public static UserBaseVo convert(User user) {
        if (user == null) {
            return null;
        }
        UserBaseVo vo = new UserBaseVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setNickName(nickName);
        user.setPhone(phone);
        user.setType(type.intValue());
        return user;
    }
}
