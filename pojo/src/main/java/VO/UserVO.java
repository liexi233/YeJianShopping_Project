package VO;

import lombok.Data;

@Data
public class UserVO {
    //给点正常信息就是了，这里一定要搞个VO,并且并且就是一定有token就是了;
    Long id;
    String userAccount;
    String token;
}
