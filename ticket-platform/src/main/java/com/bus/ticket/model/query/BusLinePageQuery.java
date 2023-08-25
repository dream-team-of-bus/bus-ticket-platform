package com.bus.ticket.model.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.BusLine;
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

    @ApiModelProperty(value = "出发城市")
    private String departure;

    @ApiModelProperty(value = "目的城市")
    private String destination;

    @ApiModelProperty(value = "始发站")
    private String departureStation;

    @ApiModelProperty(value = "到达站")
    private String arrivalStation;

    public LambdaQueryWrapper<BusLine> toWrapper() {
        LambdaQueryWrapper<BusLine> queryWrapper = Wrappers.lambdaQuery(BusLine.class);
        if (departure != null) {
            queryWrapper.eq(BusLine::getDeparture, departure);
        }
        if (destination != null) {
            queryWrapper.eq(BusLine::getDestination, destination);
        }
        if (departureStation != null) {
            queryWrapper.eq(BusLine::getDepartureStation, departureStation);
        }
        if (arrivalStation != null) {
            queryWrapper.eq(BusLine::getArrivalStation, arrivalStation);
        }
        return queryWrapper;
    }
}
