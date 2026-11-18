package DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationPageQueryDTO {
    //根据查询结果来搞的
    private int page;
    private int pageSize;
    //栓选条件就是了
    //显示的,但是这里是有状态和用户名的就是了;
    //也是查询条件时间
    private LocalDateTime createTime;
    //貌似那里好像还有截止的时间就是了;
    private LocalDateTime endTime;

    private String userName;//用户搜到的
    private Integer status; //0,1,2 就是了;


}
