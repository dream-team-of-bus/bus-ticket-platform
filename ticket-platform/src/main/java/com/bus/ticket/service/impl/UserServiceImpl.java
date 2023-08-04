package com.bus.ticket.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.ticket.dao.UserDao;
import com.bus.ticket.entity.User;
import com.bus.ticket.service.UserService;

/**
 * (User)表服务实现类
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}
