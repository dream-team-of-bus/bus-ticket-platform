package com.bus.ticket.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.ticket.entity.BusLineWaitPoint;
import com.bus.ticket.service.BusLineWaitPointService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 路线的上车地点 前端控制器
 * </p>
 *
 * @author honglixiang
 * @since 2023-08-03 07:39:36
 */
@Api("路线上车点管理")
@RestController
public class BusLineWaitPointController {

    @Resource
    private BusLineWaitPointService orderRecordService;

    @ApiOperation(value = "查询路线的上车地点信息", notes = "查询路线的上车地点信息")
    @GetMapping("busLineWaitPoint/busLine/{busLineId}/list")
    public List<BusLineWaitPoint>
        queryByPage(@ApiParam(value = "路线ID", required = true) @PathVariable Integer busLineId) {
        LambdaQueryWrapper<BusLineWaitPoint> wrapper = Wrappers.lambdaQuery(BusLineWaitPoint.class);
        wrapper.eq(BusLineWaitPoint::getBusLineId, busLineId);
        return orderRecordService.list(wrapper);
    }

    @ApiOperation(value = "获取路线的上车地点信息详情", notes = "根据ID获取路线的上车地点信息详细信息")
    @GetMapping("busLineWaitPoint/{id}")
    public BusLineWaitPoint queryById(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        return this.orderRecordService.getById(id);
    }

    @ApiOperation(value = "添加路线的上车地点信息", notes = "添加路线的上车地点信息记录")
    @PostMapping("busLineWaitPoint/busLine/{busLineId}")
    public void add(@ApiParam(value = "路线ID", required = true) @PathVariable Integer busLineId,
        @ApiParam(value = "路线的上车地点信息信息", required = true) @RequestBody BusLineWaitPoint entity) {
        entity.setBusLineId(busLineId);
        this.orderRecordService.save(entity);
    }

    @ApiOperation(value = "修改路线的上车地点信息", notes = "根据ID修改路线的上车地点信息信息")
    @PutMapping("busLineWaitPoint/{id}")
    public void edit(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "路线的上车地点信息信息", required = true) @RequestBody BusLineWaitPoint entity) {
        entity.setId(id);
        this.orderRecordService.updateById(entity);
    }
}
