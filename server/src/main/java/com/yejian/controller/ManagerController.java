package com.yejian.controller;

import DTO.ManagerDTO;
import com.yejian.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    //接入网络请求;
    @PostMapping("/login")
    public Result Login(@RequestBody ManagerDTO managerDTO){
        //先去查这个用户到底存在不;
        //到业务层即可;
        log.info("接入管理端的请求");
        return managerService.Login(managerDTO);
    }


}
