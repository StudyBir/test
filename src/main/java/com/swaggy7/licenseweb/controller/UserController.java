package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.swaggy7.licenseweb.entity.User;
/*import com.swaggy7.licenseweb.service.impl.UserServiceImpl;*/
import com.swaggy7.licenseweb.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2023-02-02*/


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

//    @Autowired
/*    private UserServiceImpl userService;*/

//    @RequestMapping("/register")
//    public Boolean Register(@RequestBody User user){
//        boolean result = userService.save(user);
//        return result;
//    }

//    @RequestMapping("/login")
//    public String Login(@RequestBody User user) {
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
//        try {
//            subject.login(usernamePasswordToken);
//            return "LoginAdmin";
//        } catch (UnknownAccountException e) {//用户名不存在
//            return "用户名错误";
//        } catch (IncorrectCredentialsException e) {
//            return "密码错误";
//        }


//        System.out.println("--------" + user);
//        User one = userService.getOne(new QueryWrapper<User>()
//                .eq("user_name", user.getUserName())
//                .eq("user_password", user.getUserPassword())
//        );
//        if(one != null && one.getUserName().equals("1") && one.getUserPassword().equals("1")){
//            return "LoginAdmin";
//        }else{
//            return "LoginFail";
//        }
//    }



}
