package com.example.shiro.service;

import com.example.shiro.entity.Role;
import java.util.List;
import java.util.Set;

/**
 * (Role)表服务接口
 *
 * @author luffy
 * @since 2020-06-08 22:19:35
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param rid 主键
     * @return 实例对象
     */
    Role queryById(Integer rid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param rid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer rid);

    List<Role> queryByUserID(Integer uid);

}