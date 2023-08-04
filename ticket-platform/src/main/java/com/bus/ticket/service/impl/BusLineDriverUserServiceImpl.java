package com.bus.ticket.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.ticket.dao.BusLineDriverUserDao;
import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.service.BusLineDriverUserService;

/**
 * 线路关联的司机信息(BusLineDriverUser)表服务实现类
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:52
 */
@Service
public class BusLineDriverUserServiceImpl extends ServiceImpl<BusLineDriverUserDao, BusLineDriverUser>
    implements BusLineDriverUserService {

}
