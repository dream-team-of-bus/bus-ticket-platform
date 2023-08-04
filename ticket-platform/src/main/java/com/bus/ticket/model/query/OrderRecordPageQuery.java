package com.bus.ticket.model.query;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.OrderRecord;
import com.bus.ticket.model.common.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/3
 */
@ApiModel("订单分页查询模型")
@Data
public class OrderRecordPageQuery extends PageInfo {

    @ApiModelProperty("线路ID")
    private Integer busLineId;

    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty("乘客用户ID")
    private Integer creationUserId;

    @ApiModelProperty("司机用户ID")
    private Integer driverUserId;

    @ApiModelProperty("创建时间")
    private Date creationTime;

    public LambdaQueryWrapper<OrderRecord> toWrapper() {
        LambdaQueryWrapper<OrderRecord> wrapper = Wrappers.lambdaQuery(OrderRecord.class);
        if (busLineId != null) {
            wrapper.eq(OrderRecord::getBusLineId, busLineId);
        }
        if (status != null) {
            wrapper.eq(OrderRecord::getStatus, status);
        }
        if (creationUserId != null) {
            wrapper.eq(OrderRecord::getCreatorUserId, creationUserId);
        }
        if (driverUserId != null) {
            wrapper.eq(OrderRecord::getDriverUserId, driverUserId);
        }
        if (creationTime != null) {
            wrapper.gt(OrderRecord::getCreationTime, creationTime);
        }
        return wrapper;
    }
}
