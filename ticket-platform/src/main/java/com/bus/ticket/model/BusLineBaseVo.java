package com.bus.ticket.model;

import org.springframework.beans.BeanUtils;

import com.bus.ticket.entity.BusLine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/31
 */
@Data
@ApiModel("汽车线路基础实体类")
public class BusLineBaseVo {

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

    public static BusLineBaseVo convert(BusLine line) {
        if (line == null) {
            return null;
        }
        BusLineBaseVo vo = new BusLineBaseVo();
        BeanUtils.copyProperties(line, vo);
        return vo;
    }

    public BusLine to() {
        BusLine line = new BusLine();
        line.setDeparture(departure);
        line.setDestination(destination);
        line.setDepartureStation(departureStation);
        line.setArrivalStation(arrivalStation);
        line.setStationFare(stationFare);
        line.setPlatformFare(platformFare);
        return line;
    }
}
