package com.bus.ticket.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.ticket.entity.OrderRecord;
import com.bus.ticket.model.query.OrderRecordPageQuery;
import com.bus.ticket.service.OrderRecordService;

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

    @ApiOperation(value = "添加订单信息", notes = "添加订单信息记录")
    @PostMapping("orderRecord")
    public void add(@ApiParam(value = "订单信息信息", required = true) @RequestBody OrderRecord orderRecord) {
        this.orderRecordService.save(orderRecord);
    }

    @ApiOperation(value = "修改订单信息", notes = "根据ID修改订单信息信息")
    @PutMapping("orderRecord/{id}")
    public void edit(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "订单信息信息", required = true) @RequestBody OrderRecord baseOrderRecord) {
        baseOrderRecord.setId(id);
        this.orderRecordService.updateById(baseOrderRecord);
    }
}
