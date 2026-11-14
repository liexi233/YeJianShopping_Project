package com.yejian.service;

import DTO.RegisterDTO;
import result.Result;

public interface RegisterService {
    Result getCode(String phone,String userAccount);

    void register(RegisterDTO registerDTO);
}
