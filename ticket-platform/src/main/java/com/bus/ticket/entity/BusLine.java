package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 汽车线路(BusLine)实体类
 *
 * @author honglixiang
 * @since 2023-07-31 14:58:03
 */
@Data
@ApiModel("汽车线路实体类")
public class BusLine implements Serializable {

    private static final long serialVersionUID = 473723037162924241L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "出发城市")
    private String departure;

    @ApiModelProperty(value = "目的城市")
    private String destination;

    @ApiModelProperty(value = "始发站")
    private String departureStation;

    @ApiModelProperty(value = "到达站")
    private String arrivalStation;

    @ApiModelProperty(value = "车站票价，单位分")
    private Long stationFare;

    @ApiModelProperty(value = "平台票价，单位分")
    private Long platformFare;

    @ApiModelProperty(value = "平台收入，单位分")
    private Long platformRevenue;

    private Date creationTime;

    private Date updateTime;
}
