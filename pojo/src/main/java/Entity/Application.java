package Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Application {
    private Long id;
    private Long userId;
    private LocalDateTime createTime;
    private Integer status;
    private String rejectionReason;
    private String applyReason;
}
