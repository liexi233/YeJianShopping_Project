package com.yejian;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//扫描包找不到，所以这里加上即可;
@SpringBootApplication(
        scanBasePackages = {
                "com.yejian",
                "properties"
        }

)
@Slf4j
//要写个拦截器类配置类;
public class ShoppingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
        log.info("server started");
    }
}
