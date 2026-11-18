package result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    //分页查询的结果只输出一部分即可
    //另外就是这里还要总的数量即可
    private long total;
    private List records;
}
