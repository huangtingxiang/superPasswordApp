package com.mengyunzhi.frame;

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

    JComboBox systemSelected; // 系统名称下拉框

    JLabel systemNameLabel; // 系统名称label

    JButton selectSystemButton; // 选择系统按钮

    JLabel superPasswordSeedLabel; // 超级密码种子label

    JTextField superPasswordSeedTextField; // 超级密码种子输入框

    JLabel superPasswordLabel; // 超级密码label

    JLabel superPassword; // 生成的超级密码

    JButton copySuperPasswordButton; // 复制超级密码

    JButton countSuperPasswordButton; // 计算超级密码

    Font font; // 字体

    JLabel titleLabel; // 标题

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
        indexPanel = new IndexPanel();
        editPanel = new JPanel();
        editPanel.setBackground(Color.yellow);
        this.setTitle("超级密码生成器");


        mainPanel.add(indexPanel, "index");
        mainPanel.add(editPanel, "edit");

        this.getContentPane().add(mainPanel);

        // 设置首页面板布局
//        indexPanel.setLayout(new MigLayout("", // Layout Constraints
//                "", // Column constraints
//                "[]35[]"));
//        indexPanel.add(new JLabel(), "wrap");;
//        indexPanel.add(new JLabel(), "wrap");
//        indexPanel.add(systemNameLabel, "gapleft 50");
//        indexPanel.add(systemSelected);
//        indexPanel.add(selectSystemButton,"gapright 50");
//        indexPanel.add(new Label(), "wrap");
//        indexPanel.add(superPasswordSeedLabel, "gapleft 50");
//        indexPanel.add(superPasswordSeedTextField, "wrap");
//        indexPanel.add(superPasswordLabel, "gapleft 50");
//        indexPanel.add(superPassword, "wrap");
//        indexPanel.add(new JLabel());
//        indexPanel.add(copySuperPasswordButton, "split 2");
//        indexPanel.add(countSuperPasswordButton);
        // 添加控件
//        this.add(systemNameLabel);
//        this.add(systemSelected);
//        this.add(selectSystemButton);

    }

}
