package com.bus.ticket.controller;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.ticket.constant.OrderRecordStatus;
import com.bus.ticket.entity.BusLine;
import com.bus.ticket.entity.OrderRecord;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.query.OrderRecordPageQuery;
import com.bus.ticket.service.BusLineService;
import com.bus.ticket.service.OrderRecordService;
import com.bus.ticket.util.AuthenticationUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author honglixiang
 * @since 2023-08-03 04:57:35
 */
@Api(tags = "订单记录管理")
@RestController
public class OrderRecordController {

    @Resource
    private OrderRecordService orderRecordService;
    @Resource
    private BusLineService busLineService;

    @ApiOperation(value = "订单信息分页查询", notes = "订单信息分页查询")
    @PostMapping("orderRecord/pageQuery")
    public Page<OrderRecord>
        queryByPage(@ApiParam(value = "分页查询参数", required = true) @RequestBody OrderRecordPageQuery pageQuery) {
        return orderRecordService.page(pageQuery.toPage(), pageQuery.toWrapper());
    }

    @ApiOperation(value = "获取订单信息详情", notes = "根据ID获取订单信息详细信息")
    @GetMapping("orderRecord/{id}")
    public OrderRecord queryById(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        return this.orderRecordService.getById(id);
    }

    @ApiOperation(value = "下订单", notes = "乘客下订单")
    @PostMapping("orderRecord")
    public void add(@ApiParam(value = "订单信息信息", required = true) @RequestBody OrderRecord orderRecord) {
        Assert.notNull(orderRecord.getBusLineId(), "BusLineId");
        Assert.notNull(orderRecord.getDepartureTime(), "DepartureTime");
        User user = AuthenticationUtils.getUser();

        BusLine busLine = busLineService.getById(orderRecord.getBusLineId());
        OrderRecord order = new OrderRecord();
        order.setBusLineId(busLine.getId());
        order.setDeparture(busLine.getDeparture());
        order.setDestination(busLine.getDestination());
        order.setDepartureStation(busLine.getDepartureStation());
        order.setArrivalStation(busLine.getArrivalStation());
        order.setPaymentAmount(busLine.getPlatformFare());
        order.setDepartureTime(orderRecord.getDepartureTime());
        order.setCreatorUserId(user.getId());
        order.setStatus(OrderRecordStatus.WAITING_PAYMENT.getCode());
        this.orderRecordService.save(orderRecord);
    }

    @ApiOperation(value = "订单支付", notes = "订单支付")
    @PutMapping("orderRecord/{id}/pay")
    public void pay(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        OrderRecord oldOrder = orderRecordService.getById(id);
        if (!OrderRecordStatus.WAITING_PAYMENT.getCode().equals(oldOrder.getStatus())) {
            throw new RuntimeException("订单已支付");
        }

        OrderRecord updater = new OrderRecord();
        updater.setId(id);
        updater.setStatus(OrderRecordStatus.PAID.getCode());
        this.orderRecordService.updateById(updater);
    }

    @ApiOperation(value = "乘客扫码确认", notes = "订单确认，乘客扫码确认")
    @PutMapping("orderRecord/{id}/confirm")
    public void confirm(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        OrderRecord oldOrder = orderRecordService.getById(id);
        if (!OrderRecordStatus.PAID.getCode().equals(oldOrder.getStatus())) {
            throw new RuntimeException("订单状态异常");
        }

        LambdaQueryWrapper<OrderRecord> wrapper = Wrappers.lambdaQuery(OrderRecord.class);
        wrapper.lt(OrderRecord::getDepartureTime, oldOrder.getDepartureTime());
        wrapper.eq(OrderRecord::getStatus, OrderRecordStatus.PAID.getCode());
        long count = orderRecordService.count(wrapper);
        if (count > 0) {
            throw new RuntimeException("还存在上个车次的订单，请排队稍后再试");
        }

        OrderRecord updater = new OrderRecord();
        updater.setId(id);
        updater.setStatus(OrderRecordStatus.COMPLETED.getCode());
        this.orderRecordService.updateById(updater);
    }

    @ApiOperation(value = "司机扫码确认", notes = "订单确认，司机扫码确认")
    @PutMapping("orderRecord/{id}/driver/confirm")
    public void driverConfirm(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        OrderRecord oldOrder = orderRecordService.getById(id);
        if (!OrderRecordStatus.PAID.getCode().equals(oldOrder.getStatus())) {
            throw new RuntimeException("订单状态异常");
        }
        OrderRecord updater = new OrderRecord();
        updater.setId(id);
        updater.setStatus(OrderRecordStatus.COMPLETED.getCode());
        this.orderRecordService.updateById(updater);
    }

    @ApiOperation(value = "订单退款", notes = "订单退款")
    @PutMapping("orderRecord/{id}/refund")
    public void refund(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        OrderRecord updater = new OrderRecord();
        updater.setId(id);
        updater.setStatus(OrderRecordStatus.REFUNDED.getCode());
        this.orderRecordService.updateById(updater);
    }
}
