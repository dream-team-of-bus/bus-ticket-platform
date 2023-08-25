package com.bus.ticket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/17
 */
@ApiModel("同程车次查询请求实体类")
@NoArgsConstructor
@Data
public class TcScheduleListRequest {

    @ApiModelProperty(value = "出发城市")
    @JsonProperty("departure")
    private String departure;

    @ApiModelProperty(value = "达到城市")
    @JsonProperty("destination")
    private String destination;

    @ApiModelProperty(value = "日期 yyyy-MM-dd")
    @JsonProperty("departureDate")
    private String departureDate;

    @ApiModelProperty(value = "出发车站")
    @JsonProperty("departureStation")
    private String departureStation;

    @ApiModelProperty(value = "到达车站")
    @JsonProperty("arrivalStation")
    private String arrivalStation;

    @ApiModelProperty(value = "当前第几页")
    @JsonProperty("page")
    private Integer page = 1;

    @ApiModelProperty(value = "分页大小")
    @JsonProperty("pageSize")
    private Integer pageSize = 50;

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
