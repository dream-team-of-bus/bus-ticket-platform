package com.bus.ticket.service;

import java.util.List;

import com.bus.ticket.entity.BusLineDriverUser;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.BusLineDriverUserPageQuery;

/**
 * 线路关联的司机信息(BusLineDriverUser)表服务接口
 *
 * @author honglixiang
 * @since 2023-07-31 16:08:52
 */
public interface BusLineDriverUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    BusLineDriverUser queryById(Integer id);

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    PageBean<BusLineDriverUser> queryByPage(BusLineDriverUserPageQuery pageQuery);

    /**
     * 列表查询
     * 
     * @param pageQuery
     * @return
     */
    List<BusLineDriverUser> query(BusLineDriverUserPageQuery pageQuery);

    /**
     * 新增数据
     *
     * @param busLineDriverUser
     *            实例对象
     * @return 实例对象
     */
    BusLineDriverUser insert(BusLineDriverUser busLineDriverUser);

    /**
     * 修改数据
     *
     * @param busLineDriverUser
     *            实例对象
     * @return 实例对象
     */
    BusLineDriverUser update(BusLineDriverUser busLineDriverUser);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
