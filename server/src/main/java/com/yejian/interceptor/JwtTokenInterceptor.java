package com.yejian.interceptor;

import Constant.JwtConstant;
import context.BaseContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import properties.JwtProperties;
import utils.JwtUtil;

//处理请求拦截器;
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;


    //对于拦截器;
    //注意是他
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("当前线程的Id:"+Thread.currentThread().getId());


        //判断是不是Controller层的方法,不是直接放行即可;
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        //拿取请求的东西;
        String token=request.getHeader(jwtProperties.getUserTokenName());
        //校验令牌
        //因为要返回状态码的;
        try{
            log.info("jwt校验: {}",token);
            //取出
            //Claims可以理解为本体Body;
            Claims claims= JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
            //取出里面的用户,Claims那里取，val就是id,记录id;线程给他用
            Long userId=Long.valueOf(claims.get(JwtConstant.USER_ID).toString());
            //设置
            BaseContext.setCurrentId(userId);
            //同意放行
            return true;
        }catch(Exception e){
            //设置状态码401
            //也就是这里给前端回应的;
            log.error("校验失败");
            response.setStatus(401);
            //不放行，后续直接给前端处理，直接转为登录页面即可;
            return false;
        }
    }


}
