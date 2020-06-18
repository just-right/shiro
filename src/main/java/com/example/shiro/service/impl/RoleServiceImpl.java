package com.example.shiro.service.impl;

import com.example.shiro.entity.Role;
import com.example.shiro.dao.RoleDao;
import com.example.shiro.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * (Role)表服务实现类
 *
 * @author luffy
 * @since 2020-06-08 22:19:35
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param rid 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer rid) {
        return this.roleDao.queryById(rid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRid());
    }

    /**
     * 通过主键删除数据
     *
     * @param rid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer rid) {
        return this.roleDao.deleteById(rid) > 0;
    }

    @Override
    public List<Role> queryByUserID(Integer uid) {
        return this.roleDao.queryByUID(uid);
    }
}