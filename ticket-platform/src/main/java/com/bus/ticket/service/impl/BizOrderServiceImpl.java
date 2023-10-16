package com.bus.ticket.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bus.ticket.constant.OrderRecordStatus;
import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.entity.OrderRecord;
import com.bus.ticket.model.query.BusLineDriverUserPageQuery;
import com.bus.ticket.service.BizOrderService;
import com.bus.ticket.service.BusLineDriverUserService;
import com.bus.ticket.service.OrderRecordService;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/16
 */
@Service
public class BizOrderServiceImpl implements BizOrderService {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private BusLineDriverUserService driverUserService;

    @Override
    public void confirm(Integer orderId, Integer driverUserId) {
        Assert.notNull(orderId, "orderId不能为空");
        Assert.notNull(driverUserId, "driverUserId不能为空");

        OrderRecord order = orderRecordService.getById(orderId);
        if (!OrderRecordStatus.PAID.getCode().equals(order.getStatus())) {
            throw new RuntimeException("订单状态异常");
        }

        BusLineDriverUserPageQuery pageQuery = new BusLineDriverUserPageQuery();
        pageQuery.setBusLineId(order.getBusLineId());
        pageQuery.setUserId(driverUserId);
        List<BusLineDriverUser> driverUsers = driverUserService.list(pageQuery.toWrapper());
        BusLineDriverUser driverUser = driverUsers.get(0);

        OrderRecord updater = new OrderRecord();
        updater.setId(orderId);
        updater.setStatus(OrderRecordStatus.COMPLETED.getCode());
        updater.setPlatformRevenue(order.getPaymentAmount() - driverUser.getRevenue());
        updater.setDriverRevenue(driverUser.getRevenue());
        updater.setDriverConfirmTime(new Date());
        updater.setDriverUserId(driverUserId);
        this.orderRecordService.updateById(updater);
    }
}
