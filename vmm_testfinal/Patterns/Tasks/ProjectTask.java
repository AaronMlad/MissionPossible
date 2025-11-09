/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Tasks;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Observer;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.Patterns.Tasks.SimpleTask;
import com.mycompany.vmm_testfinal.UI.GUI.Components.sidebarFactory;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author racme
 */
public class ProjectTask implements Task{
    private int id;
    private String title;
    private String description;
    private String status;
    private String deadline;
    
    private List<Observer> Observers = new ArrayList<>();
    private ArrayList<Task> folder = new ArrayList<>();
    
    public ProjectTask(){
    }
    
    public ProjectTask(String title, String description, String status, String deadline){
        this.title = title;
        this.description = description;
        this.status = status;
        this.deadline = deadline;
    }
    //REQUIRED GETTERS
    @Override
    public String getTitle() {
        System.out.println("Folder: " + title);
        for(Task file: folder){
            System.out.println(file.getTitle());
        }
        return title;
    }
    @Override
    public String getDescription() {
        System.out.println("Folder: " + title);
        System.out.println("Folder: " + description);
        for(Task file: folder){
            System.out.println(file.getDescription());
        }
        return description;
    }
    public String getStatus() {
         System.out.println("Folder: " + title);
         System.out.println("Folder: " + status);
        for(Task file: folder){
            System.out.println(file.getStatus());
        }
        return status;
    }
    public String getDeadline() {
         System.out.println("Folder: " + title);
         System.out.println("Folder: " + deadline);
        for(Task file: folder){
            System.out.println(file.getDeadline());
        }
        return deadline;
    }
    //REQUIRED SETTERS
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
        syncChildProjectLinks();
    }
    //
    
    //COMMAND + PROTOTYPE
        @Override
        public Task Clone() {
            return new ProjectTask(this.title, this.description, this.status, this.deadline);
        }
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
            System.out.println("Deleted Current Project");
            sidebarFactory.listOfTasks.remove(this);
            }
        @Override
        public void New() {
    //        return new SimpleTask("Unnamed"," Description : ");
            this.title = "Unnamed Project";
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
    
    //COMPOSITION
    public void addComponent(Task file){
        folder.add(file);
        if(file instanceof SimpleTask){
            ((SimpleTask)file).setParentProject(this);
        }
    }

    public void removeComponent(Task file){
        folder.remove(file);
        if(file instanceof SimpleTask){
            ((SimpleTask)file).clearParentProject();
        }
    }
    public boolean checkComponent(Task file){
        if(folder.contains(file)) return true;
        else return false;
    }

    public List<Task> getComponents(){
        return Collections.unmodifiableList(folder);
    }

    private void syncChildProjectLinks(){
        for(Task file : folder){
            if(file instanceof SimpleTask){
                ((SimpleTask)file).setParentProject(this);
            }
        }
    }
    
    
    //OBSERVERS
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
