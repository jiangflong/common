package cn.feilong.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

/**
 * @description: Jwt工具类，生成JWT和认证
 * @author: Java碎碎念
 */
public class JwtUtil {

    /**
     * 密钥
     */
    private static String secret;

    /**
     * 过期时间
     **/
    private static int expiredTimeIn;

    @Value("${baby.security.secret}")
    public void setJwtKey(String jwtKey) {
        JwtUtil.secret = jwtKey;
    }
    @Value("${baby.security.token-expired-in}")
    public void setExpiredTimeIn(Integer expiredTimeIn) {
        JwtUtil.expiredTimeIn = expiredTimeIn;
    }

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(Long uid,Integer role) {
        //过期时间
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND,expiredTimeIn);
        Date expireDate = calendar.getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("uid", uid)//userId
                .withClaim("role", role)
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(now) //签发时间
                .sign(Algorithm.HMAC256(secret)); //SECRET加密
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static boolean verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return false;
        }
        return  true;
    }
    public static Map<String, Claim> getClaims(String token) {
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(JwtUtil.secret);
        com.auth0.jwt.JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return Map.of();
        }
        return decodedJWT.getClaims();
    }
}
