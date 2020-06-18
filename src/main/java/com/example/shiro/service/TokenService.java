package com.example.shiro.service;

/**
 * @className: TokenService
 * @description
 * @author: luffy
 * @date: 2020/6/16 12:31
 * @version:V1.0
 */
public interface TokenService {

    String getUserToken(Integer uid);

    String createUserToken(Integer uid);

    void delUserToken(Integer uid);

    String getTokenUser(String token);

    void createTokenUser(Integer uid,String token);

    void delTokenUser(String token);
}
