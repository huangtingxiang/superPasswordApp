package com.mengyunzhi.panel;

import com.google.common.collect.Iterables;
import com.mengyunzhi.entity.SystemMessage;
import com.mengyunzhi.listener.EditPanelListener;
import com.mengyunzhi.service.SystemMessageService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author: htx
 * @date: 19-7-17
 */
public class EditPanel extends JPanel {

    public final static String name = "editPanel";

    SystemMessageService systemMessageService = SystemMessageService.getInstance();

    static private Logger logger = Logger.getLogger(EditPanel.class.getName());


    private JLabel systemNameLabel; // 系统名称label

    private JTextField systemNameTextField; // 系统名称输入框

    private JLabel superPasswordSeedLabel; // 随机密码label

    private JTextField superPasswordSeedTextField; // 随机密码输入框

    private JButton addButton; // 添加按钮

    private JList<SystemMessage> systemList; // 系统列表

    private Font font; // 字体

    private JButton editButton; // 编辑按钮

    private JButton deleteButton; // 删除按钮

    private JButton cancelButton; // 取消按钮

    private Box buttonBox; // 按钮组

    DefaultListModel<SystemMessage> systemMessageListModel;

    ArrayList<SystemMessage> systemMessageList;

    JScrollPane scrollPane;

    private Set<EditPanelListener> listeners = new HashSet<>(); // 外部监听者

    public EditPanel() {
        init();
    }

    /**
     * @param editPanelListener
     * @return void
     * @description 添加监听者
     * @author htx
     * @date 下午4:47 19-7-17
     **/
    public void addListener(EditPanelListener editPanelListener) {
        this.listeners.add(editPanelListener);
    }

    /**
     * @return void
     * @description 初始化编辑面板
     * @author htx
     * @date 下午4:57 19-7-17
     **/
    void init() {
        initComponent(); // 初始化控件
        settingLayout(); // 设置布局
        registerEvent(); // 监听事件
    }

    /**
     * @return void
     * @description 初始化控件
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

        SystemMessage[] systemMessages = systemMessageService.getAll();
        systemMessageList = new ArrayList<>(Arrays.asList(systemMessages));
        systemMessageListModel = new DefaultListModel<>();
        for (SystemMessage systemMessage :
                systemMessages) {
            systemMessageListModel.addElement(systemMessage);
        }
        systemList = new JList<>(systemMessageListModel);

        systemList.setCellRenderer(new SystemListCellRenderer());
        scrollPane = new JScrollPane(systemList);
        systemList.setVisibleRowCount(20);
        systemList.setPreferredSize(new Dimension(450, 500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
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
     * @return void
     * @description 注册组件事件
     * @author htx
     * @date 下午4:50 19-7-17
     **/
    private void registerEvent() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (EditPanelListener listener :
                        listeners) {
                    listener.listenerCancelButton(e);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemMessage systemMessage = new SystemMessage();
                systemMessage.setName(systemNameTextField.getText());
                systemMessage.setSuperPasswordSeed(superPasswordSeedTextField.getText());
                systemMessageListModel.addElement(systemMessage);
                systemMessageList.add(systemMessage);
                systemMessageService.saveAll(Iterables.toArray(systemMessageList, SystemMessage.class));
                systemNameTextField.setText("");
                superPasswordSeedTextField.setText("");
                for (EditPanelListener listener :
                        listeners) {
                    listener.listenerAddButton(e);
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
        add(scrollPane, "span 2");
        add(buttonBox, "wrap");
    }

    class SystemListCellRenderer extends JPanel implements ListCellRenderer<SystemMessage> {
        JLabel name;
        JLabel speed;

        SystemListCellRenderer() {
            name = new JLabel();
            speed = new JLabel();
            name.setFont(font);
            speed.setFont(font);
            setBackground(Color.white);
            setLayout(new MigLayout());
            add(name);
            add(speed);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends SystemMessage> list, SystemMessage value, int index, boolean isSelected, boolean cellHasFocus) {
            name.setText(value.getName() + "(" + value.getSuperPasswordSeed() + ")");
            setEnabled(list.isEnabled());
            return this;
        }
    }

}
