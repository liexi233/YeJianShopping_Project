package com.yejian.controller;

import Constant.UserConstant;
import DTO.CheckUserDTO;
import DTO.ForgetSubmitUserDTO;
import DTO.ForgetUserDTO;
import DTO.UserLoginDTO;
import code.Code;
import com.yejian.service.UserService;
import context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import result.Result;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    //先注入依赖
    //关于注入的原因依赖注入	由 Spring 准备好对象，再交给需要它的类
    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    //登录请求,这里测试一下;
    @PostMapping("/login")
    //用新搞的DTO来测试
    public Result Login(@RequestBody UserLoginDTO userLoginDTO){
        //然后就是一步步来拆解就是，先测试一下
        //第一步还是查询数据库就是了，看有没有这个


        //那么这里就需要的就是VO类，因为VO类这里需要我们的token才可


        return userService.login(userLoginDTO);
    }
    @PostMapping("/check")
    public Result check(@RequestBody CheckUserDTO checkUserDTO){
        //不知道;
        //所以这里的话:
        //还是先判断用户存不存
        log.info("处理忘记密码，用户和手机是否一致的问题,{}",checkUserDTO);
        return userService.check(checkUserDTO);
    }
    //不对，要手机号 //forgetpwdCode:userAccount:phone:"val_verify_code"即可

    @GetMapping("/forgetpwd/{userAccount}/{phone}")
    public Result forgetpwd(@PathVariable String userAccount, @PathVariable String phone){
        //直接进入业务层处理数据即可;
        //但是好像没有数据可以处理，直接发码即可;
        log.info("发码执行中...");
        String scode = Code.getCode();
        try {
            stringRedisTemplate.opsForValue().set(UserConstant.ForgetPwdVCodePrefix+userAccount+":"+phone,scode, 2,TimeUnit.MINUTES);
            log.info("验证码成功发送!");
            return Result.success(scode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //修改数据
    @PutMapping("/forgetpwdSubmit")
    public Result forgetpwdSubmit(@RequestBody ForgetSubmitUserDTO forgetSubmitUserDTO){
        //这里的话:
        return userService.forgetpwdSubmit(forgetSubmitUserDTO);
    }

    @GetMapping("/my")
    public Result<Long> my(){
        Long userId= BaseContext.getCurrentId();
        return Result.success(userId);
    }

}
