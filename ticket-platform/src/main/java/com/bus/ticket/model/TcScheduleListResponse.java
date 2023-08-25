package com.bus.ticket.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/17
 */
@NoArgsConstructor
@Data
public class TcScheduleListResponse {

    @JsonProperty("header")
    private HeaderVo header;
    @JsonProperty("body")
    private BodyVo body;

    @NoArgsConstructor
    @Data
    public static class HeaderVo {
        @JsonProperty("isSuccess")
        private Boolean isSuccess;
        @JsonProperty("errCode")
        private String errCode;
        @JsonProperty("errMsg")
        private String errMsg;
        @JsonProperty("logId")
        private String logId;
    }

    @NoArgsConstructor
    @Data
    public static class BodyVo {

        @JsonProperty("schedule")
        private List<ScheduleVo> schedule;

        @JsonProperty("pageInfo")
        private PageInfoVo pageInfo;

        @NoArgsConstructor
        @Data
        public static class PageInfoVo {
            @JsonProperty("page")
            private Integer page;
            @JsonProperty("pageSize")
            private Integer pageSize;
            @JsonProperty("totalPage")
            private Integer totalPage;
            @JsonProperty("totalCount")
            private Integer totalCount;
        }

        @ApiModel("车次信息模型")
        @NoArgsConstructor
        @Data
        public static class ScheduleVo {

            @ApiModelProperty("是否能购票")
            @JsonProperty("canBooking")
            private Boolean canBooking;

            @ApiModelProperty("剩余票数")
            @JsonProperty("ticketLeft")
            private String ticketLeft;

            @ApiModelProperty("出发地")
            @JsonProperty("departure")
            private String departure;

            @ApiModelProperty("出发车站")
            @JsonProperty("dptStation")
            private String dptStation;

            @ApiModelProperty("目的地")
            @JsonProperty("destination")
            private String destination;

            @ApiModelProperty("目的地车站")
            @JsonProperty("arrStation")
            private String arrStation;

            @ApiModelProperty("出发时间")
            @JsonProperty("dptDateTime")
            private Long dptDateTime;

            @ApiModelProperty("出发日期")
            @JsonProperty("dptDate")
            private String dptDate;

            @ApiModelProperty("出发时间")
            @JsonProperty("dptTime")
            private String dptTime;

            @ApiModelProperty("0 固定班车， 1 过路车")
            @JsonProperty("isPassingStation")
            private Integer isPassingStation;

            @ApiModelProperty("车票价格")
            @JsonProperty("ticketPrice")
            private Double ticketPrice;

            @JsonProperty("studentPrice")
            private Double studentPrice;
            @JsonProperty("canNotBookingType")
            private Integer canNotBookingType;
            @JsonProperty("bookingDesc")
            private String bookingDesc;

            @JsonProperty("bookingType")
            private Integer bookingType;
            @JsonProperty("timePeriodType")
            private Integer timePeriodType;
            @JsonProperty("runTime")
            private String runTime;
            @JsonProperty("runTimeDesc")
            private String runTimeDesc;

            @JsonProperty("distance")
            private String distance;
            @JsonProperty("runningSchFlag")
            private Integer runningSchFlag;

            @JsonProperty("transferSchFlag")
            private Integer transferSchFlag;

            @JsonProperty("fuZiSchFlag")
            private Integer fuZiSchFlag;

            @JsonProperty("currDateTime")
            private String currDateTime;

            @JsonProperty("coachType")
            private String coachType;

            @JsonProperty("coachNo")
            private String coachNo;

            @JsonProperty("depId")
            private Integer depId;

            @JsonProperty("desId")
            private Integer desId;

            @JsonProperty("scheduleTicketType")
            private Integer scheduleTicketType;
        }
    }
}
