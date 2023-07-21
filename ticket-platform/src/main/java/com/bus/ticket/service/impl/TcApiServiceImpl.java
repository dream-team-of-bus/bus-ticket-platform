package com.bus.ticket.service.impl;

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
        Assert.notNull(request.getDeparture(), "Departure");
        Assert.notNull(request.getDestination(), "Destination");
        Assert.notNull(request.getDepartureDate(), "DepartureDate");
        Assert.notNull(request.getDepartureStation(), "DepartureStation");
        Assert.notNull(request.getArrivalStation(), "ArrivalStation");

        String resultJson = HttpUtil.post(TcApiUrl.SCHEDULE_LIST_URL, JSONUtils.toJSONStringIgnoreErrors(request));
        TcScheduleListResponse response = JSONUtils.parseIgnoreErrors(resultJson, TcScheduleListResponse.class);
        return response;
    }
}
