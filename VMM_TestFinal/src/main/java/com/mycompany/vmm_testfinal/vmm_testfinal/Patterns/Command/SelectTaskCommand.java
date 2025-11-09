/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author racme
 */
public class SelectTaskCommand implements Command{
    CommandControl taskControl = new CommandControl();
    private static Task task;
    private static JPanel panel;
    
    public SelectTaskCommand(Task task, JPanel panel){
        this.task = task;
        this.panel = panel;
    }
    @Override
    public void execute() {
        System.out.println(task.getTitle()+" SELECTED");
        System.out.println(task.getDescription());
        System.out.println(task.getStatus());
        System.out.println(task.getDeadline());
        
        UpdateBodyCommand ubc = new UpdateBodyCommand();
        ubc.newBody(task);
        
        SaveTaskCommand stc = new SaveTaskCommand(task);
        DeleteTaskCommand stc2 = new DeleteTaskCommand(panel);
        UpdateTaskPanelCommand utpc = new UpdateTaskPanelCommand(panel);
        
//        updateSidebar us = new updateSidebar();
//        updateSidebar.setPanel(panel);
        
        taskControl.setCommand(ubc);
        taskControl.clickedButton();
    }
}
