package result;


import lombok.Data;

//记得这里要写个T表示泛型就是了;
@Data
public class Result<T> {
    //一共是有三个的，一个状态码，一个是信息，一个就是data就是;
    private Integer code;

    private String message;//错误信息

    private T data;

    //这里需要改一下;
    //不需要结果的:

    public static <T> Result<T> success(T data){
        //因为需要确定类型:
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setCode(1);
        return result;
    }

    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.setCode(1);
        return result;
    }

    public static <T> Result<T> error(String message){
        Result<T> result = new Result<T>();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }
}
