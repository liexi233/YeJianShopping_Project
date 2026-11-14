package com.yejian.mapper;

import Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//Mapper这里是接口，不是普通的类
//关于Mapper的注解这里，是mybatis,不是Spring的，
//所以这里pom要加上依赖即可
@Mapper
public interface UserMapper {

    User get(User user);

    //这里还要模糊查询吗我天
    @Select("select * from user where user_account=#{userAccount}")
    User getByUserAccount(String userAccount);

    @Select("select * from user where user_account=#{userAccount} and phone=#{phone}")
    User getByUserAccountAndPhone(String userAccount,String phone);

    void insert(User user);


    @Update("update user set password=#{password} where user_account=#{userAccount}")
    void delegatePwd(@Param("userAccount")String userAccount, @Param("password")String password);
}
