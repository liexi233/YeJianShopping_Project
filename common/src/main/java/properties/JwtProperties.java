package properties;

//JWt相关参数


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
//因为是一些参数，关于JWT，所以这里放入yml文件即可
//里面填的是yml的前缀，找到即可
@ConfigurationProperties(prefix="yejianshop.jwt")
public class JwtProperties {
    //先是用户的;
    public String userSecretKey;
    public  long userTtl;
    public String userTokenName;
}
