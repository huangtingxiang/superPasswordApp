package com.mengyunzhi.frame;

import com.mengyunzhi.panel.EditPanel;
import com.mengyunzhi.panel.IndexPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author: htx
 * 主窗体
 * @date: 19-7-16
 */
public class MainFrame extends JFrame {

    public static final MainFrame mainFrame = new MainFrame();

    JPanel mainPanel; // 主面板

    JPanel editPanel; // 编辑面板

    JPanel indexPanel; // 首页面板

    private MainFrame() {
        initFrame();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setVisible(true);
    }

    /**
     * @param
     * @return void
     * @description 初始化主界面
     * @author htx
     * @date 下午9:52 19-7-16
     **/
    private void initFrame() {
        // 初始化控件
        mainPanel = new JPanel(new CardLayout());
        editPanel = new EditPanel();
        indexPanel = new IndexPanel();

        this.setTitle("超级密码生成器");

        mainPanel.add(editPanel, "edit");
        mainPanel.add(indexPanel, "index");


        this.getContentPane().add(mainPanel);

    }

}
