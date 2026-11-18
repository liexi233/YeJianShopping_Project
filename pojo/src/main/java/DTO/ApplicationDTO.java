package DTO;

import lombok.Data;

@Data
public class ApplicationDTO {
    //详情搞的,只想查询里面的理由就是了
    private Long id;
    //搞个sql查查得了;

    //但是申请的话好像就不是这样了，对的，因为用户好像也是这样的;
    private String user_name;

    //完整的封装在Entity,必要时封装就是了;
    private String applyReason;

    //时间的话就在业务层那里处理即可;

}
