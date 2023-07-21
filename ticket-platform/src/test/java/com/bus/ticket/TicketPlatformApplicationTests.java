package com.bus.ticket;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bus.ticket.model.TcScheduleListRequest;
import com.bus.ticket.model.TcScheduleListResponse;
import com.bus.ticket.service.TcApiService;

@SpringBootTest
class TicketPlatformApplicationTests {

    @Resource
    private TcApiService tcApiService;

    @Test
    void contextLoads() {
        TcScheduleListRequest request = new TcScheduleListRequest();
        request.setDeparture("澧县");
        request.setDestination("长沙");
        request.setDepartureDate("2023-07-19");
        request.setDepartureStation("澧县汽车站");
        request.setArrivalStation("长沙汽车西站");
        request.setPage(1);
        request.setPageSize(50);
        TcScheduleListResponse response = tcApiService.scheduleList(request);
        System.err.println(response.getHeader().getIsSuccess());

        for (TcScheduleListResponse.BodyVo.ScheduleVo scheduleVo : response.getBody().getSchedule()) {
            System.out.println(scheduleVo);
        }
    }

}
