package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

//用于创建jwt
public class JwtUtil {
    public static String createJWT(String secretKey, long ttlMillis, Map<String,Object> claims)
    {
        //Claims本体是有的;
        //指定签名的算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long expireMillis=System.currentTimeMillis()+ttlMillis;
        Date exp = new Date(expireMillis);
        //jwt是分成三个部分的:1.指明签名算法2.载体(明文)3.签名
        //设置jwtBody
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)//这是body部分吧，应该
                //设置签名使用的签名算法和秘钥
                //这里要getBytes啊
                .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8))
                //设置过期时间
                .setExpiration(exp);

        //合成字符串
        return builder.compact();
    }

    //后端解析的JWT
    //HS是签名算法，解密加密都是用同一个秘钥，对称秘钥就是了
    //token就是那个authentication;
    public static Claims parseJWT(String secretKey, String token){
        Claims claims= Jwts.parser()
                //签名的秘钥，这个时候就要解密就是了
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()//创建
                .parseClaimsJws(token) //验签，检查过期时间并且解析
                .getBody(); //取出claims
                //解密token
        return claims;
    }

    //给Vo的那个，其实还是包含JWt，也就是token,所以这里其实是有点麻烦的;



}
