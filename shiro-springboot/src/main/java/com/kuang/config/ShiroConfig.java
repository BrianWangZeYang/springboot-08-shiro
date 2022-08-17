package com.kuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    @Bean
    //ShiroFilterFactoryBean:3
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
        * anon ：无需认证就可以访问
        * anthc ：必须认证了才能访问
        * user ：必须拥有记住我功能才能用
        * perms ：拥有对某个资源的权限才能访问
        * role ：拥有某个角色权限才能访问
        * */
        Map<String,String> filterMap = new LinkedHashMap<>();

        //filterMap.put("/user/add","authc");
        // filterMap.put("/user/update","authc");
        //授权正常情况下，没有授权会跳到未授权页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/*","authc");
        filterMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterMap);

        //如果没有权限，设置登陆的请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");



        return bean;
    }


    //DeafaultWebSecurityManager:2L
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建realm对象，需要自定义类：1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合ShiroDialect：用来整合shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
