package com.qf.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.qf.realm.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    //配置realm
    @Bean
    public ShiroRealm getShiroRealm(){
        return new ShiroRealm();
    }

    //配置SecurityManager
    @Bean
    public SecurityManager getSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getShiroRealm());
        return securityManager;
    }

    //目的是在thymeleaf中使用shiro的自定义标签
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    //配置ShiroFilter过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        //配置ShiroFilter工厂对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未认证跳转URL
        shiroFilterFactoryBean.setLoginUrl("/user/afterLogin");
        //未授权跳转的URL
        shiroFilterFactoryBean.setUnauthorizedUrl("refuse");
        //创建有序的map集合
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("/js/**","anon");
        linkedHashMap.put("/css/**","anon");
        linkedHashMap.put("/fonts/**","anon");
        linkedHashMap.put("/images/**","anon");
        linkedHashMap.put("/layui/**","anon");
        linkedHashMap.put("/user/afterLogin", "anon");
        linkedHashMap.put("/user/afterRegister", "anon");
        //授权过滤配置
        linkedHashMap.put("/car/toCar", "perms[user:query]");
        linkedHashMap.put("/city/toCity", "perms[user:query]");
        linkedHashMap.put("/user/toUser", "perms[user:query]");
        linkedHashMap.put("/score/toScore", "perms[user:query]");
        linkedHashMap.put("/backcar/toBackCar", "perms[user:query]");
        linkedHashMap.put("/car/toCarUpdate", "perms[user:update]");
        linkedHashMap.put("/city/toCityUpdate", "perms[user:update]");
        linkedHashMap.put("/car/toCarUpdate", "perms[user:add]");
        linkedHashMap.put("/city/toCityUpdate", "perms[user:add]");
        linkedHashMap.put("/car/del", "perms[user:delete]");
        linkedHashMap.put("/city/delCity", "perms[user:delete]");
        linkedHashMap.put("/score/delScore", "perms[user:delete]");
        //退出登录
        linkedHashMap.put("/user/afterLogout","logout");

        //必须放在所有权限设置的最后，所有url都必须认证通过才可以访问
        //后台权限设置
        linkedHashMap.put("/car/toCar","authc");
        linkedHashMap.put("/city/toCity","authc");
        linkedHashMap.put("/user/toUser","authc");
        linkedHashMap.put("/backcar/toBackCar","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        return shiroFilterFactoryBean;
    }
}
