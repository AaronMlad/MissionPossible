/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskBodyEmpty;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskBodyFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;

/**
 *
 * @author racme
 */
public class UpdateBodyCommand implements Command{
    private static boolean selected = false;
    private static JPanel Body;
    private static Task task;
    
    public UpdateBodyCommand(){
    }
    
    public UpdateBodyCommand(JPanel body){
        this.Body=body;
    }
    
    public void newBody(Task task){
        this.task = task;
    }
    
    @Override
    public void execute() {
        System.out.println("UPDATED BODY");
        

        CardLayout cardLayout = (CardLayout)Body.getLayout();
        if(task!=null){
            System.out.println(task.getTitle()+" "+task.getDescription()+" "+task.getStatus()+" "+task.getDeadline());
            cardLayout.show(Body, "INFO");
            taskBodyFactory.setValues(task.getTitle(),
                                  task.getDescription(),
                                  task.getStatus(),
                                  task.getDeadline());
        }else{
            cardLayout.show(Body, "EMPTY");
        }
        Body.revalidate();
        Body.repaint();
    }
}
