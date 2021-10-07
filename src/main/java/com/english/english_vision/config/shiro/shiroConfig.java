package com.english.english_vision.config.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author
 * @Description
 * @Date 8.31
 **/
@Configuration
public class shiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("security") DefaultWebSecurityManager manager)
    {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);//设置安全管理器
        //可以添加shiro的内置过滤器
        //anon:无需认证就可以访问 authc:必须认证了才能用 oerms:拥有对某个资源的权限才能访问
       bean.setLoginUrl("/user/login");//设置登录路径
        Map<String,String> map = new HashMap<>();
        map.put("/user/add","anon");
        map.put("/user/update","authc");
        map.put("/login/update","authc");
        map.put("/0.png","anon");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean(name="security")
    public DefaultWebSecurityManager getDefaultManager(@Qualifier("realm")UserRealm userRealm){
     DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
     //关联自己的realm
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }
    @Bean(name = "realm")
    //创建realm对象
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
