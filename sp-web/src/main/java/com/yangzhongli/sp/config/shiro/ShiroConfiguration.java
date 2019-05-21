package com.yangzhongli.sp.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration // 等价于beans
public class ShiroConfiguration {
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> fMap=new HashMap<>();
        //拦截页面
        fMap.put("/all","authc");
        fMap.put("/one","authc");
        //拦截未授权
        fMap.put("/all","perms[user:all]");
        fMap.put("/one","perms[user:one]");
        //被拦截返回登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //授权拦截返回页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/permission");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fMap);
        return shiroFilterFactoryBean;
    }
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}