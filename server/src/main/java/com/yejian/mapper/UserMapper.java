package com.yejian.mapper;

import Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//Mapper这里是接口，不是普通的类
//关于Mapper的注解这里，是mybatis,不是Spring的，
//所以这里pom要加上依赖即可
@Mapper
public interface UserMapper {

    User get(User user);

    @Select("select * from user where user_account=#{userAccount}")
    User getByUserAccount(String userAccount);

    void insert(User user);
}
