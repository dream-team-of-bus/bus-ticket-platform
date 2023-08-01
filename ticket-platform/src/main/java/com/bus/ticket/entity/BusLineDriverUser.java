package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 线路关联的司机信息(BusLineDriverUser)实体类
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:52
 */
@ApiModel("线路关联的司机信息实体类")
public class BusLineDriverUser implements Serializable {
    private static final long serialVersionUID = 915255669850753589L;

    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "线路ID")
    private Integer busLineId;

    private Date creationTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(Integer busLineId) {
        this.busLineId = busLineId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

}
