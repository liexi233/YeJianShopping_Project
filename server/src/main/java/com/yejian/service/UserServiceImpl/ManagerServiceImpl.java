package com.yejian.service.UserServiceImpl;

import DTO.ManagerDTO;
import Entity.Manager;
import VO.ManagerVO;
import com.yejian.mapper.ManagerMapper;
import com.yejian.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import properties.JwtProperties;
import result.Result;
import utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public Result Login(ManagerDTO managerDTO) {
        //这里的话:
        //直接写根据名字即可就是了;
        String name=managerDTO.getName();
        Manager manager=managerMapper.getByName(name);

        if(manager!=null)
        {
            if(manager.getPassword().equals(managerDTO.getPassword()))
            {
                //生成Jwt
                ;
                log.info("生成管理端的JWt");
                ManagerVO managerVo=new ManagerVO();
                BeanUtils.copyProperties(manager,managerVo);
                Map<String,Object> claims=new HashMap<>();
                String token= JwtUtil.createJWT(jwtProperties.managerSecretKey, jwtProperties.getManagerTtl(), claims);
                managerVo.setToken(token);
                return Result.success(managerVo);
            }
            return Result.error("该管理用户密码输入不正确!!");
        }else {
            return Result.error("该管理用户不存在");
        }
    }
}
