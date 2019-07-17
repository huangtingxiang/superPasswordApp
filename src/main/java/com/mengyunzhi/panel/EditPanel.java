package com.mengyunzhi.panel;

import com.mengyunzhi.listener.EditPanelListener;
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
 * @date: 19-7-17
 */
public class EditPanel extends JPanel {

    public final static String name = "editPanel";

    private JLabel systemNameLabel; // 系统名称label

    private JTextField systemNameTextField; // 系统名称输入框

    private JLabel superPasswordSeedLabel; // 随机密码label

    private JTextField superPasswordSeedTextField; // 随机密码输入框

    private JButton addButton; // 添加按钮

    private JList<String> systemList; // 系统列表

    private Font font; // 字体

    private JButton editButton; // 编辑按钮

    private JButton deleteButton; // 删除按钮

    private JButton cancelButton; // 取消按钮

    private Box buttonBox; // 按钮组

    private Set<EditPanelListener> listeners = new HashSet<>(); // 外部监听者

    public EditPanel() {
        init();
    }

    /**
     * @description 添加监听者
     * @param editPanelListener
     * @return void
     * @author htx
     * @date 下午4:47 19-7-17
     **/
    public void addListener(EditPanelListener editPanelListener) {
        this.listeners.add(editPanelListener);
    }

    /**
     * @description 初始化编辑面板
     * @return void
     * @author htx
     * @date 下午4:57 19-7-17
     **/
    void init() {
        initComponent(); // 初始化控件
        settingLayout(); // 设置布局
        registerEvent(); // 监听事件
    }

    /**
     * @description 初始化控件
     * @return void
     * @author htx
     * @date 下午4:57 19-7-17
     **/
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
        cancelButton = new JButton("取消");
        cancelButton.setFont(font);
        buttonBox.add(editButton);
        buttonBox.add(deleteButton);
        buttonBox.add(cancelButton);
    }

    /**
     * @description  注册组件事件
     * @return void
     * @author htx
     * @date 下午4:50 19-7-17
     **/
    private void registerEvent() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (EditPanelListener listener:
                        listeners) {
                    listener.listenerCancelButton(e);
                }
            }
        });
    }

    /*
     * @description 设置布局
     * @return void
     * @author htx
     * @date 下午4:14 19-7-17
     **/
    void settingLayout() {
        setLayout(new MigLayout());
        add(new Label(), "wrap 10px");
        add(systemNameLabel);
        add(systemNameTextField, "wrap");
        add(superPasswordSeedLabel);
        add(superPasswordSeedTextField);
        add(addButton, "wrap 20px");
        add(systemList, "span 2");
        add(buttonBox, "wrap");
    }

}
