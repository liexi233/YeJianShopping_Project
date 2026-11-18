package com.yejian.service;

import DTO.ApplicationPageQueryDTO;
import result.PageResult;

public interface ApplicationService {
    PageResult pageQuery(ApplicationPageQueryDTO applicationPageQueryDTO);
}
