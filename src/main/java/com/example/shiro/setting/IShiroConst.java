package com.example.shiro.setting;

/**
 * @className: IShiroConst
 * @description
 * @author: luffy
 * @date: 2020/6/15 12:35
 * @version:V1.0
 */
public interface IShiroConst {
    String LOGIN_URL = "/login";
    String REGISTER_URL = "/register";
    int HASH_ITERATIONS = 1024;
    String HASH_ALGORITHMNAME = "md5";
    Integer EXPIRE_TIME = 86400; //一天
    String LOGIN_REALM_NAME = "loginRealmName";
    String TOKEN_REALM_NAME = "tokenRealmName";
    String REQUEST_TOKEN_FIELD = "token";
}
