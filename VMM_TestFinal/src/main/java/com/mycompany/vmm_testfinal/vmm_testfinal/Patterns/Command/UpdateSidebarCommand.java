/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Command;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Command;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author racme
 */
public class UpdateSidebarCommand implements Command{
    public static JPanel sideScrollBar;
    public static JScrollPane sideScrollPane;
    
    
    public static boolean inFolder = false;
    public static int increase = 0;
    public JPanel taskPanel;
    public JPanel parentPanel;
    
    public UpdateSidebarCommand(JPanel sideScrollBar, JScrollPane sideScrollPane){
        this.sideScrollBar = sideScrollBar;
        this.sideScrollPane = sideScrollPane;
    }
    public UpdateSidebarCommand(){
    }
    
    public void addToSidebar(JPanel taskPanel){
        this.taskPanel = taskPanel;
        inFolder = false;
    }
//    public void removeFromSidebar(JPanel taskPanel){
//        Component[] components = sideScrollBar.getComponents();
//        for(Component component : components){
//            if(component instanceof JPanel&&(JPanel)component==taskPanel){
//                sideScrollBar.remove(taskPanel);
//            }
//        }
//        inFolder = false;
//    }
    
    public void addInFolder(JPanel taskPanel){
        this.taskPanel = taskPanel;
        inFolder = true;
    }
    
    
    @Override
    public void execute(){
        if(taskPanel!=null){
            System.out.println("E");
            increase++;
            System.out.println("F");
            if(inFolder){
                Component[] components = sideScrollBar.getComponents();
                for(Component component : components){
                    System.out.println(" "+component.getClass().getSimpleName());
                    
                }
                
            }else{
                sideScrollBar.add(taskPanel);
            }    
        }
        sideScrollPane.revalidate();
        sideScrollPane.repaint();
        sideScrollBar.revalidate();
        sideScrollBar.repaint();
    }
}
