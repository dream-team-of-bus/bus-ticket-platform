package com.bus.ticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.ticket.entity.BusLine;
import com.bus.ticket.model.query.BusLinePageQuery;

/**
 * 汽车线路(BusLine)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-28 09:38:37
 */
public interface BusLineDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    BusLine queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param pageQuery
     *            查询条件
     * @return 对象列表
     */
    List<BusLine> queryAllByLimit(BusLinePageQuery pageQuery);

    /**
     * 统计总行数
     *
     * @param pageQuery
     *            查询条件
     * @return 总行数
     */
    int count(BusLinePageQuery pageQuery);

    /**
     * 新增数据
     *
     * @param busLine
     *            实例对象
     * @return 影响行数
     */
    int insert(BusLine busLine);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities
     *            List<BusLine> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BusLine> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities
     *            List<BusLine> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException
     *             入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BusLine> entities);

    /**
     * 修改数据
     *
     * @param busLine
     *            实例对象
     * @return 影响行数
     */
    int update(BusLine busLine);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
