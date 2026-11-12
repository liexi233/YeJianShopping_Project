package Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    Long id; //这里也是包装类就是了
    String userAccount;
    String password;
    LocalDateTime createTime;
    Integer isSaler; //建议搞成包装类即可，这里也是:
    String phone;
}
