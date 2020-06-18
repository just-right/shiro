package com.example.shiro.controller;

import com.example.shiro.entity.UserRole;
import com.example.shiro.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserRole)表控制层
 *
 * @author luffy
 * @since 2020-06-08 22:20:17
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserRole selectOne(Integer id) {
        return this.userRoleService.queryById(id);
    }

}