package com.mengyunzhi.frame;

import com.mengyunzhi.listener.EditPanelListener;
import com.mengyunzhi.panel.EditPanel;
import com.mengyunzhi.panel.IndexPanel;
import com.mengyunzhi.service.DBService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author: htx
 * 主窗体
 * @date: 19-7-16
 */
public class MainFrame extends JFrame {

    static private Logger logger = Logger.getLogger(DBService.class.getName());

    DBService dbService = DBService.getInstance();

    EditPanel editPanel; // 编辑面板

    IndexPanel indexPanel; // 首页面板

    CardLayout layout;

    JPanel mainPanel; // 主面板

    public MainFrame() throws IOException {
        initFrame();
        registerListener();
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
        layout = new CardLayout();
        mainPanel = new JPanel(layout);
        editPanel = new EditPanel();
        indexPanel = new IndexPanel();
        setTitle("超级密码生成器");
        mainPanel.add(indexPanel, IndexPanel.name);
        mainPanel.add(editPanel, EditPanel.name);
        setSize(500, 500);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * @description  注册监听者监听子面板事件
     * @param
     * @return void
     * @author htx
     * @date 下午4:44 19-7-17
     **/
    void registerListener() {
        this.indexPanel.addListener(new IndexPanelListener());
        this.editPanel.addListener(new EditPanelListener());
    }

    /**
     * @description 内部类 负责监听index面板
     * @author htx
     * @date 下午4:45 19-7-17
     **/
    class IndexPanelListener implements com.mengyunzhi.listener.IndexPanelListener {

        /**
         * @description 选择按钮点击时切换到编辑面板
         * @param event
         * @return void
         * @author htx
         * @date 下午4:58 19-7-17
         **/
        @Override
        public void listenerSelectSystemButton(ActionEvent event) {
            layout.show(mainPanel, EditPanel.name);
        }
    }

    /**
     * @description 内部类 负责监听edit面板
     * @author htx
     * @date 下午4:45 19-7-17
     **/
    class EditPanelListener implements com.mengyunzhi.listener.EditPanelListener {
        @Override
        public void listenerAddButton(ActionEvent event) {
            System.out.println("点击了");
            indexPanel.reload();
        }

        @Override
        public void listenerCancelButton(ActionEvent event) {
            layout.show(mainPanel, IndexPanel.name);
        }
    }

}
