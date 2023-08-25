package com.bus.ticket.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author honglixiang
 * @since 2023-08-03 04:57:35
 */
@ToString
@Getter
@Setter
@TableName("order_record")
public class OrderRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("线路ID")
    private Integer busLineId;

    @ApiModelProperty("出发地")
    private String departure;

    @ApiModelProperty("目的地")
    private String destination;

    @ApiModelProperty("始发站")
    private String departureStation;

    @ApiModelProperty("到达站")
    private String arrivalStation;

    @ApiModelProperty("发车时间")
    private LocalDateTime departureTime;

    @ApiModelProperty("支付金额，单位分")
    private Long paymentAmount;

    @ApiModelProperty("状态：待出行、已完成、已退票、退票中")
    private String status;

    @ApiModelProperty("台收入，单位分")
    private Long platformRevenue;

    @ApiModelProperty("司机收入，单位分")
    private Long driverRevenue;

    @ApiModelProperty("司机用户ID")
    private Integer driverUserId;

    @ApiModelProperty("订单确认时间")
    private LocalDateTime driverConfirmTime;

    private Integer creatorUserId;

    private LocalDateTime creationTime;

    private LocalDateTime updateTime;
}
