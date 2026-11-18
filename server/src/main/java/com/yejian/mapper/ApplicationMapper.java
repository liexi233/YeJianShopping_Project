package com.yejian.mapper;

import DTO.ApplicationPageQueryDTO;
import VO.ApplicationVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplicationMapper {


    Page<ApplicationVO> pageQuery(ApplicationPageQueryDTO applicationPageQueryDTO);


}
