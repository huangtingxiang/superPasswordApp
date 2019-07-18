package com.mengyunzhi.service;

import com.mengyunzhi.config.SystemConfig;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

import java.util.logging.Logger;

import static org.iq80.leveldb.impl.Iq80DBFactory.factory;

import java.io.*;

/**
 * @author: htx
 * @describe
 * @date: 19-7-17
 */
public class DBService {

    private static DB levelDBStore;

    private static DBService dbService;

    static private Logger logger = Logger.getLogger(DBService.class.getName());

    private DBService() throws IOException {
        Options options = new Options();
        String path = getClass().getClassLoader().getResource(SystemConfig.DBPath).getPath();
        path = java.net.URLDecoder.decode(path, "utf-8");
        levelDBStore = factory.open(new File(path), options);
    }


    public static DBService getInstance() throws IOException {
        if (dbService == null) {
            dbService = new DBService();
            return dbService;
        } else {
            return dbService;
        }
    }

    public void delete(Object key) {
        try {
            levelDBStore.delete(objectToByteArray(key));
        } catch (IOException e) {
            logger.warning("删除失败");
        }
    }

    public boolean save(Object key, Object value) {
        // 将数组和索引化为byte[]
        boolean result = true;
        try {
            levelDBStore.put(objectToByteArray(key), objectToByteArray(value));
        } catch (IOException e) {
            logger.warning("保存出错");
            result = false;
        }
        return result;
    }

    public Object get(Object key) {
        try {
            try {
                byte[] bytes = levelDBStore.get(objectToByteArray(key));
                return bytes != null ? ByteArrayToObject(levelDBStore.get(objectToByteArray(key))) : null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            logger.warning("获取出错");
            e.printStackTrace();
        }
        return null;
    }

    private byte[] objectToByteArray(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            bos.close();
        }
        return bos.toByteArray();
    }

    private Object ByteArrayToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
