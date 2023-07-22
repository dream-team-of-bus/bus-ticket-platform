package com.bus.ticket.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.ticket.model.TcScheduleListRequest;
import com.bus.ticket.model.TcScheduleListResponse;
import com.bus.ticket.service.TcApiService;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/22
 */
@RequestMapping("/schedule")
@RestController
public class ScheduleController {

    @Resource
    private TcApiService tcApiService;

    @PostMapping("/list")
    public TcScheduleListResponse queryList(@RequestBody TcScheduleListRequest request) {
        return tcApiService.scheduleList(request);
    }

}
