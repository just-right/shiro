package com.example.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro.entity.LoginDto;
import com.example.shiro.entity.User;
import com.example.shiro.dao.UserDao;
import com.example.shiro.service.TokenService;
import com.example.shiro.service.UserService;
import com.example.shiro.service.util.TokenUserRedisUtils;
import com.example.shiro.service.util.TokenUtils;
import com.example.shiro.service.util.UserTokenRedisUtils;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author luffy
 * @since 2020-06-08 22:19:07
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uid) {
        return this.userDao.queryById(uid);
    }

    @Override
    public User queryByUserName(String userName) {
        return this.userDao.queryByUserName(userName);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        //设置盐
        String salt = UUID.randomUUID().toString();
        String pwd = new Md5Hash(user.getPassword(), salt, IShiroConst.HASH_ITERATIONS).toString();
        user.setSalt(salt);
        user.setPassword(pwd);
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDao.deleteById(uid) > 0;
    }


    /**
     * 用户登录
     * @param dto
     * @return
     */
    @Transactional
    @Override
    public JSONObject login(LoginDto dto) {
        JSONObject result = new JSONObject();
        Integer uid = queryByUserName(dto.getUserName()).getUid();

        String oldToken = tokenService.getUserToken(uid);
        /**
         * 删除旧Token信息
         * { token: userId }
         * { userId: [tokenList] }
         */
        tokenService.delUserToken(uid);
        tokenService.delTokenUser(oldToken);
        /**
         * 创建新Token信息
         */
        String token = tokenService.createUserToken(uid);
        tokenService.createTokenUser(uid,token);
        result.put("data", "login success!");
        result.put("token", token);
        return result;
    }

    /**
     * 用户登出
     * @param token
     * @return
     */
    @Override
    public JSONObject logout(String token) {
        JSONObject result = new JSONObject();
        String uid = TokenUserRedisUtils.getValueByKey(token);
/**
 * 删除Token信息
 */
tokenService.delUserToken(Integer.valueOf(uid));
tokenService.delTokenUser(token);
        result.put("data", "logout success!");
        return result;
    }
}