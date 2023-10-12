package com.bus.ticket.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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

    @Override
    public User getByMobile(String phone) {
        Assert.notNull(phone, "phone");
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getPhone, phone);
        return getOne(queryWrapper);
    }

    @Override
    public User getByOpenId(String openId) {
        Assert.notNull(openId, "openId");
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getWxOpenId, openId);
        return getOne(queryWrapper);
    }
}
