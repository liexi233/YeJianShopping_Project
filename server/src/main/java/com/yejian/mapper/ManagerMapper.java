package com.yejian.mapper;

import Entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ManagerMapper {
    @Select("select * from manager where name=#{name}")
    Manager getByName(String name);
}
