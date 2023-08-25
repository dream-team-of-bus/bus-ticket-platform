package com.bus.ticket.model.query;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.common.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * (User)表分页查询模型
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
@ApiModel("分页查询模型")
@Data
public class UserPageQuery extends PageInfo {

    private List<Integer> ids;

    private String name;

    @ApiModelProperty(value = "用户类型：1普通用户，2司机，-1管理员")
    private Integer type;

    @ApiModelProperty(value = "微信OpenID")
    private String wxOpenId;

    @ApiModelProperty(value = "微信UnionID，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的。")
    private String wxUnionId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    public LambdaQueryWrapper<User> toWrapper() {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (ids != null) {
            wrapper.in(User::getId, ids);
        }
        if (name != null) {
            wrapper.like(User::getName, name);
        }
        if (nickName != null) {
            wrapper.like(User::getNickName, nickName);
        }
        if (type != null) {
            wrapper.eq(User::getType, type);
        }
        if (wxOpenId != null) {
            wrapper.eq(User::getWxOpenId, wxOpenId);
        }
        if (wxUnionId != null) {
            wrapper.eq(User::getWxUnionId, wxUnionId);
        }
        if (phone != null) {
            wrapper.eq(User::getPhone, phone);
        }
        return wrapper;
    }
}
