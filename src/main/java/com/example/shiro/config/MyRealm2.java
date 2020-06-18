package com.example.shiro.config;

import com.example.shiro.entity.MyAuthToken;
import com.example.shiro.entity.Permission;
import com.example.shiro.entity.Role;
import com.example.shiro.entity.User;
import com.example.shiro.service.PermissionService;
import com.example.shiro.service.RoleService;
import com.example.shiro.service.UserService;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 普通登陆 认证-授权 Realm
 * @className: MyRealm2
 * @description
 * @author: luffy
 * @date: 2020/6/16 13:13
 * @version:V1.0
 */
public class MyRealm2 extends AuthorizingRealm {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;


    @Override
    public String getName() {
        return IShiroConst.LOGIN_REALM_NAME;
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

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        MyAuthToken token = (MyAuthToken) authenticationToken;
        User user = userService.queryByUserName(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());
        if (user.getSalt() != null) {
            info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        }
        return info;
    }
}
