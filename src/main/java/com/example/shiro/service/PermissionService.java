package com.example.shiro.service;

import com.example.shiro.entity.Permission;
import java.util.List;
import java.util.Set;

/**
 * (Permission)表服务接口
 *
 * @author luffy
 * @since 2020-06-08 22:19:56
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    Permission queryById(Integer pid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pid);

    List<Permission> queryByRID(Integer rid);

}