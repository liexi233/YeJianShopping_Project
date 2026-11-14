package com.yejian.service;

import DTO.CheckUserDTO;
import DTO.ForgetSubmitUserDTO;
import DTO.UserLoginDTO;
import Entity.User;
import result.Result;

public interface UserService {
    Result login(UserLoginDTO userLoginDTO);

    User getByUserAccount(String userAccount);

    Result check(CheckUserDTO checkUserDTO);

    Result forgetpwdSubmit(ForgetSubmitUserDTO forgetSubmitUserDTO);
}
