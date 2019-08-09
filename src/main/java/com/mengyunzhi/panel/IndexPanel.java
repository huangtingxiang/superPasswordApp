package com.mengyunzhi.panel;

import com.mengyunzhi.entity.SystemMessage;
import com.mengyunzhi.listener.IndexPanelListener;
import com.mengyunzhi.service.SystemMessageService;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: htx
 * 首页面板
 * @date: 19-7-17
 */
public class IndexPanel extends JPanel {

    public final static String name = "indexPanel";

    private SystemMessageService systemMessageService = SystemMessageService.getInstance();

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

    private Set<IndexPanelListener> listeners = new HashSet<>(); // 外部监听者

    public IndexPanel() {
        init();
    }

    /**
     * @param indexPanelListener
     * @return void
     * @description 添加监听者
     * @author htx
     * @date 下午4:47 19-7-17
     **/
    public void addListener(IndexPanelListener indexPanelListener) {
        this.listeners.add(indexPanelListener);
    }

    /**
     * @return void
     * @description 初始化面板信息
     * @author htx
     * @date 下午4:56 19-7-17
     **/
    private void init() {
        initComponent(); // 初始化控件
        settingLayout(); // 设置布局
        registerEvent(); // 注册事件
    }

    /**
     * @return void
     * @description 初始化控件
     * @author htx
     * @date 下午4:55 19-7-17
     **/
    private void initComponent() {
        font = new Font("Serif", Font.PLAIN, 16); // 设置字体
        initComponentForSuperPasswordSeed();
        initComponentForSuperPassword();
        initComponentForSystemSelected();

    }

    /**
     * @description 初始化超级密码种子控件
     * @author htx
     * @date 下午8:22 19-7-18
     **/
    private void initComponentForSuperPasswordSeed() {
        // 初始化超级密码
        superPasswordSeedLabel = new JLabel("超级密码种子:"); // 超级密码种子
        superPasswordSeedLabel.setFont(font);
        superPasswordSeedTextField = new JTextField(100);    // 超级密码种子输入框
        superPasswordSeedTextField.setFont(font);
    }

    /**
     * @description 初始化超级密码控件
     * @author htx
     * @date 下午8:22 19-7-18
     **/
    private void initComponentForSuperPassword() {
        superPasswordLabel = new JLabel("超级密码生成:"); // 生成超级密码
        superPasswordLabel.setFont(font);
        superPassword = new JLabel(); // 超级密码文本
        copySuperPasswordButton = new JButton("复制"); // 复制按钮
        copySuperPasswordButton.setFont(font);
        countSuperPasswordButton = new JButton("生成");   // 生成按钮
        countSuperPasswordButton.setFont(font);
    }

    /**
     * @description 初始化下拉列表控件
     * @author htx
     * @date 下午8:22 19-7-18
     **/
    private void initComponentForSystemSelected() {
        // 初始化管理按钮
        selectSystemButton = new JButton("管理"); // 管理按钮
        selectSystemButton.setFont(font);
        // 初始化系统设置
        systemNameLabel = new JLabel("系统名称:"); // 系统名称
        systemNameLabel.setFont(font);
        systemSelected = new JComboBox();   // 系统下拉框
        systemSelected.setFont(font);
        systemSelected.setRenderer(new SelectListCellRenderer());
    }

    /**
     * @return void
     * @description 注册组件事件
     * @author htx
     * @date 下午4:50 19-7-17
     **/
    private void registerEvent() {
        registerSelectSystemButtonEvent();
        registerSystemSelectedEvent();
        registerCopySuperPasswordButtonEvent();
        registerCountSuperPasswordButtonEvent();
    }

    /**
     * @description  注册选择系统按钮事件
     * @author htx
     * @date 下午8:46 19-7-18
     **/
    public void registerSelectSystemButtonEvent() {
        selectSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IndexPanelListener listener :
                        listeners) {
                    listener.listenerSelectSystemButton(e);
                }
            }
        });
    }

    /**
     * @description  注册下拉框事件
     * @author htx
     * @date 下午8:46 19-7-18
     **/
    public void registerSystemSelectedEvent() {
        systemSelected.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    for (IndexPanelListener listener :
                            listeners) {
                        listener.listenerSystemSelectedItemChange(e);
                    }
                }
            }
        });
    }

    /**
     * @description  注册复制按钮事件
     * @author htx
     * @date 下午8:46 19-7-18
     **/
    public void registerCopySuperPasswordButtonEvent() {
        copySuperPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IndexPanelListener listener :
                        listeners) {
                    listener.listenerCopySuperPasswordButton(e);
                }
            }
        });
    }

    /**
     * @description  注册计算按钮事件
     * @author htx
     * @date 下午8:46 19-7-18
     **/
    public void registerCountSuperPasswordButtonEvent() {
        countSuperPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (IndexPanelListener listener :
                        listeners) {
                    listener.listenerCountSuperPasswordButton(e);
                }
            }
        });
    }

    /**
     * @param
     * @return void
     * @description 重新加载数据到下拉框
     * @author htx
     * @date 下午4:36 19-7-18
     **/
    public void reload() {
        systemSelected.removeAllItems();
        superPasswordSeedTextField.setText("");
        SystemMessage[] systemMessages = systemMessageService.getAll();
        for (SystemMessage systemMessage :
                systemMessages) {
            systemSelected.addItem(systemMessage);
        }
    }

    /**
     * @description  将此系统信息设置为默认选中
     * @param systemMessage
     * @return void
     * @author htx
     * @date 下午8:46 19-7-18
     **/
    public void setDefaultSelected(SystemMessage systemMessage) {
        for (int i = 0; i < systemSelected.getItemCount(); i++) {
            SystemMessage systemMessageItem = (SystemMessage) systemSelected.getItemAt(i);
            if (systemMessageItem.getName().equals(systemMessage.getName()) && systemMessageItem.getSuperPasswordSeed().equals(systemMessage.getSuperPasswordSeed())) {
                systemSelected.setSelectedItem(systemMessageItem);
                superPasswordSeedTextField.setText(systemMessageItem.getSuperPasswordSeed());
                return;
            }
        }
    }

    /**
     * @return void
     * @description 设置布局
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
        add(copySuperPasswordButton, "split 2,gapleft 100");
        add(countSuperPasswordButton,"gapleft 50");
    }

    public JComboBox getSystemSelected() {
        return systemSelected;
    }

    public JLabel getSystemNameLabel() {
        return systemNameLabel;
    }

    public JButton getSelectSystemButton() {
        return selectSystemButton;
    }

    public JLabel getSuperPasswordSeedLabel() {
        return superPasswordSeedLabel;
    }

    public JTextField getSuperPasswordSeedTextField() {
        return superPasswordSeedTextField;
    }

    public JLabel getSuperPasswordLabel() {
        return superPasswordLabel;
    }

    public JLabel getSuperPassword() {
        return superPassword;
    }

    public JButton getCopySuperPasswordButton() {
        return copySuperPasswordButton;
    }

    public JButton getCountSuperPasswordButton() {
        return countSuperPasswordButton;
    }

    /**
     * @description  设置下拉框渲染方式
     * @author htx
     * @date 下午6:26 19-7-18
     **/
    class SelectListCellRenderer implements ListCellRenderer {

        protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) {
                SystemMessage message = (SystemMessage) value;
                renderer.setText(message.getName());
            }
            return renderer;
        }
    }

}
