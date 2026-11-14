package com.yejian.service.UserServiceImpl;

import Constant.JwtConstant;
import Constant.UserConstant;
import DTO.CheckUserDTO;

import DTO.ForgetSubmitUserDTO;
import DTO.UserLoginDTO;
import Entity.User;
import VO.UserVO;
import com.yejian.mapper.UserMapper;
import com.yejian.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import properties.JwtProperties;
import result.Result;
import utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //
    @Autowired
    JwtProperties jwtProperties;


    //返回的是Vo类就是了;
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

        //貌似这里好像可以直接用java来搞session对的，不过其实完全可以用jwt来存到Redis当中我感觉也是可以的;

        //封装数据，这里要搞个Vo类封装就是了
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user,userVO);
        //这里要创建token
        //不过先创建一个Map,作用是确定用户的id在那里;
        Map<String,Object> claims=new HashMap<>();
        claims.put(JwtConstant.USER_ID,user.getId());//就是这个
        //使用JwtProperties,要先注入，他是Resource
        String token=JwtUtil.createJWT(jwtProperties.userSecretKey, jwtProperties.getUserTtl(), claims);
        log.info("用户JWT令牌:"+token);


        //存了ThreadLocal有什么用，另外就是
        log.info("登录成功");
        userVO.setToken(token);
        return Result.success(userVO);
    }

    @Override
    public User getByUserAccount(String userAccount) {

        //这里的话直接调用了
        return userMapper.getByUserAccount(userAccount);
    }

    @Override
    public Result check(CheckUserDTO checkUserDTO) {
        //这里的话
        //先是看用没有这个账户就是了
        User temp = new User();

        //查询即可;
        User user=userMapper.getByUserAccountAndPhone(checkUserDTO.getUserAccount(),checkUserDTO.getPhone());
        if(user==null)
        {
            log.error("忘记密码数据不一致，失败");
            return Result.error("账户不存在或手机号不一致，请重试!!!");
        }
        //重新封装数据成VO类;
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user,userVO);

        log.info("忘记密码校验数据一致，进行发码操作");
        return Result.success(userVO);

    }

    @Override
    public Result forgetpwdSubmit(ForgetSubmitUserDTO forgetSubmitUserDTO) {
        //这里的话:
        //先去看数据完整性
        if(forgetSubmitUserDTO.getUserAccount()==""||forgetSubmitUserDTO.getPhone()==""||forgetSubmitUserDTO.getPassword()=="")
        {
            log.error("用户提交的信息有空，错误!");
            return Result.error("用户名或者手机号或者密码为空，重新输入");
        }
        //比较验证码，这里的话:
        String comp_s = stringRedisTemplate.opsForValue().get(UserConstant.ForgetPwdVCodePrefix + forgetSubmitUserDTO.getUserAccount() + ":" +
                forgetSubmitUserDTO.getPhone());

        //其实就是修改密码就是了;
        if(!comp_s.equals(forgetSubmitUserDTO.getCode()))
        {
            //所以这里的
            log.error("验证码不一致!!!");
            return Result.error("验证码校验不一致!!!");
        }

        String password=forgetSubmitUserDTO.getPassword();
        String userAccount=forgetSubmitUserDTO.getUserAccount();

        //这个时候可以修改密码了;
        //账户和密码即可
        userMapper.delegatePwd(userAccount,password);
        log.info("修改成功!!");
        return Result.success();
    }
}
