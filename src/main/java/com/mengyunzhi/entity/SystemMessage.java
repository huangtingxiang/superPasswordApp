package com.mengyunzhi.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author: htx
 * @date: 19-7-17
 */
public class SystemMessage implements Serializable {

    public static final String key = "system-messages";

    private String name; // 系统名称

    private String superPasswordSeed; // 系统超级密码种子

    public SystemMessage(String name, String superPasswordSeed) {
        this.name = name;
        this.superPasswordSeed = superPasswordSeed;
    }

    public SystemMessage() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperPasswordSeed() {
        return superPasswordSeed;
    }

    public void setSuperPasswordSeed(String superPasswordSeed) {
        this.superPasswordSeed = superPasswordSeed;
    }
}
