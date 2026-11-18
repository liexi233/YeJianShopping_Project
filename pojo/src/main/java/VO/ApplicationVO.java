package VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationVO {
    private Long id;
    private String userName;
    private String applyReason;
    //就是这里的话，如果已经操作的话，这里就直接不用显示那几个按钮即可就是了;
    private Integer status;//因为要记录是不是的就是了，如果是0就可以操作，是1，2的话就不行写个
    // (通过 / 不通过:拒绝信息，这里面有个详情信息就是了)
    private LocalDateTime createTime;

}
