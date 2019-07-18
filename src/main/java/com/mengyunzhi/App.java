package com.mengyunzhi;

import com.mengyunzhi.entity.SystemMessage;
import com.mengyunzhi.frame.MainFrame;
import com.mengyunzhi.service.DBService;

import java.io.IOException;


/**
 * Hello world!
 */
public class App {

    static DBService dbService;

    static {
        try {
            dbService = DBService.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        dbService.delete(SystemMessage.key);
        new MainFrame().setVisible(true);
    }
}
