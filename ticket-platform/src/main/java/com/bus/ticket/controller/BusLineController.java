package com.bus.ticket.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.ticket.entity.BusLine;
import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.BusLineBaseVo;
import com.bus.ticket.model.UserBaseVo;
import com.bus.ticket.model.query.BusLineDriverUserPageQuery;
import com.bus.ticket.model.query.BusLinePageQuery;
import com.bus.ticket.model.query.UserPageQuery;
import com.bus.ticket.service.BusLineDriverUserService;
import com.bus.ticket.service.BusLineService;
import com.bus.ticket.service.UserService;
import com.bus.ticket.util.PageUtils;
import com.bus.ticket.util.StreamUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 汽车线路(BusLine)表控制层
 *
 * @author makejava
 * @since 2023-07-28 09:38:37
 */
@Api(tags = "汽车线路管理")
@RestController
public class BusLineController {

    @Resource
    private BusLineService busLineService;
    @Resource
    private BusLineDriverUserService driverUserService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "汽车线路分页查询", notes = "汽车线路分页查询")
    @PostMapping("busLine/pageQuery/admin")
    public Page<BusLine>
        queryByPageAdmin(@ApiParam(value = "分页查询参数", required = true) @RequestBody BusLinePageQuery pageQuery) {
        return this.busLineService.page(pageQuery.toPage(), pageQuery.toWrapper());
    }

    @ApiOperation(value = "汽车线路分页查询", notes = "汽车线路分页查询")
    @PostMapping("busLine/pageQuery")
    public Page<BusLineBaseVo>
        queryByPage(@ApiParam(value = "分页查询参数", required = true) @RequestBody BusLinePageQuery pageQuery) {
        Page<BusLine> page = this.busLineService.page(pageQuery.toPage(), pageQuery.toWrapper());
        List<BusLineBaseVo> vos = StreamUtils.toList(page.getRecords(), v -> BusLineBaseVo.convert(v));
        return PageUtils.convert(page, vos);
    }

    @ApiOperation(value = "汽车线路司机分页查询", notes = "汽车线路司机分页查询")
    @PostMapping("busLine/{id}/driver")
    public Page<UserBaseVo> pageQueryDrivers(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "分页查询参数", required = true) @RequestBody UserPageQuery userPageQuery) {
        BusLineDriverUserPageQuery driverUserPageQuery = new BusLineDriverUserPageQuery();
        driverUserPageQuery.setBusLineId(id);
        List<BusLineDriverUser> driverUsers = driverUserService.list(driverUserPageQuery.toWrapper());
        if (CollectionUtils.isEmpty(driverUsers)) {
            return new Page<>();
        }
        Set<Integer> userIds = StreamUtils.toSet(driverUsers, BusLineDriverUser::getUserId);
        userPageQuery.setIds(new ArrayList<>(userIds));
        Page<User> userPage = userService.page(userPageQuery.toPage(), userPageQuery.toWrapper());
        List<UserBaseVo> vos = StreamUtils.toList(userPage.getRecords(), v -> UserBaseVo.convert(v));
        return PageUtils.convert(userPage, vos);
    }

    @ApiOperation(value = "汽车线路查询", notes = "汽车线路查询")
    @PostMapping("busLine/list")
    public List<BusLineBaseVo>
        query(@ApiParam(value = "分页查询参数", required = true) @RequestBody BusLinePageQuery pageQuery) {
        List<BusLine> list = this.busLineService.list(pageQuery.toWrapper());
        return StreamUtils.toList(list, v -> BusLineBaseVo.convert(v));
    }

    @ApiOperation(value = "获取汽车线路详情", notes = "根据ID获取汽车线路详细信息")
    @GetMapping("busLine/{id}")
    public BusLine queryById(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id) {
        return this.busLineService.getById(id);
    }

    @ApiOperation(value = "添加汽车线路", notes = "添加汽车线路记录")
    @PostMapping("busLine")
    public void add(@ApiParam(value = "汽车线路信息", required = true) @RequestBody BusLineBaseVo busLine) {
        this.busLineService.save(busLine.to());
    }

    @ApiOperation(value = "修改汽车线路基本信息", notes = "根据ID修改汽车线路基本信息")
    @PutMapping("busLine/{id}/basic")
    public void editBasic(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "汽车线路信息", required = true) @RequestBody BusLineBaseVo busLine) {
        busLine.setId(id);
        this.busLineService.updateById(busLine.to());
    }

    @ApiOperation(value = "修改汽车线路平台设置信息", notes = "根据ID修改汽车线路平台设置信息")
    @PutMapping("busLine/{id}")
    public void edit(@ApiParam(value = "主键ID", required = true) @PathVariable("id") Integer id,
        @ApiParam(value = "汽车线路信息", required = true) @RequestBody BusLine busLine) {
        Assert.notNull(busLine.getStationFare(), "StationFare is null");
        Assert.notNull(busLine.getPlatformFare(), "PlatformFare is null");
        Assert.notNull(busLine.getPlatformRevenue(), "PlatformRevenue is null");
        Assert.isTrue(busLine.getPlatformFare() >= busLine.getStationFare(), "PlatformFare 不能大于 StationFare");
        Assert.isTrue(busLine.getPlatformRevenue() >= busLine.getPlatformFare(), "PlatformRevenue 不能大于 PlatformFare ");
        busLine.setId(id);
        this.busLineService.updateById(busLine);
    }
}
