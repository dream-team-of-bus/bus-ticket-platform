package com.bus.ticket.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

        @NoArgsConstructor
        @Data
        public static class ScheduleVo {
            // @JsonProperty("scheduleId")
            // private String scheduleId;
            // @JsonProperty("scheduleNo")
            // private String scheduleNo;
            @JsonProperty("coachType")
            private String coachType;
            @JsonProperty("coachNo")
            private String coachNo;
            @JsonProperty("depId")
            private Integer depId;
            @JsonProperty("desId")
            private Integer desId;
            @JsonProperty("departure")
            private String departure;
            @JsonProperty("dptStation")
            private String dptStation;
            @JsonProperty("destination")
            private String destination;
            @JsonProperty("arrStation")
            private String arrStation;
            @JsonProperty("dptDateTime")
            private Long dptDateTime;
            /**
             * 发车日期 yyyy-MM-dd
             */
            @JsonProperty("dptDate")
            private String dptDate;
            /**
             * 发车时间
             */
            @JsonProperty("dptTime")
            private String dptTime;
            @JsonProperty("studentPrice")
            private Double studentPrice;
            @JsonProperty("canNotBookingType")
            private Integer canNotBookingType;
            @JsonProperty("bookingDesc")
            private String bookingDesc;
            /**
             * 是否能购票
             */
            @JsonProperty("canBooking")
            private Boolean canBooking;
            @JsonProperty("bookingType")
            private Integer bookingType;
            @JsonProperty("timePeriodType")
            private Integer timePeriodType;
            @JsonProperty("runTime")
            private String runTime;
            @JsonProperty("runTimeDesc")
            private String runTimeDesc;
            /**
             * 车票价格
             */
            @JsonProperty("ticketPrice")
            private Double ticketPrice;
            @JsonProperty("distance")
            private String distance;
            @JsonProperty("runningSchFlag")
            private Integer runningSchFlag;
            /**
             * 0 固定班车， 1 过路车
             */
            @JsonProperty("isPassingStation")
            private Integer isPassingStation;
            @JsonProperty("transferSchFlag")
            private Integer transferSchFlag;
            @JsonProperty("fuZiSchFlag")
            private Integer fuZiSchFlag;
            @JsonProperty("currDateTime")
            private String currDateTime;
            // @JsonProperty("militaryPrice")
            // private Integer militaryPrice;
            // @JsonProperty("disabledMilitaryPrice")
            // private Integer disabledMilitaryPrice;
            // @JsonProperty("elderPrice")
            // private Integer elderPrice;
            // @JsonProperty("childPrice")
            // private Integer childPrice;
            // @JsonProperty("doubleTripPrice")
            // private Integer doubleTripPrice;
            // @JsonProperty("travelPrice")
            // private Integer travelPrice;
            // @JsonProperty("routeActivityPrice")
            // private Integer routeActivityPrice;
            // @JsonProperty("runningStartTime")
            // private String runningStartTime;
            // @JsonProperty("runningEndTime")
            // private String runningEndTime;
            // @JsonProperty("dptStationInfo")
            // private DptStationInfoVo dptStationInfo;
            // @JsonProperty("arrStationInfo")
            // private ArrStationInfoVo arrStationInfo;
            /**
             * 剩余票数
             */
            @JsonProperty("ticketLeft")
            private String ticketLeft;
            @JsonProperty("scheduleTicketType")
            private Integer scheduleTicketType;
            // @JsonProperty("scheduleTicketTypeDesc")
            // private String scheduleTicketTypeDesc;
            // @JsonProperty("discount")
            // private Integer discount;
            // @JsonProperty("childDiscount")
            // private Integer childDiscount;
            // @JsonProperty("studentDiscount")
            // private Integer studentDiscount;
            // @JsonProperty("elderDiscount")
            // private Integer elderDiscount;
            // @JsonProperty("scheduleName")
            // private String scheduleName;
            // @JsonProperty("fareType")
            // private String fareType;

            // @JsonProperty("mixProjectTag")
            // private String mixProjectTag;
            // @JsonProperty("chartered")
            // private Boolean chartered;
            // @JsonProperty("suggestOrder")
            // private Integer suggestOrder;
            // @JsonProperty("ExtraSchFlag")
            // private Integer extraSchFlag;
            // @JsonProperty("expandData")
            // private ExpandDataVo expandData;
            // @JsonProperty("tagTips")
            // private TagTipsVo tagTips;

            /*@NoArgsConstructor
            @Data
            public static class DptStationInfoVo {
                @JsonProperty("id")
                private Integer id;
                @JsonProperty("ticketDelivery")
                private String ticketDelivery;
                @JsonProperty("takeTicketAddress")
                private String takeTicketAddress;
                @JsonProperty("takeTicketStartTime")
                private String takeTicketStartTime;
                @JsonProperty("takeTicketEndTime")
                private String takeTicketEndTime;
                @JsonProperty("ticketType")
                private String ticketType;
                @JsonProperty("stationPic")
                private String stationPic;
                @JsonProperty("stationLevel")
                private Integer stationLevel;
                @JsonProperty("StationName")
                private String stationName;
                @JsonProperty("StationAddress")
                private String stationAddress;
                @JsonProperty("ContactPhoneNo")
                private String contactPhoneNo;
                @JsonProperty("Longitude")
                private Double longitude;
                @JsonProperty("Latitude")
                private Double latitude;
            }*/

            /*@NoArgsConstructor
            @Data
            public static class ArrStationInfoVo {
                @JsonProperty("id")
                private Integer id;
                @JsonProperty("ticketDelivery")
                private String ticketDelivery;
                @JsonProperty("takeTicketAddress")
                private String takeTicketAddress;
                @JsonProperty("takeTicketStartTime")
                private String takeTicketStartTime;
                @JsonProperty("takeTicketEndTime")
                private String takeTicketEndTime;
                @JsonProperty("ticketType")
                private String ticketType;
                @JsonProperty("stationPic")
                private String stationPic;
                @JsonProperty("stationLevel")
                private Integer stationLevel;
                @JsonProperty("StationName")
                private String stationName;
                @JsonProperty("StationAddress")
                private String stationAddress;
                @JsonProperty("ContactPhoneNo")
                private String contactPhoneNo;
                @JsonProperty("Longitude")
                private Double longitude;
                @JsonProperty("Latitude")
                private Double latitude;
            }*/

            // @NoArgsConstructor
            // @Data
            // public static class ExpandDataVo {
            // @JsonProperty("scheduleType")
            // private String scheduleType;
            // @JsonProperty("runTime")
            // private String runTime;
            // @JsonProperty("BackwardOther")
            // private String backwardOther;
            // @JsonProperty("supplierIdList")
            // private List<Integer> supplierIdList;
            // }

            // @NoArgsConstructor
            // @Data
            // public static class TagTipsVo {
            // @JsonProperty("tags")
            // private String tags;
            // @JsonProperty("infos")
            // private List<InfosVo> infos;
            //
            // @NoArgsConstructor
            // @Data
            // public static class InfosVo {
            // @JsonProperty("title")
            // private String title;
            // @JsonProperty("info")
            // private String info;
            // }
            // }
        }
    }
}
