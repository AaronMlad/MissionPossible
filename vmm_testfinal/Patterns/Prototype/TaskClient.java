package com.mycompany.vmm_testfinal.Patterns.Prototype;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author racme
 */
public class TaskClient {
    private Task taskPrototype;
    
    public TaskClient(Task taskPrototype){
        this.taskPrototype = taskPrototype;
    }
    
    public Task cloneTask(){
        return taskPrototype.Clone();
    }
}
