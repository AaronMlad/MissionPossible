/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.GUI.Components;

import com.mycompany.vmm_testfinal.Patterns.Command.CloneTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.CommandControl;
import com.mycompany.vmm_testfinal.Patterns.Command.NewTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.UpdateSidebarCommand;
import com.mycompany.vmm_testfinal.Patterns.Factory.TaskFactory;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.Patterns.Prototype.TaskClient;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author racme
 */
public class sidebarFactory {
    
    public static Vector<Task> listOfTasks = new Vector<>();
    
    public static JPanel createSidebar(int width){
        //INITIALIZE
        CommandControl taskControl = new CommandControl();
        TaskClient client;
        CloneTaskCommand cloneTaskCommand;
        
        taskPanelFactory tpf = new taskPanelFactory();
        
        JPanel sideScrollBar = new JPanel();
        JScrollPane sideScrollPane = new JScrollPane(sideScrollBar, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        
        UpdateSidebarCommand udc = new UpdateSidebarCommand(sideScrollBar,sideScrollPane);
        //SIDEBAR
        JPanel Sidebar = new JPanel();
        Sidebar.setPreferredSize(new Dimension(width,Integer.MAX_VALUE));
        Sidebar.setBackground(Color.DARK_GRAY); //--------------COLOR
        Sidebar.setLayout(new BorderLayout());
        
            JPanel Sidebar_Lip = new JPanel();
            Sidebar_Lip.setPreferredSize(new Dimension(Integer.MAX_VALUE,40));
            Sidebar_Lip.setBackground(Color.BLACK); //--------------CHANGE
            Sidebar_Lip.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
            
                JLabel NewSimpleTask_Button = new JLabel();
                //<editor-fold defaultstate="collapsed" desc="NEW SIMPLE TASK">
                NewSimpleTask_Button.setPreferredSize(new Dimension(30,30));
                NewSimpleTask_Button.setBackground(Color.GREEN);
                //NewSimpleTask_Button.setIcon(simpletask);

                NewSimpleTask_Button.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        NewSimpleTask_Button.setBackground(Color.WHITE); //--------------COLOR
                        NewSimpleTask_Button.setOpaque(true);
                        NewSimpleTask_Button.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        NewSimpleTask_Button.setOpaque(false);
                        NewSimpleTask_Button.repaint();
                    }
                    @Override
                    public void mouseClicked(MouseEvent e){
                        Task emptySimpleTask = TaskFactory.newTask("SIMPLE");

                        NewTaskCommand newTaskCommand = new NewTaskCommand(emptySimpleTask);
                        taskControl.setCommand(newTaskCommand);
                        taskControl.clickedButton();

//                        emptySimpleTask.setTitle(emptySimpleTask.getTitle()+" "+task_id);
                        
                        System.out.println(emptySimpleTask.getTitle());
                        System.out.println(emptySimpleTask.getDescription());

                        listOfTasks.add(emptySimpleTask);
                        
//                        task_id++;
//                        System.out.println(task_id);
                        
                        JPanel emptySimpleTask_panel = tpf.createNewTaskPanel(emptySimpleTask,"SIMPLE",width);

                        udc.addToSidebar(emptySimpleTask_panel);
                        taskControl.setCommand(udc);
                        taskControl.clickedButton();
                        
//                        sideScrollBar.add(emptySimpleTask_panel);
//                        
//                        sideScrollPane.revalidate();
//                        sideScrollPane.repaint();
//                        sideScrollBar.revalidate();
//                        sideScrollBar.repaint();
                    }
                });
                //</editor-fold>
                
                JLabel newProjectTask_Button = new JLabel();
                newProjectTask_Button.setPreferredSize(new Dimension(30,30));
//                newProjectTask_Button.setIcon(projecttask);
                newProjectTask_Button.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseEntered(MouseEvent e){
                        newProjectTask_Button.setBackground(Color.WHITE); //--------------CHANGE
                        newProjectTask_Button.setOpaque(true);
                        newProjectTask_Button.repaint();
                    };
                    @Override
                    public void mouseExited(MouseEvent e){
                        newProjectTask_Button.setOpaque(false);
                        newProjectTask_Button.repaint();
                    }
                    @Override
                    public void mouseClicked(MouseEvent e){
                        Task emptyProjectTask = TaskFactory.newTask("PROJECT");
                        
                        NewTaskCommand newTaskCommand = new NewTaskCommand(emptyProjectTask);
                        taskControl.setCommand(newTaskCommand);
                        taskControl.clickedButton();
                        
//                        emptyProjectTask.setTitle(emptyProjectTask.getTitle()+" "+task_id);
                        
                        System.out.println(emptyProjectTask.getTitle());
                        System.out.println(emptyProjectTask.getDescription());
                        
                        listOfTasks.add(emptyProjectTask);
                        
//                        task_id++;
//                        System.out.println(task_id);
                        
                        JPanel emptyProjectTask_panel = tpf.createNewTaskPanel(emptyProjectTask,"PROJECT",width);
                        
                        udc.addToSidebar(emptyProjectTask_panel);
                        taskControl.setCommand(udc);
                        taskControl.clickedButton();
                    }
                });
        //ADD EVERYTHING
        sideScrollBar.setPreferredSize(new Dimension(180,Integer.MAX_VALUE));
        sideScrollBar.setOpaque(false); //--------------CHANGE
        sideScrollBar.setLayout(new FlowLayout());
        
        sideScrollPane.setOpaque(false);
        sideScrollPane.getViewport().setOpaque(false);
        sideScrollPane.setBorder(null);
        
        
        Sidebar.add(sideScrollPane,BorderLayout.CENTER);
        Sidebar_Lip.add(NewSimpleTask_Button);
        Sidebar_Lip.add(newProjectTask_Button);
        Sidebar.add(Sidebar_Lip,BorderLayout.NORTH);
        //RETURN
        return Sidebar;
    }
}
