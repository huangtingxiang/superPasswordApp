package com.mengyunzhi.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author: htx
 * @date: 19-7-18
 */
public class EditPanel extends JPanel {

    private Font font;

    JLabel nameLabel;

    JTextField nameTextField;

    JLabel seedLabel;

    JTextField seedTextField;

    public EditPanel() {
        init();
    }

    private void init() {
        font = new Font("Serif", Font.PLAIN, 16);
        setLayout(new MigLayout());
        nameLabel = new JLabel("系统名称: ");
        nameLabel.setFont(font);
        nameTextField = new JTextField(30);
        nameTextField.setFont(font);
        seedLabel = new JLabel("超级密码种子: ");
        seedLabel.setFont(font);
        seedTextField = new JTextField(30);
        seedTextField.setFont(font);
        add(nameLabel);
        add(nameTextField, "wrap");
        add(seedLabel);
        add(seedTextField, "wrap");
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JLabel getSeedLabel() {
        return seedLabel;
    }

    public JTextField getSeedTextField() {
        return seedTextField;
    }
}
