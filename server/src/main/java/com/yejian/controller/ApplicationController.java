package com.yejian.controller;

import DTO.ApplicationPageQueryDTO;
import com.yejian.mapper.ApplicationMapper;
import com.yejian.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.PageResult;
import result.Result;

@Component
@RestController
@Slf4j
@RequestMapping("/application")
public class ApplicationController {
    //分页查询
    //这里的话
    @Autowired
    ApplicationService applicationService;

    //处理的话
    //就是每次默认，或者是换页的时候就要发请求获取页数就是了
    @GetMapping("/pageQuery")
    public Result<PageResult> pageQuery(ApplicationPageQueryDTO  applicationPageQueryDTO){
        //完整信息是在这;
        log.info("申请表分页查询:");
        PageResult pageResult = applicationService.pageQuery(applicationPageQueryDTO);
        return Result.success(pageResult);
    }


}
