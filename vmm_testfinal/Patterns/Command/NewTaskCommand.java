/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Command.*;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
/**
 *
 * @author racme
 */
public class NewTaskCommand implements Command{
    public Task task;
    
    public NewTaskCommand(Task task){
        this.task = task;
    }
    
    @Override
    public void execute() {
        task.New();
    }
}
