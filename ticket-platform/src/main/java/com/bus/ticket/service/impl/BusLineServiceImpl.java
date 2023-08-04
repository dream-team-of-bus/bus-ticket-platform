package com.bus.ticket.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.ticket.dao.BusLineDao;
import com.bus.ticket.entity.BusLine;
import com.bus.ticket.service.BusLineService;

/**
 * 汽车线路(BusLine)表服务实现类
 *
 * @author honglixiang
 * @since 2023-07-28 09:38:37
 */
@Service
public class BusLineServiceImpl extends ServiceImpl<BusLineDao, BusLine> implements BusLineService {

}
