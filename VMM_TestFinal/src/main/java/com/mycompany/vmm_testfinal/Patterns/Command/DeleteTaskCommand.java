/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Command.*;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.UI.GUI.Components.sidebarFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
/**
 *
 * @author racme
 */
public class DeleteTaskCommand implements Command{
    
    CommandControl taskControl = new CommandControl();
    private Task task;
    private static JPanel panel;
    private static JPanel parentPanel;
    
    public DeleteTaskCommand(){
    }
    
    public DeleteTaskCommand(JPanel panel){
        this.panel = panel;
    }
    
    public void deleteTask(Task task){
        this.task = task;
    }
    
    @Override
    public void execute() {
        task.Delete();
        
        UpdateBodyCommand ubc = new UpdateBodyCommand();
        ubc.newBody(null);
        taskControl.setCommand(ubc);
        taskControl.clickedButton();
        
        parentPanel = (JPanel)panel.getParent();
        
        JPanel grandParentPanel = new JPanel();
        JPanel greatGrandParentPanel = new JPanel();
        
        System.out.println(parentPanel.getParent().getName());
        
        try{
            grandParentPanel =  (JPanel)parentPanel.getParent();
            greatGrandParentPanel = (JPanel)grandParentPanel.getParent();
            parentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
                                                      parentPanel.getPreferredSize().height-panel.getPreferredSize().height));
            grandParentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
                                                      grandParentPanel.getPreferredSize().height-panel.getPreferredSize().height));
            greatGrandParentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
                                                      greatGrandParentPanel.getPreferredSize().height-panel.getPreferredSize().height));
        }catch(Exception ex){
            ex.printStackTrace();
        
//        if(parentPanel.getLayout() instanceof FlowLayout){
//           grandParentPanel =  (JPanel)parentPanel.getParent();
//           greatGrandParentPanel = (JPanel)grandParentPanel.getParent();
////           grandParentPanel.setBackground(Color.YELLOW);
//           
//            System.out.println(panel.getPreferredSize().height);
//           
//            parentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
//                                                      parentPanel.getPreferredSize().height-panel.getPreferredSize().height));
//            grandParentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
//                                                      grandParentPanel.getPreferredSize().height-panel.getPreferredSize().height));
//            greatGrandParentPanel.setPreferredSize(new Dimension(panel.getPreferredSize().width,
//                                                      greatGrandParentPanel.getPreferredSize().height-panel.getPreferredSize().height));
//            
        }
        parentPanel.remove(panel);        
        parentPanel.getParent().revalidate();
        parentPanel.getParent().repaint();
    }
    
}
