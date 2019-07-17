package com.mengyunzhi.panel;

import com.mengyunzhi.listener.IndexPanelListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: htx
 * 首页面板
 * @date: 19-7-17
 */
public class IndexPanel extends JPanel {

    public final static String name = "indexPanel";

    private JComboBox systemSelected; // 系统名称下拉框

    private JLabel systemNameLabel; // 系统名称label

    private JButton selectSystemButton; // 选择系统按钮

    private JLabel superPasswordSeedLabel; // 超级密码种子label

    private JTextField superPasswordSeedTextField; // 超级密码种子输入框

    private JLabel superPasswordLabel; // 超级密码label

    private JLabel superPassword; // 生成的超级密码

    private JButton copySuperPasswordButton; // 复制超级密码

    private JButton countSuperPasswordButton; // 计算超级密码

    private Font font; // 字体

    private JLabel titleLabel; // 标题

    private Set<IndexPanelListener> listeners = new HashSet<>(); // 外部监听者

    public IndexPanel() {
        init();
    }

    /**
     * @description 添加监听者
     * @param indexPanelListener
     * @return void
     * @author htx
     * @date 下午4:47 19-7-17
     **/
    public void addListener(IndexPanelListener indexPanelListener) {
        this.listeners.add(indexPanelListener);
    }

    /**
     * @description 初始化面板信息
     * @return void
     * @author htx
     * @date 下午4:56 19-7-17
     **/
    private void init() {
        initComponent(); // 初始化控件
        settingLayout(); // 设置布局
        registerEvent(); // 注册事件
    }

    /**
     * @description 初始化控件
     * @return void
     * @author htx
     * @date 下午4:55 19-7-17
     **/
    private void initComponent() {
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
    }

    /**
     * @description  注册组件事件
     * @return void
     * @author htx
     * @date 下午4:50 19-7-17
     **/
    private void registerEvent() {
        selectSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IndexPanelListener listener:
                     listeners) {
                    listener.listenerSelectSystemButton(e);
                }
            }
        });
    }

    /**
     * @description  设置布局
     * @return void
     * @author htx
     * @date 下午4:56 19-7-17
     **/
    private void settingLayout() {
        // 设置首页面板布局
        setLayout(new MigLayout("", // Layout Constraints
                "", // Column constraints
                "[]35[]"));
        add(new JLabel(), "wrap");
        add(new JLabel(), "wrap");
        add(systemNameLabel, "gapleft 50");
        add(systemSelected);
        add(selectSystemButton, "gapright 50");
        add(new Label(), "wrap");
        add(superPasswordSeedLabel, "gapleft 50");
        add(superPasswordSeedTextField, "wrap");
        add(superPasswordLabel, "gapleft 50");
        add(superPassword, "wrap");
        add(new JLabel());
        add(copySuperPasswordButton, "split 2");
        add(countSuperPasswordButton);
    }

}
