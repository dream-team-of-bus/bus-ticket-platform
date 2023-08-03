package com.bus.ticket.service;

import com.bus.ticket.entity.User;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.UserPageQuery;

/**
 * (User)表服务接口
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    PageBean<User> queryByPage(UserPageQuery pageQuery);

    /**
     * 新增数据
     *
     * @param user
     *            实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user
     *            实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据微信OpenId查询用户
     * 
     * @param openId
     * @return
     */
    User getByOpenId(String openId);

    /**
     * 根据电话号码获取用户
     * 
     * @param mobilePhone
     * @return
     */
    User getByMobile(String mobilePhone);
}
