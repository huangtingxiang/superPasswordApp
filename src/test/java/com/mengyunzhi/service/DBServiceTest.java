package com.mengyunzhi.service;

import org.junit.Test;

import java.io.IOException;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author: htx
 * @date: 19-7-17
 */
public class DBServiceTest {

    DBService dbService = DBService.getInstance();

    static private Logger logger = Logger.getLogger(DBService.class.getName());

    public DBServiceTest() throws IOException {
    }


    @Test
    public void saveAndGet() {
        String key = "test";
        String[] values = {"test1", "test2"};
        logger.info("断言保存成功");
        assertThat(dbService.save(key, values)).isTrue();
        logger.info("断言获取成功");
        String[] getValues = (String[]) dbService.get(key);
        assertThat(getValues.length).isEqualTo(values.length);
        for (int i = 0; i < getValues.length; i ++) {
            assertThat(getValues[i]).isEqualTo(values[i]);
        }
    }
}