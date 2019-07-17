package com.mengyunzhi.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author: htx
 * 首页面板
 * @date: 19-7-17
 */
public class IndexPanel extends JPanel {

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

    public IndexPanel() {
        init();
    }

    void init() {
        font = new Font("Serif", Font.PLAIN, 16); // 设置字体
        systemNameLabel = new JLabel("系统名称:"); // 系统名称
        systemNameLabel.setFont(font);
        systemSelected = new JComboBox();   // 系统下拉框
        systemSelected.addItem("爱利益学生系统");
        systemSelected.addItem("计量项目");
        systemSelected.setFont(font);
        selectSystemButton = new JButton("添加"); // 添加按钮
        selectSystemButton.setFont(font);
        superPasswordSeedLabel = new JLabel("超级密码种子:"); // 超级密码种子
        superPasswordSeedLabel.setFont(font);
        superPasswordSeedTextField = new JTextField(50);    // 超级密码种子输入框
        superPasswordSeedTextField.setFont(font);
        superPasswordLabel = new JLabel("超级密码生成:"); // 生成超级密码
        superPasswordLabel.setFont(font);
        superPassword = new JLabel("12345679fdsfsdfds"); // 超级密码文本
        copySuperPasswordButton = new JButton("复制"); // 复制按钮
        copySuperPasswordButton.setFont(font);
        countSuperPasswordButton = new JButton("生成");   // 生成按钮
        countSuperPasswordButton.setFont(font);
        titleLabel = new JLabel("");
        titleLabel.setFont(font);
        // 设置首页面板布局
        this.setLayout(new MigLayout("", // Layout Constraints
                "", // Column constraints
                "[]35[]"));
        this.add(new JLabel(), "wrap");;
        this.add(new JLabel(), "wrap");
        this.add(systemNameLabel, "gapleft 50");
        this.add(systemSelected);
        this.add(selectSystemButton,"gapright 50");
        this.add(new Label(), "wrap");
        this.add(superPasswordSeedLabel, "gapleft 50");
        this.add(superPasswordSeedTextField, "wrap");
        this.add(superPasswordLabel, "gapleft 50");
        this.add(superPassword, "wrap");
        this.add(new JLabel());
        this.add(copySuperPasswordButton, "split 2");
        this.add(countSuperPasswordButton);
    }

}
