package com.bus.ticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bus.ticket.entity.User;
import com.bus.ticket.model.query.UserPageQuery;

/**
 * (User)表数据库访问层
 *
 * @author honglixiang
 * @since 2023-07-31 16:36:00
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param pageQuery
     *            查询条件
     * @return 对象列表
     */
    List<User> queryAllByLimit(UserPageQuery pageQuery);

    /**
     * 统计总行数
     *
     * @param pageQuery
     *            查询条件
     * @return 总行数
     */
    int count(UserPageQuery pageQuery);

    /**
     * 新增数据
     *
     * @param user
     *            实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities
     *            List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities
     *            List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException
     *             入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user
     *            实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
