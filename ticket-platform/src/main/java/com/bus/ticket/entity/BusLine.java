package com.bus.ticket.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 汽车线路(BusLine)实体类
 *
 * @author honglixiang
 * @since 2023-07-31 14:58:03
 */
@ApiModel("汽车线路实体类")
public class BusLine implements Serializable {
    private static final long serialVersionUID = 473723037162924241L;

    private Integer id;

    @ApiModelProperty(value = "出发城市")
    private String departure;

    @ApiModelProperty(value = "目的城市")
    private String destination;

    @ApiModelProperty(value = "始发站")
    private String departureStation;

    @ApiModelProperty(value = "到达站")
    private String arrivalStation;

    @ApiModelProperty(value = "车站票价，单位分")
    private Long stationFare;

    @ApiModelProperty(value = "平台票价，单位分")
    private Long platformFare;

    @ApiModelProperty(value = "平台收入，单位分")
    private Long platformRevenue;

    private Date creationTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public Long getStationFare() {
        return stationFare;
    }

    public void setStationFare(Long stationFare) {
        this.stationFare = stationFare;
    }

    public Long getPlatformFare() {
        return platformFare;
    }

    public void setPlatformFare(Long platformFare) {
        this.platformFare = platformFare;
    }

    public Long getPlatformRevenue() {
        return platformRevenue;
    }

    public void setPlatformRevenue(Long platformRevenue) {
        this.platformRevenue = platformRevenue;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
