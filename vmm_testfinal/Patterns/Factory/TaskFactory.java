/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Factory;

import com.mycompany.vmm_testfinal.Patterns.Tasks.ProjectTask;
import com.mycompany.vmm_testfinal.Patterns.Tasks.SimpleTask;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;

/**
 *
 * @author racme
 */
public class TaskFactory {
    public static Task newTask(String taskType){
        switch(taskType){
            case "SIMPLE":
                return new SimpleTask();
            case "PROJECT":
                return new ProjectTask();
            default:
                return new SimpleTask();
        }
    }
}
