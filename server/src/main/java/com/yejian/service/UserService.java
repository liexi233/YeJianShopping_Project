package com.yejian.service;

import DTO.UserLoginDTO;
import Entity.User;
import result.Result;

public interface UserService {
    Result login(UserLoginDTO userLoginDTO);

    User getByUserAccount(String userAccount);
}
