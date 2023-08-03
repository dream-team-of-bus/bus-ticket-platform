package com.bus.ticket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.bus.ticket.dao.BusLineDao;
import com.bus.ticket.entity.BusLine;
import com.bus.ticket.model.common.PageBean;
import com.bus.ticket.model.query.BusLinePageQuery;
import com.bus.ticket.service.BusLineService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 汽车线路(BusLine)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 09:38:37
 */
@Service("busLineService")
public class BusLineServiceImpl implements BusLineService {
    @Resource
    private BusLineDao busLineDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public BusLine queryById(Integer id) {
        return this.busLineDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     *            筛选条件
     * @return 查询结果
     */
    @Override
    public PageBean<BusLine> queryByPage(BusLinePageQuery pageQuery) {
        int total = this.busLineDao.count(pageQuery);
        List<BusLine> list = this.busLineDao.queryAllByLimit(pageQuery);
        return new PageBean<>(pageQuery, list, total);
    }

    @Override
    public List<BusLine> query(BusLinePageQuery pageQuery) {
        return this.busLineDao.queryAll(pageQuery);
    }

    /**
     * 新增数据
     *
     * @param busLine
     *            实例对象
     * @return 实例对象
     */
    @Override
    public BusLine insert(BusLine busLine) {
        Assert.notNull(busLine.getDeparture(), "Departure is null");
        Assert.notNull(busLine.getDestination(), "Destination is null");
        Assert.notNull(busLine.getDepartureStation(), "DepartureStation is null");
        Assert.notNull(busLine.getArrivalStation(), "ArrivalStation is null");
        Assert.notNull(busLine.getStationFare(), "StationFare is null");
        Assert.notNull(busLine.getPlatformFare(), "PlatformFare is null");
        Assert.notNull(busLine.getPlatformRevenue(), "PlatformRevenue is null");
        Assert.isTrue(busLine.getPlatformFare() >= busLine.getStationFare(), "PlatformFare 不能大于 StationFare");
        Assert.isTrue(busLine.getPlatformRevenue() >= busLine.getPlatformFare(), "PlatformRevenue 不能大于 PlatformFare ");
        this.busLineDao.insert(busLine);
        return busLine;
    }

    /**
     * 修改数据
     *
     * @param busLine
     *            实例对象
     * @return 实例对象
     */
    @Override
    public BusLine update(BusLine busLine) {
        this.busLineDao.update(busLine);
        return this.queryById(busLine.getId());
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
        return this.busLineDao.deleteById(id) > 0;
    }
}
