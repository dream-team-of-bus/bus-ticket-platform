package com.bus.ticket.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.service.BusLineDriverUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 线路关联的司机信息(BusLineDriverUser)表控制层
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:53
 */
@Api(tags = "线路关联的司机信息管理")
@RestController
public class BusLineDriverUserController {

    @Resource
    private BusLineDriverUserService busLineDriverUserService;

    @ApiOperation(value = "添加线路关联的司机信息", notes = "添加线路关联的司机信息记录")
    @PostMapping("busLineDriverUser")
    public void
        add(@ApiParam(value = "线路关联的司机信息信息", required = true) @RequestBody BusLineDriverUser busLineDriverUser) {
        this.busLineDriverUserService.save(busLineDriverUser);
    }

    @ApiOperation(value = "删除线路关联的司机信息", notes = "根据ID删除线路关联的司机信息记录")
    @DeleteMapping("busLineDriverUser/{id}")
    public Boolean deleteById(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        return this.busLineDriverUserService.removeById(id);
    }
}
