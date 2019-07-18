package com.mengyunzhi.service;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author: htx
 * @date: 19-7-18
 */
public class SuperPasswordService {

    static private Logger logger = Logger.getLogger(SuperPasswordService.class.getName());

    private static SuperPasswordService superPasswordService;

    private SuperPasswordService() {

    }

    public static SuperPasswordService getInstance() {
        if (superPasswordService == null) {
            superPasswordService = new SuperPasswordService();
        }
        return superPasswordService;
    }

    public String getSuperPassword(String superPasswordSeed, Date date) {
        Long seed = Long.parseLong(superPasswordSeed);
        logger.info("获取今日凌晨12时间戳");
        Long superPasswordTime = CommonService.getStartOfDay(date).getTime();
        logger.info("sha256加密");
        String plaintext = Long.toString(seed + superPasswordTime);
        String cipherText = CommonService.encryptSha256(plaintext);
        return cipherText;
    }

}
