package com.mengyunzhi.listener;

import com.mengyunzhi.entity.SystemMessage;

import java.awt.event.ActionEvent;

/**
 * @author: htx
 * @describe 编辑面板监听者
 * @date: 19-7-17
 */
public interface ManagerListener {

    /**
     * @description 当addButton按钮点击时触发
     * @param event
     * @return void
     * @author htx
     * @date 下午4:43 19-7-17
     **/
    void listenerAddButton(ActionEvent event);

    /**
     * @description 当cancelButton按钮点击时触发
     * @param event
     * @return void
     * @author htx
     * @date 下午4:43 19-7-17
     **/
    void listenerCancelButton(ActionEvent event);

    void listenerDoubleClickList(SystemMessage systemMessage);

    void listenerDeleteButton(ActionEvent event);

    void listenerEditButton(ActionEvent event);
}
