package com.mengyunzhi.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author: htx
 * 主窗体
 * @date: 19-7-16
 */
public class MainFrame extends JFrame{

    public static final MainFrame mainFrame = new MainFrame();

    JComboBox systemSelected; // 系统名称下拉框

    JLabel systemNameLabel; // 系统名称label

    JButton selectSystemButton; // 选择系统按钮

    JLabel superPasswordSeedLabel; // 超级密码种子label

    JTextField superPasswordSeedTextField; // 超级密码种子输入框

    JLabel superPasswordLabel; // 超级密码label

    JLabel superPassword; // 生成的超级密码

    private MainFrame() {
        initFrame();
        this.setSize(600,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setVisible(true);
    }

    /**
     * @description  初始化主界面
     * @param
     * @return void
     * @author htx
     * @date 下午9:52 19-7-16
     **/
    private void initFrame() {
//        // 初始化控件
//        systemNameLabel = new JLabel("系统名称:");
//        systemSelected = new JComboBox();
//        systemSelected.addItem("爱利益学生系统");
//        systemSelected.addItem("计量项目");
//        selectSystemButton = new JButton("添加");
//        superPasswordSeedLabel = new JLabel("超级密码种子:");
//        superPasswordSeedTextField = new JTextField();
//        superPasswordLabel = new JLabel("超级密码生成:");
//        superPassword = new JLabel("12345679fdsfsdfds");
//
//        // 设置布局
//        GridBagLayout layout = new GridBagLayout();
//        GridBagConstraints s= new GridBagConstraints();
//        s.fill = GridBagConstraints.HORIZONTAL;
//        s.gridwidth=2;
//        s.weightx = 0;
//        s.weighty=0;
//        layout.setConstraints(systemNameLabel, s);
//        s.gridwidth=4;
//        layout.setConstraints(systemSelected, s);
//        s.gridwidth=0;
//        layout.setConstraints(selectSystemButton, s);
//
//        // 添加控件
//        this.setLayout(layout);
//        this.add(systemNameLabel);
//        this.add(systemSelected);
//        this.add(selectSystemButton);
//        this.pack();

        GridBagLayout gridBag = new GridBagLayout();    // 布局管理器
        GridBagConstraints c = null;                    // 约束

        JPanel panel = new JPanel(gridBag);

        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");
        JButton btn04 = new JButton("Button04");
        JButton btn05 = new JButton("Button05");
        JButton btn06 = new JButton("Button06");
        JButton btn07 = new JButton("Button07");
        JButton btn08 = new JButton("Button08");
        JButton btn09 = new JButton("Button09");
        JButton btn10 = new JButton("Button10");

        /* 添加 组件 和 约束 到 布局管理器 */
        // Button01
        c = new GridBagConstraints();
        gridBag.addLayoutComponent(btn01, c); // 内部使用的仅是 c 的副本

        // Button02
        c = new GridBagConstraints();
        gridBag.addLayoutComponent(btn02, c);

        // Button03
        c = new GridBagConstraints();
        gridBag.addLayoutComponent(btn03, c);

        // Button04 显示区域占满当前行剩余空间（换行），组件填充显示区域
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn04, c);

        // Button05 显示区域独占一行（换行），组件填充显示区域
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn05, c);

        // Button06 显示区域占到当前尾倒车第二个单元格（下一个组件后需要手动换行），组件填充显示区域
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn06, c);

        // Button07 放置在当前行最后一个单元格（换行）
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridBag.addLayoutComponent(btn07, c);

        // Button08 显示区域占两列，组件填充显示区域
        c = new GridBagConstraints();
        c.gridheight = 2;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn08, c);

        // Button09 显示区域占满当前行剩余空间（换行），组件填充显示区域
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn09, c);

        // Button10 显示区域占满当前行剩余空间（换行），组件填充显示区域
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        gridBag.addLayoutComponent(btn10, c);

        /* 添加 组件 到 内容面板 */
        panel.add(btn01);
        panel.add(btn02);
        panel.add(btn03);
        panel.add(btn04);
        panel.add(btn05);
        panel.add(btn06);
        panel.add(btn07);
        panel.add(btn08);
        panel.add(btn09);
        panel.add(btn10);


        this.getContentPane().add(panel);
    }

}
