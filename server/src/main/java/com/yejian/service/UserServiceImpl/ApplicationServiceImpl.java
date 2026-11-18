package com.yejian.service.UserServiceImpl;

import DTO.ApplicationPageQueryDTO;
import VO.ApplicationVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yejian.mapper.ApplicationMapper;
import com.yejian.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import result.PageResult;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationMapper applicationMapper;

    @Override
    public PageResult pageQuery(ApplicationPageQueryDTO applicationPageQueryDTO) {
        /*
        PageHelper 把分页条件暂存在它自己的 ThreadLocal 中。同一线程执行紧接着的 Mapper 查询时，
        分页插件就能读到这些条件，并把 SQL 改成分页查询。`startPage()` 本身还没有查询数据库
         */
        //设置分页条件
        PageHelper.startPage(applicationPageQueryDTO.getPage(),applicationPageQueryDTO.getPageSize());
        //收集数据
        Page<ApplicationVO> page=applicationMapper.pageQuery(applicationPageQueryDTO);
        //
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }
}
