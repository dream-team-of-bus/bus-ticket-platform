package com.bus.ticket.model.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.BusLineWaitPoint;
import com.bus.ticket.model.common.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/3
 */
@ApiModel("线路上车点信息查询模型")
@Data
public class BusLineWaitPointPageQuery extends PageInfo {

    @ApiModelProperty("线路ID")
    private Integer busLineId;

    public LambdaQueryWrapper<BusLineWaitPoint> toWrapper() {
        LambdaQueryWrapper<BusLineWaitPoint> wrapper = Wrappers.lambdaQuery(BusLineWaitPoint.class);
        if (busLineId != null) {
            wrapper.eq(BusLineWaitPoint::getBusLineId, busLineId);
        }
        return wrapper;
    }
}
