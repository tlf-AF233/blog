package com.af.blog.realm;


import com.af.blog.entity.User;
import com.af.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义 UserRealm 使用Shiro作登录验证
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户登录的信息
        Subject subject = SecurityUtils.getSubject();
        String currentUser = (String) subject.getPrincipal();
        User user = userService.selectAnUserByName(currentUser);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRoleName());
        return simpleAuthorizationInfo;
    }

    /**
     * 登录身份认证
     * @param token 令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 取得token
        String username = token.getPrincipal().toString();
        User user = userService.selectAnUserByName(username);
        if (user != null) {
            // 如果存在当前用户

            return new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
        }
        return null;
    }
}
