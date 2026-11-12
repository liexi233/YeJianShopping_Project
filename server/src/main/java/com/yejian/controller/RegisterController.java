package com.yejian.controller;

import DTO.RegisterCodeDTO;
import DTO.RegisterDTO;
import Entity.User;
import com.yejian.service.RegisterService;
import com.yejian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import result.Result;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController {
    //生成一个验证码:
    @Autowired
    private RegisterService registerService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;


    // 变成路径的一部分
    @GetMapping("/code/{phone}")
    public Result SendCode(@PathVariable("phone") String phone) {
        //返回一个Result的空成功结果即可:
        //所以这里的话:
        //进入业务逻辑层处理
        //因为redis那里需要phone打标记的;
        return registerService.getCode(phone);
    }


    @PostMapping("/register")
    public Result Register(@RequestBody RegisterDTO registerDTO) {
        //这里的话;
        //先看验证码是否正确;
        if(!(registerDTO.getCode().equals(stringRedisTemplate.opsForValue().get("register:code:"+registerDTO.getPhone())))){
            //如果不正确
            log.error("用户的验证码不正确");
            return Result.error("用户验证码不正确");
            //这里前端接受后，怎么办，怎么处理数据，另外不是要用session吗，怎么配置的，出问题了怎么办;
        }

        //另外就是看用户是否是存在的了;
        //查询
        //只拿去userAccount即可就是了;
        String userAccount=registerDTO.getUserAccount();
        //重写一个吗这里
        User user=userService.getByUserAccount(userAccount);
        if(user!=null)
        {
            //说明用户已经存在，不能创建重复用户:
            return Result.error("该用户名已经存在，试试别的用户名吧");
        }

        //如果没有的话，这里就开始创建了就是了，手机号到无所谓;
        //insert
        registerService.register(registerDTO);

        return Result.success();
    }


}
