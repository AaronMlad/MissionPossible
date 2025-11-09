/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Command.*;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Observer;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Subject;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskPanelFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
/**
 *
 * @author racme
 */
public class SaveTaskCommand implements Command{
    CommandControl taskControl = new CommandControl();
    private static Task task;
    private String[] information;
    
    /////////////////////////////
    
    public SaveTaskCommand(){
    }
    
    public SaveTaskCommand(Task task){
        this.task = task;
    }
    
    public void saveTask(String[] information){
        this.information = information;
    }
    
    /////////////////////////////
    
    @Override
    public void execute() {
        task.Save(information);
        
        UpdateTaskPanelCommand utpc = new UpdateTaskPanelCommand();
        utpc.updateLabel(task);
        taskControl.setCommand(utpc);
        taskControl.clickedButton();
        
        
    }
}
