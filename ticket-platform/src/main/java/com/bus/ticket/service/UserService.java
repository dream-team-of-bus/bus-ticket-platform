package com.bus.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bus.ticket.entity.User;

/**
 * (User)表服务接口
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
public interface UserService extends IService<User> {

    /**
     * 根据电话号码查用户
     * 
     * @param phone
     * @return
     */
    User getByMobile(String phone);

    /**
     * 根据微信OpenID查询用户
     * 
     * @param openId
     * @return
     */
    User getByOpenId(String openId);
}
