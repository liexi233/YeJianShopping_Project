package com.yejian.controller;

import DTO.UserLoginDTO;
import com.yejian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    //先注入依赖
    //关于注入的原因依赖注入	由 Spring 准备好对象，再交给需要它的类
    @Autowired
    UserService userService;

    //登录请求,这里测试一下;
    @PostMapping("/login")
    //用新搞的DTO来测试
    public Result Login(@RequestBody UserLoginDTO userLoginDTO){
        //然后就是一步步来拆解就是，先测试一下
        //第一步还是查询数据库就是了，看有没有这个
        return userService.login(userLoginDTO);
    }



}
