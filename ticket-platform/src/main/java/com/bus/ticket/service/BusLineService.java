package com.bus.ticket.service;

import com.bus.ticket.entity.BusLine;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.BusLinePageQuery;

/**
 * 汽车线路(BusLine)表服务接口
 *
 * @author makejava
 * @since 2023-07-28 09:38:37
 */
public interface BusLineService {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    BusLine queryById(Integer id);

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    PageBean<BusLine> queryByPage(BusLinePageQuery pageQuery);

    /**
     * 新增数据
     *
     * @param busLine
     *            实例对象
     * @return 实例对象
     */
    BusLine insert(BusLine busLine);

    /**
     * 修改数据
     *
     * @param busLine
     *            实例对象
     * @return 实例对象
     */
    BusLine update(BusLine busLine);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
