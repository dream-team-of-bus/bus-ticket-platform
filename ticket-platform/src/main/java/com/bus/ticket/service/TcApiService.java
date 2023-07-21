package com.bus.ticket.service;

import com.bus.ticket.model.TcScheduleListRequest;
import com.bus.ticket.model.TcScheduleListResponse;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/18
 */
public interface TcApiService {

    /**
     * 车次查询
     * 
     * @param request
     * @return
     */
    TcScheduleListResponse scheduleList(TcScheduleListRequest request);
}
