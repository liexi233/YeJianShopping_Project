package DTO;

import lombok.Data;

@Data
public class ForgetSubmitUserDTO {
    String userAccount;
    String password;
    String phone;
    String code;
}
