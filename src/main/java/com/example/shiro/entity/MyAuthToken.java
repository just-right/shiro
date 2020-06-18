package com.example.shiro.entity;

import com.example.shiro.setting.IShiroConst;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义Token
 * @className: MyAuthToken
 * @description
 * @author: luffy
 * @date: 2020/6/16 11:17
 * @version:V1.0
 */
@Data
@NoArgsConstructor
public class MyAuthToken extends UsernamePasswordToken {
    private String token;
    private String loginType;

    public MyAuthToken(final String username, final String password,final String token,
                     final String loginType) {
        super(username, password);
        this.token = token;
        this.loginType = loginType;
    }

    /**
     * 祖父类  --- AuthenticationToken
     * Object getPrincipal();    --- 资源对象
     * Object getCredentials();  --- 比较对象
     * 1.如果是普通登陆 返回密码
     * 2.如果是Token访问 返回token
     * 目前只有两种Realm
     * ... ...
     * @return
     */
    @Override
    public Object getCredentials() {
        if (IShiroConst.TOKEN_REALM_NAME.equals(this.getLoginType())){
            return getToken();
        }
        return getPassword();
    }
}
