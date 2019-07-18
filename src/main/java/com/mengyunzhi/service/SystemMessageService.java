package com.mengyunzhi.service;

import com.google.common.collect.Iterables;
import com.mengyunzhi.entity.SystemMessage;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author: htx
 * @date: 19-7-18
 */
public class SystemMessageService {

    private static SystemMessageService systemMessageService;

    static private Logger logger = Logger.getLogger(SystemMessageService.class.getName());

    DBService dbService;

    {
        try {
            dbService = DBService.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
            logger.warning("连接数据库失败");
        }
    }

    private SystemMessageService() {
    }

    public static SystemMessageService getInstance() {
        if (systemMessageService == null) {
            systemMessageService = new SystemMessageService();
            return systemMessageService;
        } else {
            return systemMessageService;
        }
    }

    public void saveAll(SystemMessage[] systemMessages) {
        if (systemMessages != null) {
            dbService.save(SystemMessage.key, systemMessages);
        }
    }

    public SystemMessage[] getAll() {
        Object object = dbService.get(SystemMessage.key);
        if (object != null) {
            return (SystemMessage[]) object;
        }
        return new SystemMessage[0];
    }

    public void save(Object key, SystemMessage systemMessage) {
        if (systemMessage != null) {
            dbService.save(key, systemMessage);
        }
    }

    public SystemMessage get(Object key) {
        Object object = dbService.get(key);
        if (object != null) {
            return (SystemMessage) object;
        }
        return null;
    }
}
