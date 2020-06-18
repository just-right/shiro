package com.example.shiro.controller;

import com.example.shiro.entity.Permission;
import com.example.shiro.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Permission)表控制层
 *
 * @author luffy
 * @since 2020-06-08 22:19:56
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(Integer id) {
        return this.permissionService.queryById(id);
    }

}