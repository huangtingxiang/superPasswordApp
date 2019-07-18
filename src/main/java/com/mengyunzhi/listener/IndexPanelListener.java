package com.mengyunzhi.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * @author: htx
 * 首页面板监听
 * @date: 19-7-17
 */
public interface IndexPanelListener {

    /**
     * @param event
     * @return void
     * @description 当selectSystemButton按钮点击时触发
     * @author htx
     * @date 下午4:43 19-7-17
     **/
    void listenerSelectSystemButton(ActionEvent event);

    void listenerSystemSelectedItemChange(ItemEvent e);

    void listenerCopySuperPasswordButton(ActionEvent event);

    void listenerCountSuperPasswordButton(ActionEvent event);
}
