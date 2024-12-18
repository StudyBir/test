//package com.swaggy7.licenseweb.utils;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.swaggy7.licenseweb.entity.User;
//import com.swaggy7.licenseweb.service.impl.UserServiceImpl;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Realm extends AuthorizingRealm {
//
//    @Autowired
//    private UserServiceImpl userService;
//
//    //授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行了=>授权");
//        //记录用户的所有角色和权限
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();//权限信息
//
//        //获取当前登录的对象
//        Subject subject = SecurityUtils.getSubject();
//        //get的信息是认证返回值的第一个参数
//        User principal = (User) subject.getPrincipal();
//
//        //添加session过期时间
//        subject.getSession().setTimeout(30 * 60 * 1000);
//
////        System.out.println("session过期时间: " + subject.getSession().getTimeout());
//
//        //给对应的登录请求的User添加对应的权限(由数据库读取)
//        simpleAuthorizationInfo.addRole(principal.getUserRole());
//
//        return simpleAuthorizationInfo;
//    }
//
//    //认证
//    //用户登录后，通过token信息找到用户所有信息，将其封装为AuthenticationInfo返回
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("执行了=>认证");
//
//        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//        String userName = usernamePasswordToken.getUsername();
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", userName);
//        User user = userService.getOne(queryWrapper);
//
//        //用户名认证
//        if (user == null) {
//            return null;
//        }
//
////        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
////        System.out.println("simpleAuthenticationInfo: " + simpleAuthenticationInfo);
//        //密码认证
//        return new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
//    }
//}
