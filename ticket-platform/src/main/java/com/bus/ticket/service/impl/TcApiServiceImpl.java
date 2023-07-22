package com.bus.ticket.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bus.ticket.constant.TcApiUrl;
import com.bus.ticket.model.TcScheduleListRequest;
import com.bus.ticket.model.TcScheduleListResponse;
import com.bus.ticket.service.TcApiService;
import com.bus.ticket.util.JSONUtils;

import cn.hutool.http.HttpUtil;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/18
 */
@Service
public class TcApiServiceImpl implements TcApiService {

    @Override
    public TcScheduleListResponse scheduleList(TcScheduleListRequest request) {
        Assert.notNull(request, "request");
        Assert.notNull(request.getDeparture(), "Departure 不能为空");
        Assert.notNull(request.getDestination(), "Destination 不能为空");
        Assert.notNull(request.getDepartureDate(), "DepartureDate 不能为空");
        Assert.notNull(request.getDepartureStation(), "DepartureStation 不能为空");
        Assert.notNull(request.getArrivalStation(), "ArrivalStation 不能为空");

        LocalDate localDate = LocalDate.parse(request.getDepartureDate());
        if (localDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("不支持今天之前的时间车次查询");
        }

        String resultJson = HttpUtil.post(TcApiUrl.SCHEDULE_LIST_URL, JSONUtils.toJSONStringIgnoreErrors(request));
        TcScheduleListResponse response = JSONUtils.parseIgnoreErrors(resultJson, TcScheduleListResponse.class);
        return response;
    }
}
