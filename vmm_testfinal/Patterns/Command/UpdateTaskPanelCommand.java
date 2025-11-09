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
public class UpdateTaskPanelCommand implements Command{

    private static JPanel panel;
    private static Task task;
    
    public UpdateTaskPanelCommand(){
    }
    
    public UpdateTaskPanelCommand(JPanel panel){
        this.panel = panel;
    }
    
    public void updateLabel(Task task){
        this.task = task;
    }
    
    @Override
    public void execute() {
        BorderLayout layout = (BorderLayout) panel.getLayout();
        Component centermostComponent = layout.getLayoutComponent(BorderLayout.CENTER);
                            
        if(centermostComponent instanceof JLabel){
            ((JLabel)centermostComponent).setText(task.getTitle());
        }
    }
    
}
