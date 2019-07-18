package com.mengyunzhi.panel;

import com.google.common.collect.Iterables;
import com.mengyunzhi.entity.SystemMessage;
import com.mengyunzhi.listener.EditPanelListener;
import com.mengyunzhi.service.SystemMessageService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private static final Logger logger = Logger.getLogger(EditPanel.class.getName());

    public final static String name = "editPanel"; // 面板名字

    SystemMessageService systemMessageService = SystemMessageService.getInstance(); // 系统信息服务

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

    DefaultListModel<SystemMessage> systemMessageListModel; // 下拉框模型

    JScrollPane scrollPane; // 下拉框滚动条

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
        initComponentForSystem();
        initComponentForSuperPasswordSeed();
        initComponentForSystemList();
        initComponentForButtonBox();
    }

    /**
     * @description 初始化按钮组
     * @return void
     * @author htx
     * @date 下午7:52 19-7-18
     **/
    public void initComponentForButtonBox() {
        buttonBox = new Box(BoxLayout.Y_AXIS);
        editButton = new JButton("编辑");
        editButton.setFont(font);
        deleteButton = new JButton("删除");
        deleteButton.setFont(font);
        cancelButton = new JButton("返回");
        cancelButton.setFont(font);
        buttonBox.add(editButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonBox.add(cancelButton);
    }

    /**
     * @description 初始化超级密码种子 的部件
     * @return void
     * @author htx
     * @date 下午7:50 19-7-18
     **/
    public void initComponentForSuperPasswordSeed() {
        superPasswordSeedLabel = new JLabel("超级密码种子:");
        superPasswordSeedLabel.setFont(font);
        superPasswordSeedTextField = new JTextField(40);
        superPasswordSeedTextField.setFont(font);
        addButton = new JButton("保存");
        addButton.setFont(font);
    }

    /**
     * @description  初始化系统信息名称，输入框部件
     * @return void
     * @author htx
     * @date 下午7:49 19-7-18
     **/
    public void initComponentForSystem() {
        systemNameLabel = new JLabel("系统名称:");
        systemNameLabel.setFont(font);
        systemNameTextField = new JTextField(20);
        systemNameTextField.setFont(font);
    }

    /**
     * @description  初始化系统信息 列表
     * @return void
     * @author htx
     * @date 下午7:51 19-7-18
     **/
    public void initComponentForSystemList() {
        SystemMessage[] systemMessages = systemMessageService.getAll();
        systemMessageListModel = new DefaultListModel<>();
        for (SystemMessage systemMessage :
                systemMessages) {
            systemMessageListModel.addElement(systemMessage);
        }
        systemList = new JList<>(systemMessageListModel);
        systemList.setCellRenderer(new SystemListCellRenderer());
        scrollPane = new JScrollPane(systemList);
        scrollPane.setPreferredSize(new Dimension(650, 500));
        systemList.setVisibleRowCount(20);
        systemList.setPreferredSize(new Dimension(650, 500));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * @return void
     * @description 注册组件事件
     * @author htx
     * @date 下午4:50 19-7-17
     **/
    private void registerEvent() {
        systemList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    for (EditPanelListener listener :
                            listeners) {
                        listener.listenerDoubleClickList(systemList.getSelectedValue());
                    }
                }
                super.mouseClicked(e);
            }
        });
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
                systemMessageService.saveAll(selectModelToArray());
                systemNameTextField.setText("");
                superPasswordSeedTextField.setText("");
                for (EditPanelListener listener :
                        listeners) {
                    listener.listenerAddButton(e);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (systemList.getSelectedValue() != null) {
                    systemMessageListModel.removeElement(systemList.getSelectedValue());
                    systemMessageService.saveAll(selectModelToArray());
                    for (EditPanelListener listener :
                            listeners) {
                        listener.listenerDeleteButton(e);
                    }
                }
            }
        });
    }



    /**
     * @description 将jlist model 转换为数组
     * @return com.mengyunzhi.entity.SystemMessage[]
     * @author htx
     * @date 下午7:53 19-7-18
     **/
    private SystemMessage[] selectModelToArray() {
        int size = systemMessageListModel.size();
        SystemMessage[] messages = new SystemMessage[size];
        for (int i = 0; i < size; i++) {
            messages[i] = systemMessageListModel.get(i);
        }
        return messages;
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
        add(scrollPane, "span 4");
        add(buttonBox, "wrap");
    }

    /**
     * @author htx
     * @description 设置列表渲染方式
     * @date 下午6:26 19-7-18
     **/
    class SystemListCellRenderer implements ListCellRenderer<SystemMessage> {
        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList<? extends SystemMessage> list, SystemMessage value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            renderer.setText(value.getName() + "(" + value.getSuperPasswordSeed() + ")");
            setEnabled(list.isEnabled());
            return renderer;
        }
    }

}
