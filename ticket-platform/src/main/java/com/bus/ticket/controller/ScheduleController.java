package com.bus.ticket.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.ticket.model.TcScheduleListRequest;
import com.bus.ticket.model.TcScheduleListResponse;
import com.bus.ticket.service.TcApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/22
 */
@Api(tags = "车次管理")
@RequestMapping("/schedule")
@RestController
public class ScheduleController {

    @Resource
    private TcApiService tcApiService;

    @ApiOperation(value = "车次查询", notes = "车次查询")
    @PostMapping("/list")
    public List<TcScheduleListResponse.BodyVo.ScheduleVo>
        queryList(@ApiParam(value = "请求参数", required = true) @RequestBody TcScheduleListRequest request) {
        TcScheduleListResponse response = tcApiService.scheduleList(request);
        if (!response.getHeader().getIsSuccess()) {
            throw new RuntimeException("车次查询失败：" + response.getHeader().getErrMsg());
        }
        return response.getBody().getSchedule();
    }

}
