package com.mengyunzhi.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author: htx
 * @date: 19-7-18
 */
public class CommonService {

    static final String SHA_256 = "SHA-256";

    static private Logger logger = Logger.getLogger(CommonService.class.getName());

    /**
     * @param date
     * @return java.util.Date
     * @description 获取日期开始时间 00:00
     * @author htx
     * @date 下午6:48 19-7-15
     **/
    static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param plaintext 明文
     * @return java.lang.String 加密后的文字
     * @throws NoSuchAlgorithmException 加密算法不存在
     * @description sha256加密
     * @author htx
     * @date 上午6:19 19-7-15
     **/
    static String encryptSha256(String plaintext) {
        try {
            // 获取sha-256加密字节数组
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] hash = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            // 将字节数组化为无符号16进制字符串
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.warning("未获取到" + SHA_256 + "算法:" + e);
        }
        return plaintext;
    }
}
