package com.example.shiro.config;

import com.example.shiro.filter.MyAuthFilter;
import com.example.shiro.setting.IShiroConst;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.*;

/**
 * Shiro相关配置
 * @className: ShiroConfig
 * @description
 * @author: luffy
 * @date: 2020/6/8 21:43
 * @version:V1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * Token认证-授权
     * @return
     */
    @Bean
    public Realm myRealm(){
        MyRealm realm =  new MyRealm();
        return realm;
    }

    /**
     * 普通登录认证-授权
     * @return
     */
    @Bean
    public Realm myRealm2(){
        MyRealm2 realm =  new MyRealm2();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 生命周期处理器
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启注解模式
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 权限管理
     * @param myRealm
     * @return
     */
    @Bean(value = "securityManager")
    public SessionsSecurityManager securityManager(@Qualifier("myRealm") Realm myRealm,@Qualifier("myRealm2") Realm myRealm2) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //指定多 realm  先配置Authenticator-顺序不能相反
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> list = Arrays.asList(myRealm,myRealm2);
        securityManager.setRealms(list);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }


    /**
     * 开启Shiro-aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 加密算法
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(IShiroConst.HASH_ALGORITHMNAME);//散列算法 --- 这里使用MD5算法
        hashedCredentialsMatcher.setHashIterations(IShiroConst.HASH_ITERATIONS);//散列的次数 --- md5(md5(""))
        return hashedCredentialsMatcher;
    }

    /**
     * Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        MyModularRealmAuthenticator modularRealmAuthenticator = new MyModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //auth过滤
        filterMap.put("auth", new MyAuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        /**
         * 可以从数据库读取 配置
         */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        /**
         * 登录 注册 不用权限控制
         */
        filterChainDefinitionMap.put(IShiroConst.LOGIN_URL, "anon");
        filterChainDefinitionMap.put(IShiroConst.REGISTER_URL, "anon");
        filterChainDefinitionMap.put("/**", "auth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


}
