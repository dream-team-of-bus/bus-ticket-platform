package com.bus.ticket.service;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/16
 */
public interface BizOrderService {

    /**
     * 订单确认
     * 
     * @param orderId
     * @param driverUserId
     */
    void confirm(Integer orderId, Integer driverUserId);
}
