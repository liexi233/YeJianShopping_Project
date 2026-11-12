package DTO;

import lombok.Data;

@Data
public class RegisterDTO {
    //不知道:
    String userAccount;
    String password;
    String phone;
    String code;//验证码;
}
