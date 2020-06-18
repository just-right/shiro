package com.example.shiro.service;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro.entity.LoginDto;
import com.example.shiro.entity.User;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author luffy
 * @since 2020-06-08 22:19:06
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

    User queryByUserName(String userName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

    JSONObject login(LoginDto dto);

    JSONObject logout(String token);

}