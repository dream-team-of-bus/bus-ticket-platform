package com.bus.ticket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bus.ticket.dao.UserDao;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.UserPageQuery;
import com.bus.ticket.service.UserService;

/**
 * (User)表服务实现类
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    @Override
    public PageBean<User> queryByPage(UserPageQuery pageQuery) {
        int total = this.userDao.count(pageQuery);
        List<User> list = this.userDao.queryAllByLimit(pageQuery);
        return new PageBean<>(pageQuery, list, total);
    }

    /**
     * 新增数据
     *
     * @param user
     *            实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user
     *            实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User getByOpenId(String openId) {
        Assert.notNull(openId, "openId is null");
        UserPageQuery query = new UserPageQuery();
        query.setWxOpenId(openId);
        List<User> users = this.userDao.queryAllByLimit(query);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getByMobile(String mobilePhone) {
        Assert.notNull(mobilePhone, "mobilePhone is null");
        UserPageQuery query = new UserPageQuery();
        query.setPhone(mobilePhone);
        List<User> users = this.userDao.queryAllByLimit(query);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
