/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Interfaces;

/**
 *
 * @author racme
 */
public interface Task {
    //COMMAND
    public String getTitle();
    public String getDescription();
    public String getStatus();
    public String getDeadline();
    public int getId();
    
    public void setTitle(String title);
    public void setDescription(String deadline);
    public void setStatus(String status);
    public void setDeadline(String deadline);
    public void setId(int id);
    
    //COMMAND AND PROTOTYPE
    public Task Clone();
    public void New();
    public void Save(String[] information);
    public void Delete();
    //OBSERVER
//    public void addObserver(Observer observer);
//    public void removeObserver(Observer observer);
//    public void notifyObservers();
}
