/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Tasks;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Observer;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.UI.GUI.Components.sidebarFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author racme
 */
public class SimpleTask implements Task{
    private int id;
    private String title;
    private String description;
    private String status;
    private String deadline;
    private Integer projectId = 0;
    private ProjectTask parentProject;
    //REQUIRED GETTERS
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public String getDeadline() {
        return deadline;
    }
    //SETTERS
    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public void setDescription(String desc) {
        this.description = desc;
    }
    @Override
    public void setStatus(String newStatus){
        this.status = newStatus;
//        notifyObservers();
    }
    @Override
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    //
    
    private List<Observer> Observers = new ArrayList<>();
    
    public SimpleTask(){
    }
    
    public SimpleTask(String title, String description, String status, String deadline){
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }
    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Integer getProjectId(){
        return projectId;
    }

    public void setProjectId(Integer projectId){
        this.projectId = projectId != null ? projectId : 0;
    }

    public ProjectTask getParentProject(){
        return parentProject;
    }

    public void setParentProject(ProjectTask parentProject){
        this.parentProject = parentProject;
        this.projectId = (parentProject != null) ? parentProject.getId() : 0;
    }

    public void clearParentProject(){
        this.parentProject = null;
        this.projectId = 0;
    }

    //COMMAND + PROTOTYPE
        @Override
        public Task Clone() {
            return new SimpleTask(this.title, this.description, this.status, this.deadline);
        }
        //COMMAND
        @Override
        public void Save(String[] information) {
            System.out.println("Saving Current Task");
            this.title = information[0];
            this.description = information[1];
            this.status = information[2];
            this.deadline = information[3];
            
            testDisplay();
        }
        @Override
        public void Delete() {
            System.out.println("Deleted Current Task");
            sidebarFactory.listOfTasks.remove(this);
        }
        @Override
        public void New() {
    //        return new SimpleTask("Unnamed"," Description : ");
            this.title = "Unnamed Task";
            this.description = " Description : ";
            this.status = " Status : Unknown";
            this.deadline = " Deadline : ";
        }
        public void testDisplay(){
            System.out.println(title);
            System.out.println(description);
            System.out.println(status);
            System.out.println(deadline);
        }
        

    //OBSERVER
    
//    @Override
//    public void addObserver(Observer observer) {
//        Observers.add(observer);
//    }
//
//    @Override
//    public void removeObserver(Observer observer) {
//        Observers.remove(observer);
//    }
//
//    @Override
//    public void notifyObservers() {
//        for(Observer observer: Observers){
//            observer.update(status);
//        }
//    }
    
}
