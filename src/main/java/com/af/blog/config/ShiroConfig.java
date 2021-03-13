package com.af.blog.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.af.blog.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shiro 配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 注入自定义realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        UserRealm userRealm = new UserRealm();
        // 注入凭证匹配器
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    /**
     * 注入安全管理器
     * @param userRealm
     * @return
     */
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 注入哈希凭证匹配器，设置哈希算法为 md5
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager);
        factoryBean.setLoginUrl("/login");
        factoryBean.setSuccessUrl("/success");
        // 设置未授权页面
        factoryBean.setUnauthorizedUrl("/login");
        // 权限设置
        /*
        anon：无需认证
        authc：必须认证
        user：必须拥有 记住我 功能
        perms：拥有对某个资源的权限
        roles：拥有某个角色权限
         */
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/back/**", "roles[站长]");     // 必须拥有admin角色
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }

    /**
     * 开启shiro标签
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
