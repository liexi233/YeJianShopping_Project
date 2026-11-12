package code;

import java.security.SecureRandom;

public class Code {

    //如果是多线程这里怎么办
    //不用，直接变成工具类即可:
    private Code(){}

    //所以这里的话:
    public static String getCode(){
        String vcode=String.format("%06d",new SecureRandom().nextInt(1_000_000));
        return vcode;
    }
}
