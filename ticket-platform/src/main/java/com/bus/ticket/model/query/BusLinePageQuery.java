package com.bus.ticket.model.query;

import java.util.Date;

import com.bus.ticket.model.common.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 汽车线路(BusLine)表分页查询模型
 *
 * @author makejava
 * @since 2023-07-28 09:38:37
 */
@ApiModel("汽车线路分页查询模型")
@Data
public class BusLinePageQuery extends PageInfo {

    private Integer id;

    @ApiModelProperty(value = "出发城市")
    private String departure;

    @ApiModelProperty(value = "目的城市")
    private String destination;

    @ApiModelProperty(value = "始发站")
    private String departureStation;

    @ApiModelProperty(value = "到达站")
    private String arrivalStation;

    private Date creationTime;

    private Date updateTime;
}
