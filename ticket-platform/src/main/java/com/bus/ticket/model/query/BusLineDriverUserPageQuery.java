package com.bus.ticket.model.query;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.model.common.PageInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 线路关联的司机信息(BusLineDriverUser)表分页查询模型
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:53
 */
@ApiModel("线路关联的司机信息分页查询模型")
@Data
public class BusLineDriverUserPageQuery extends PageInfo {

    private List<Integer> ids;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "线路ID")
    private Integer busLineId;

    private Date creationTime;

    public LambdaQueryWrapper<BusLineDriverUser> toWrapper() {
        LambdaQueryWrapper<BusLineDriverUser> wrapper = Wrappers.lambdaQuery();
        if (ids != null) {
            wrapper.in(BusLineDriverUser::getId, ids);
        }
        if (userId != null) {
            wrapper.eq(BusLineDriverUser::getUserId, userId);
        }
        if (busLineId != null) {
            wrapper.eq(BusLineDriverUser::getBusLineId, busLineId);
        }
        return wrapper;
    }
}
