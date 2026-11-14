package com.yejian.config;

import com.yejian.interceptor.JwtTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//用于拦截器相关设置;
@Configuration
@Slf4j
//这里配置类的作用是:
/*
集中向 Spring MVC 提供你的自定义设置。 这里配置拦截器，以后也可以配置资源映射、消息转换器等；这些设置也可以放进已有的 MVC 配置类中，不要求每个拦截器单独建一个配置类。
 */
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
/*
Spring 初始化 MVC
    ↓
执行父类中的初始化代码
    ↓
父类调用 addInterceptors(registry)
    ↓
执行你重写的方法
    ↓
//把两个拦截器及其路径规则登记进去
@Configuration：让 Spring 识别这是配置类。
继承 WebMvcConfigurationSupport：可以重写它提供的配置方法。
addInterceptors(...)：就是 Spring MVC 初始化时会调用的配置方法，你不用自己调用
*/

    //配置一下拦截器;
    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    //自定义拦截器;
    //添加:
    //registry：参数的名字，代表传进来的那个注册表对象。
    //这个对象是 Spring 创建好后，调用 addInterceptors() 时传给你的，所以这里不用你自己 new。
    //registry类似于一个登记处;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/user/**")//我的，活动，购物车,免费开店这些
                .excludePathPatterns(
                        "/user/login",
                        "/user/check",
                        "/user/forgetpwd/**",
                        "user/forgetpwdSubmit/"
                );
        //好烦;



    }
}
