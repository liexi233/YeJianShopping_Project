package com.yejian.service.UserServiceImpl;

import DTO.RegisterDTO;
import Entity.User;
import code.Code;
import com.yejian.mapper.UserMapper;
import com.yejian.service.RegisterService;
import com.yejian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import result.Result;
import tools.jackson.databind.annotation.JsonAppend;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    //注入redis的操作依赖;
    //也是需要配置类，是因为没有吗
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result getCode(String phone) {
        //生成6位验证码:
        //不对，这里也是要手机验证码的就是了
        String vCode = Code.getCode();
        //生成验证码成功
        log.info("验证码生成成功");
        //依旧存活时间;
        stringRedisTemplate.opsForValue().set("register:code:"+phone,vCode,2, TimeUnit.MINUTES);
        //不需要
        return Result.success();
    }

    //这里的话;
    @Override
    public void register(RegisterDTO registerDTO) {
        //这里的话;
        User user = new User();
        BeanUtils.copyProperties(registerDTO,user);
        user.setCreateTime(LocalDateTime.now());
        //还有开店状态就是了
        user.setIsSaler(0);
        userMapper.insert(user);

    }
}
