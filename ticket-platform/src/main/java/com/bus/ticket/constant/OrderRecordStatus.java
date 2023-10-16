package com.bus.ticket.constant;

import lombok.Getter;

/**
 * 订单状态
 * 
 * @author honglixiang@tiduyun.com
 * @date 2023/10/16
 */
public enum OrderRecordStatus {

    WAITING_PAYMENT("waiting_payment", "待支付"), PAID("paid", "已支付"), COMPLETED("completed", "已完成"),
    REFUNDED("refunded", "已退款");

    @Getter
    private String code;

    @Getter
    private String name;

    OrderRecordStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
