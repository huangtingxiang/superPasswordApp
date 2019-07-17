package com.mengyunzhi.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author: htx
 * @date: 19-7-17
 */
public class EditPanel extends JPanel {

    JLabel systemNameLabel; // 系统名称label

    JTextField systemNameTextField; // 系统名称输入框

    JLabel superPasswordSeedLabel; // 随机密码label

    JTextField superPasswordSeedTextField; // 随机密码输入框

    JButton addButton; // 添加按钮

    JList<String> systemList; // 系统列表

    Font font; // 字体

    JButton editButton; // 编辑按钮

    JButton deleteButton; // 删除按钮

    Box buttonBox;

    public EditPanel() {
        init();
    }

    void init() {
        initComponent();
        settingLayout();
    }

    void initComponent() {
        // 初始化控件
        font = new Font("Serif", Font.PLAIN, 16); // 设置字体
        systemNameLabel = new JLabel("系统名称:");
        systemNameLabel.setFont(font);
        systemNameTextField = new JTextField(20);
        systemNameTextField.setFont(font);
        superPasswordSeedLabel = new JLabel("超级密码种子:");
        superPasswordSeedLabel.setFont(font);
        superPasswordSeedTextField = new JTextField(40);
        superPasswordSeedTextField.setFont(font);
        addButton = new JButton("添加");
        addButton.setFont(font);
        String[] data = {"测试公司1(fds456f4651f3sd1fds4fs56df5s6dfsd)","测试公司1","测试公司1","测试公司1","测试公司1","测试公司1"};
        systemList = new JList<>(data);
        systemList.setFont(font);
        systemList.setPreferredSize(new Dimension(450, 450));
        JScrollPane scrollPane = new JScrollPane(systemList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        buttonBox = new Box(BoxLayout.Y_AXIS);
        editButton = new JButton("编辑");
        editButton.setFont(font);
        deleteButton = new JButton("删除");
        deleteButton.setFont(font);
        buttonBox.add(editButton);
        buttonBox.add(deleteButton);
    }

    /*
     * @description 设置布局
     * @return void
     * @author htx
     * @date 下午4:14 19-7-17
     **/
    void settingLayout() {
        this.setLayout(new MigLayout());
        this.add(new Label(), "wrap 10px");
        this.add(systemNameLabel);
        this.add(systemNameTextField, "wrap");
        this.add(superPasswordSeedLabel);
        this.add(superPasswordSeedTextField);
        this.add(addButton, "wrap 20px");
        this.add(systemList, "span 2");
        this.add(buttonBox, "wrap");
    }

}
