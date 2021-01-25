package cn.feilong.common.utils;


import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * bcrypt 加密
 */
public class Bcry {
    /**
     * 验证密码
     * @param password
     * @param candidate
     */

    public static Boolean checkPassword(String password, String candidate) {
        return BCrypt.checkpw(candidate, password);
    }

    /**
     * 加密
     * @param password
     * @param pLength
     * @return
     */
    public static String genPassword(String password, int pLength) {
        String salt = BCrypt.gensalt(pLength);
        String hashed = BCrypt.hashpw(password, salt);
        return hashed;
    }

    public static String genPassword(String password) {
        return genPassword(password, 10);
    }
}
