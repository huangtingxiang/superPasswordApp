package com.mengyunzhi.frame;

import com.mengyunzhi.entity.SystemMessage;
import com.mengyunzhi.panel.EditPanel;
import com.mengyunzhi.panel.ManagerPanel;
import com.mengyunzhi.panel.IndexPanel;
import com.mengyunzhi.service.DBService;
import com.mengyunzhi.service.SuperPasswordService;
import com.mengyunzhi.service.SystemMessageService;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author: htx
 * 主窗体
 * @date: 19-7-16
 */
public class MainFrame extends JFrame {

    static private Logger logger = Logger.getLogger(DBService.class.getName());

    private SuperPasswordService superPasswordService = SuperPasswordService.getInstance();

    ManagerPanel managerPanel; // 编辑面板

    IndexPanel indexPanel; // 首页面板

    CardLayout layout;

    JPanel mainPanel; // 主面板

    SystemMessageService systemMessageService = SystemMessageService.getInstance(); // 系统信息服务

    public MainFrame() {
        initFrame();
        registerListener();
        setLocationRelativeTo(null);
    }

    /**
     * @param
     * @return void
     * @description 初始化主界面
     * @author htx
     * @date 下午9:52 19-7-16
     **/
    private void initFrame() {
        // 初始化窗体
        layout = new CardLayout();
        setSize(760, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("超级密码生成器");
        // 设置面板
        mainPanel = new JPanel(layout);
        managerPanel = new ManagerPanel();
        indexPanel = new IndexPanel();
        mainPanel.add(indexPanel, IndexPanel.name);
        mainPanel.add(managerPanel, ManagerPanel.name);
        getContentPane().add(mainPanel);
        // 设置面板初始数据
        initIndexPanel();
        initManagerPanel();
    }

    /**
     * @description  初始化index面板
     * @author htx
     * @date 下午8:51 19-7-18
     **/
    private void initIndexPanel() {
        indexPanel.reload();
        SystemMessage defaultSystemMessage = systemMessageService.get(SystemMessage.lastCountKey);
        if (defaultSystemMessage != null) {
            indexPanel.setDefaultSelected(defaultSystemMessage);
        } else {
            if (indexPanel.getSystemSelected().getItemCount() > 0) {
                indexPanel.setDefaultSelected((SystemMessage) indexPanel.getSystemSelected().getItemAt(0));
            }

        }
    }

    /**
     * @description  初始化edit面板
     * @author htx
     * @date 下午8:51 19-7-18
     **/
    private void  initManagerPanel() {
        // edit面板 初始化完成时注入数据
        SystemMessage[] systemMessages = systemMessageService.getAll();
        for (SystemMessage systemMessage :
                systemMessages) {
            managerPanel.getSystemMessageListModel().addElement(systemMessage);
        }
    }

    /**
     * @param
     * @return void
     * @description 注册监听者监听子面板事件
     * @author htx
     * @date 下午4:44 19-7-17
     **/
    void registerListener() {
        this.indexPanel.addListener(new IndexPanelListener());
        this.managerPanel.addListener(new ManagerListener());
    }

    /**
     * @author htx
     * @description 内部类 负责监听index面板
     * @date 下午4:45 19-7-17
     **/
    class IndexPanelListener implements com.mengyunzhi.listener.IndexPanelListener {

        /**
         * @param event
         * @return void
         * @description 选择按钮点击时切换到编辑面板
         * @author htx
         * @date 下午4:58 19-7-17
         **/
        @Override
        public void listenerSelectSystemButton(ActionEvent event) {
            layout.show(mainPanel, ManagerPanel.name);
        }

        @Override
        public void listenerSystemSelectedItemChange(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                SystemMessage systemMessage = (SystemMessage) e.getItem();
                indexPanel.getSuperPasswordSeedTextField().setText(systemMessage.getSuperPasswordSeed());
            }
        }

        @Override
        public void listenerCopySuperPasswordButton(ActionEvent event) {
            StringSelection stringSelection = new StringSelection(indexPanel.getSuperPassword().getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }

        @Override
        public void listenerCountSuperPasswordButton(ActionEvent event) {
            String superPassword1 = superPasswordService.getSuperPassword(indexPanel.getSuperPasswordSeedTextField().getText(), new Date());
            indexPanel.getSuperPassword().setText(superPassword1);
            if (indexPanel.getSystemSelected().getSelectedItem() != null) {
                systemMessageService.save(SystemMessage.lastCountKey, (SystemMessage) indexPanel.getSystemSelected().getSelectedItem());
            }
        }
    }

    /**
     * @author htx
     * @description 内部类 负责监听edit面板
     * @date 下午4:45 19-7-17
     **/
    class ManagerListener implements com.mengyunzhi.listener.ManagerListener {
        @Override
        public void listenerAddButton(ActionEvent event) {
            // 添加按钮点击时 增加系统信息到数据库 并更新index面板
            SystemMessage systemMessage = new SystemMessage();
            systemMessage.setName(managerPanel.getSystemNameTextField().getText());
            systemMessage.setSuperPasswordSeed(managerPanel.getSuperPasswordSeedTextField().getText());
            managerPanel.getSystemMessageListModel().addElement(systemMessage);
            systemMessageService.saveAll(managerPanel.selectModelToArray());
            managerPanel.getSystemNameTextField().setText("");
            managerPanel.getSuperPasswordSeedTextField().setText("");
            indexPanel.reload();
        }

        @Override
        public void listenerCancelButton(ActionEvent event) {
            // 返回切换到index面板
            indexPanel.reload();
            layout.show(mainPanel, IndexPanel.name);
        }

        @Override
        public void listenerDoubleClickList(SystemMessage systemMessage) {
            // 双击时跳回index面板 并选中
            indexPanel.setDefaultSelected(systemMessage);
            layout.show(mainPanel, IndexPanel.name);
        }

        @Override
        public void listenerDeleteButton(ActionEvent event) {
            // 删除edit面板列表模型并保存
            managerPanel.getSystemMessageListModel().removeElement(managerPanel.getSystemList().getSelectedValue());
            systemMessageService.saveAll(managerPanel.selectModelToArray());
        }

        @Override
        public void listenerEditButton(ActionEvent event) {

            SystemMessage message = managerPanel.getSystemList().getSelectedValue();

            EditPanel editPanel = new EditPanel();

            if (message != null) {
                editPanel.getNameTextField().setText(message.getName());
                editPanel.getSeedTextField().setText(message.getSuperPasswordSeed());
            }

            int result = JOptionPane.showConfirmDialog(MainFrame.this,
                    editPanel,
                    "编辑",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                message.setName(editPanel.getNameTextField().getText());
                message.setSuperPasswordSeed(editPanel.getSeedTextField().getText());
                int index = managerPanel.getSystemList().getSelectedIndex();
                managerPanel.getSystemMessageListModel().set(index, message);
                systemMessageService.saveAll(managerPanel.selectModelToArray());
            }
        }

    }

}
