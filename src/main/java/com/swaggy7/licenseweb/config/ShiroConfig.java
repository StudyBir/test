//package com.swaggy7.licenseweb.config;
//
//import com.swaggy7.licenseweb.utils.Realm;
//import org.apache.shiro.session.mgt.DefaultSessionManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Configuration
//public class ShiroConfig {
//    @Autowired
//    Realm realm;
//
//    @Bean
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        defaultWebSecurityManager.setRealm(realm);
//        defaultWebSecurityManager.setSessionManager(getDefaultWebSessionManager());
//        return defaultWebSecurityManager;
//    }
//
//
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        //添加过滤
//        Map<String, String> map = new LinkedHashMap<>();
//        //url的过滤种类
//        map.put("/user/login", "anon");
//        map.put("/licenseInfo/insertLicenseInfo", "roles[all]");
//        map.put("/am/insertAmInfo", "roles[all]");
//        map.put("/ep/insertEpInfo", "roles[all]");
//        map.put("/licenseInfo/modifyLicenseInfo", "roles[all]");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//
//        //设置登录成功后跳转的路径
////        shiroFilterFactoryBean.setLoginUrl("/user/login");
//        //设置未授权时跳转的路径
////        shiroFilterFactoryBean.setUnauthorizedUrl();
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public DefaultWebSessionManager getDefaultWebSessionManager(){
//        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        defaultWebSessionManager.setGlobalSessionTimeout(30 * 60 * 1000);
//        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
//        defaultWebSessionManager.setSessionIdCookieEnabled(true);
////        System.out.println("session time : " + defaultWebSessionManager.getGlobalSessionTimeout());
//        return defaultWebSessionManager;
//    }
//}
