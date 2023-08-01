package com.bus.ticket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bus.ticket.dao.BusLineDriverUserDao;
import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.BusLineDriverUserPageQuery;
import com.bus.ticket.service.BusLineDriverUserService;

/**
 * 线路关联的司机信息(BusLineDriverUser)表服务实现类
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:52
 */
@Service("busLineDriverUserService")
public class BusLineDriverUserServiceImpl implements BusLineDriverUserService {
    @Resource
    private BusLineDriverUserDao busLineDriverUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public BusLineDriverUser queryById(Integer id) {
        return this.busLineDriverUserDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    @Override
    public PageBean<BusLineDriverUser> queryByPage(BusLineDriverUserPageQuery pageQuery) {
        int total = this.busLineDriverUserDao.count(pageQuery);
        List<BusLineDriverUser> list = this.busLineDriverUserDao.queryAllByLimit(pageQuery);
        return new PageBean<>(pageQuery, list, total);
    }

    /**
     * 列表查询
     * 
     * @param pageQuery
     * @return
     */
    @Override
    public List<BusLineDriverUser> query(BusLineDriverUserPageQuery pageQuery) {
        return this.busLineDriverUserDao.queryAll(pageQuery);
    }

    /**
     * 新增数据
     *
     * @param busLineDriverUser
     *            实例对象
     * @return 实例对象
     */
    @Override
    public BusLineDriverUser insert(BusLineDriverUser busLineDriverUser) {
        this.busLineDriverUserDao.insert(busLineDriverUser);
        return busLineDriverUser;
    }

    /**
     * 修改数据
     *
     * @param busLineDriverUser
     *            实例对象
     * @return 实例对象
     */
    @Override
    public BusLineDriverUser update(BusLineDriverUser busLineDriverUser) {
        this.busLineDriverUserDao.update(busLineDriverUser);
        return this.queryById(busLineDriverUser.getId());
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
        return this.busLineDriverUserDao.deleteById(id) > 0;
    }
}
