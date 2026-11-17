package VO;

import lombok.Data;

@Data
public class ManagerVO {
    //只需要这个就行了;
    String name; //存入名字，因为要显示当前用户是谁
    String token; //存入前端的sessionStorage即可;
}
