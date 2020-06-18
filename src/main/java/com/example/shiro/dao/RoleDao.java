package com.example.shiro.dao;

import com.example.shiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Set;

/**
 * (Role)表数据库访问层
 *
 * @author luffy
 * @since 2020-06-08 22:19:35
 */
@Mapper
public interface RoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param rid 主键
     * @return 实例对象
     */
    Role queryById(Integer rid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param role 实例对象
     * @return 对象列表
     */
    List<Role> queryAll(Role role);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param rid 主键
     * @return 影响行数
     */
    int deleteById(Integer rid);

    /**
     * 通过用户ID查询角色集合
     * @param uid
     * @return
     */
    List<Role> queryByUID(Integer uid);

}