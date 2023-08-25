package com.bus.ticket.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.ticket.dao.OrderRecordMapper;
import com.bus.ticket.entity.OrderRecord;
import com.bus.ticket.service.OrderRecordService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author honglixiang
 * @since 2023-08-03 04:57:35
 */
@Service
public class OrderRecordServiceImpl extends ServiceImpl<OrderRecordMapper, OrderRecord> implements OrderRecordService {

}
