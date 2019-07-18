package com.mengyunzhi.service;

import com.mengyunzhi.entity.SystemMessage;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author: htx
 * @date: 19-7-18
 */
public class SystemMessageServiceTest {

    static private Logger logger = Logger.getLogger(SystemMessageServiceTest.class.getName());

    DBService dbService = DBService.getInstance();

    SystemMessageService  systemMessageService = SystemMessageService.getInstance();

    public SystemMessageServiceTest() throws IOException {
    }

}