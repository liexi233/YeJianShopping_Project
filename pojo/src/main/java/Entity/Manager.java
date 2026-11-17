package Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Manager {
    Long id;
    String name;
    String password;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    //也是id;
    Long updateManager;
    Long createManager;
}
