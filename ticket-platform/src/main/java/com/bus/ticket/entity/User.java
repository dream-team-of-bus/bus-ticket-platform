package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息(User)实体类
 *
 * @author honglixiang
 * @since 2023-07-31 16:40:50
 */
@Data
@ApiModel("用户信息实体类")
public class User implements Serializable {
    private static final long serialVersionUID = 531599375110899237L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "真实名称")
    private String name;

    @ApiModelProperty(value = "用户类型：1普通用户，2司机，-1管理员")
    private Integer type;

    @ApiModelProperty(value = "微信OpenID")
    private String wxOpenId;

    @ApiModelProperty(value = "微信UnionID，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的。")
    private String wxUnionId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像路径")
    private String headUrl;

    @ApiModelProperty(value = "手机号")
    private String phone;

    private Date creationTime;

    private Date updateTime;
}
