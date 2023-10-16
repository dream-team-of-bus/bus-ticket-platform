package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 线路关联的司机信息(BusLineDriverUser)实体类
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:52
 */
@Data
@ApiModel("线路关联的司机信息实体类")
public class BusLineDriverUser implements Serializable {

    private static final long serialVersionUID = 915255669850753589L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "线路ID")
    private Integer busLineId;

    @ApiModelProperty(value = "路线收入，单位分")
    private Long revenue;

    private Date creationTime;
}
