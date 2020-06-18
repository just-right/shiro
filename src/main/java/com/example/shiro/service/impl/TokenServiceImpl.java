package com.example.shiro.service.impl;

import com.example.shiro.service.TokenService;
import com.example.shiro.service.util.TokenUserRedisUtils;
import com.example.shiro.service.util.UserTokenRedisUtils;
import org.springframework.stereotype.Service;

/**
 * Token管理类
 * @className: TokenServiceImpl
 * @description
 * @author: luffy
 * @date: 2020/6/16 12:32
 * @version:V1.0
 */
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * 生成Token
     * @param uid
     * @return
     */
    @Override
    public String createUserToken(Integer uid) {
        return UserTokenRedisUtils.setToken(uid);
    }

    /**
     * 删除token
     * @param uid
     */
    @Override
    public void delUserToken(Integer uid) {
        UserTokenRedisUtils.delToken(uid);
    }

    @Override
    public void createTokenUser(Integer uid, String token) {
        TokenUserRedisUtils.setToken(uid,token);
    }

    @Override
    public void delTokenUser(String token) {
        TokenUserRedisUtils.delToken(token);
    }


    @Override
    public String getUserToken(Integer uid) {
        return UserTokenRedisUtils.getValueByKey(uid);
    }

    @Override
    public String getTokenUser(String token) {
        return TokenUserRedisUtils.getValueByKey(token);
    }
}
