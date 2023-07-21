package com.bus.ticket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/17
 */
@NoArgsConstructor
@Data
public class TcScheduleListRequest {

    /**
     * 出发城市
     */
    @JsonProperty("departure")
    private String departure;
    /**
     * 达到城市
     */
    @JsonProperty("destination")
    private String destination;
    /**
     * 日期 yyyy-MM-dd
     */
    @JsonProperty("departureDate")
    private String departureDate;

    /**
     * 出发车站
     */
    @JsonProperty("departureStation")
    private String departureStation;

    /**
     * 到达车站
     */
    @JsonProperty("arrivalStation")
    private String arrivalStation;
    /**
     * 第几页
     */
    @JsonProperty("page")
    private Integer page;
    /**
     * 分页大小
     */
    @JsonProperty("pageSize")
    private Integer pageSize;

    @JsonProperty("depId")
    private Integer depId;
    @JsonProperty("desId")
    private Integer desId;

    @JsonProperty("orderTime")
    private Integer orderTime;
    @JsonProperty("orderPrice")
    private Integer orderPrice;
    @JsonProperty("dptTimeSpan")
    private String dptTimeSpan;
    @JsonProperty("hasCategory")
    private Boolean hasCategory;
}
