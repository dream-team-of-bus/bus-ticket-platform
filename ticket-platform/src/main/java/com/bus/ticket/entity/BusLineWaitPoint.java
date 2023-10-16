package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 路线的上车地点
 * </p>
 *
 * @author honglixiang
 * @since 2023-08-03 07:39:36
 */
@Getter
@Setter
@TableName("bus_line_wait_point")
@ApiModel(value = "BusLineWaitPoint对象", description = "路线的上车地点")
public class BusLineWaitPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("线路ID")
    private Integer busLineId;

    @ApiModelProperty("地理名称")
    private String geographicalName;

    @ApiModelProperty("经度")
    private String longitude;

    @ApiModelProperty("纬度")
    private String latitude;

    private Date creationTime;

    private Date updateTime;

}
