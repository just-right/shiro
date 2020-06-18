package com.example.shiro.config;

import com.example.shiro.entity.MyAuthToken;
import com.example.shiro.entity.Permission;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;
import com.example.shiro.service.*;
import com.example.shiro.service.util.TokenUserRedisUtils;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Token 认证-授权 Realm
 * @className: MyRealm
 * @description
 * @author: luffy
 * @date: 2020/6/8 21:40
 * @version:V1.0
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return IShiroConst.TOKEN_REALM_NAME;
    }

    /**
     * 认证 - 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    MyAuthToken authToken = (MyAuthToken) authenticationToken;
    String token = authToken.getToken();
    /**
     * 存缓存中获取token token为key,uid为value
     */
    if (StringUtils.isEmpty(token) || !TokenUserRedisUtils.isExistedKey(token) || StringUtils.isEmpty(TokenUserRedisUtils.getValueByKey(token))){
        throw new IncorrectCredentialsException("token失效，请重新登录");
    }
    String uid = TokenUserRedisUtils.getValueByKey(token);
    assert uid != null;
    User user =  userService.queryById(Integer.parseInt(uid));
    if (user == null){
        throw new UnknownAccountException("用户不存在!");
    }
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, getName());
    return info;
}

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new RuntimeException("principalCollection is null!");
        }
        User user = (User) getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = roleService.queryByUserID(user.getUid());
        if (roles == null || roles.size() <= 0){
            return info;
        }
        for (Role role : roles) {
            info.addRole(role.getRvalue());
            List<Permission> permissions = permissionService.queryByRID(role.getRid());
            if (permissions == null || permissions.size() <= 0){
                return info;
            }
            for (Permission permission : permissions) {
                info.addStringPermission(permission.getPvalue());
            }
        }
        return info;
    }
}

