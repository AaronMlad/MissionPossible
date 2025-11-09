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
public class CloneTaskCommand implements Command{
    private Task task;
    private Task taskClone;
    
    public CloneTaskCommand(Task task){
        this.task = task;
    }
    
    public Task getTaskClone(){
        return taskClone;
    }
    @Override
    public void execute() {
        this.taskClone = task.Clone();
        System.out.println("TASK SUCCESSFULLY CLONED");
    }
}
