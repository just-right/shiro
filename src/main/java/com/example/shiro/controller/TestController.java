package com.example.shiro.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shiro.entity.LoginDto;
import com.example.shiro.entity.MyAuthToken;
import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @className: TestController
 * @description
 * @author: luffy
 * @date: 2020/6/9 20:21
 * @version:V1.0
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * 普通登陆
     * @param dto
     * @return
     */
    @PostMapping(value = "/login")
    public String userLogin(@RequestBody LoginDto dto) {
        JSONObject jsonObject = new JSONObject();
        String userName = dto.getUserName();
        String passWord = dto.getPassWord();
        Subject userSubject = SecurityUtils.getSubject();
        try {
            userSubject.login(new MyAuthToken(userName, passWord,null, IShiroConst.LOGIN_REALM_NAME));
            Integer uid = Optional.ofNullable(userService.queryByUserName(userName)).map(e->e.getUid()).orElse(null);
            jsonObject = userService.login(dto);
        } catch (AuthenticationException e) {
            jsonObject.put("data", "account or pwd is error!");
        }
        return jsonObject.toString();
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @GetMapping(value = "/logout")
    public String userLogout(@RequestParam(value = "token")String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject = userService.logout(token);
        return jsonObject.toString();
    }

    /**
     * 注册
     * @param dto
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@RequestBody LoginDto dto) {
        JSONObject jsonObject = new JSONObject();
        String userName = dto.getUserName();
        String passWord = dto.getPassWord();
        User user = new User();
        user.setName(userName);
        user.setPassword(passWord);
        userService.insert(user);
        jsonObject.put("data", "success");
        return jsonObject.toString();
    }

    /**
     * 用户管理-删除
     * @return
     */
    @RequiresPermissions(value = {"permission:user:manage:menu","permission:user:manage:del"})
    @GetMapping(value = "/user/del")
    public String delUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "success");
        return jsonObject.toString();
    }

    /**
     * 文章管理-查询
     * @return
     */
    @RequiresPermissions(value = {"permission:article:manage:menu"})
    @GetMapping(value = "/article/query")
    public String queryArticle() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "success");
        return jsonObject.toString();
    }

}
