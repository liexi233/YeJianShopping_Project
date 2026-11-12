package com.yejian.service.UserServiceImpl;

import DTO.UserLoginDTO;
import Entity.User;
import com.yejian.mapper.UserMapper;
import com.yejian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.Result;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        //传输到业务层那里进行处理数据
        //先看一下，就是当前有没有这个用户，没有就直接返回Result(null);
        //查询的话，这里是要接收的是实体，所以这里也是要写Entity实体来包装的就是了;
        //可以先这样
        //Spring 提供的 BeanUtils.copyProperties(),可以直接导入就是了
        String given_password=userLoginDTO.getPassword();
        User user = new User();
        //对的用户名一致;
        BeanUtils.copyProperties(userLoginDTO,user);
        user=userMapper.get(user);
        //先是判断一下是否为空就是了;
        if(user==null)
        {
            log.error("该用户不存在!");
            return Result.error("该用户不存在!!!请注册");
        }
        //再是看密码是否正确:
        if(!user.getPassword().equals(given_password))
        {
            log.error("用户名或者密码错误,请重试!");
            return Result.error("用户名或者密码错误,请重试!");
        }
        log.info("登录成功");
        return Result.success(user);
    }

    @Override
    public User getByUserAccount(String userAccount) {

        //这里的话直接调用了
        return userMapper.getByUserAccount(userAccount);
    }
}
