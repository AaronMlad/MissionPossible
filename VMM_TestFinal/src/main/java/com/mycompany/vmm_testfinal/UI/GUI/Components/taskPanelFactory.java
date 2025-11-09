/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.GUI.Components;

import com.mycompany.vmm_testfinal.Patterns.Command.CloneTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.CommandControl;
import com.mycompany.vmm_testfinal.Patterns.Command.NewTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.SelectTaskCommand;
import com.mycompany.vmm_testfinal.Patterns.Command.UpdateSidebarCommand;
import static com.mycompany.vmm_testfinal.Patterns.Command.UpdateSidebarCommand.sideScrollPane;
import com.mycompany.vmm_testfinal.Patterns.Factory.TaskFactory;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Observer;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.Patterns.Prototype.TaskClient;
import com.mycompany.vmm_testfinal.Patterns.Tasks.ProjectTask;
import com.mycompany.vmm_testfinal.Patterns.Tasks.SimpleTask;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author racme
 */
public class taskPanelFactory extends JPanel{    
    public JPanel createNewTaskPanel(Task task, String type, int width){

        //INITIALIZE
        JLabel folder_name = new JLabel(); //MIDDLEMOST
        JPanel currentPanel = new JPanel();
        JLabel task_icon = new JLabel(); //LEFTMOST
        JLabel dropdownButton= new JLabel(); //RIGHTMOST
        
        JPanel bottomPanel = new JPanel(); // DROPDOWN
        
        JPanel taskSettings = new JPanel(); //DROPDOWN UP
        JPanel taskList = new JPanel(); //DROPDOWN DOWN

        
        CommandControl taskControl = new CommandControl();
        TaskClient client;
        CloneTaskCommand cloneTaskCommand;
        
        buttonFactory bf = new buttonFactory();
        UpdateSidebarCommand udc = new UpdateSidebarCommand();
                        
        //FOLDER  ////////////////////////////////////////////////////////////////////
        currentPanel.setLayout(new BorderLayout(0,0));
        currentPanel.setPreferredSize(new Dimension((int)(width*0.875),30));
        currentPanel.setBackground(Color.DARK_GRAY); //--------------CHANGE
        currentPanel.setName(task.getTitle());
        currentPanel.putClientProperty("task", task);
        currentPanel.putClientProperty("taskType", type);
        currentPanel.putClientProperty("panelWidth", width);
        
        bottomPanel.setLayout(new BorderLayout(0,0));
        
        taskList.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        taskList.setPreferredSize(new Dimension(0,0));
        
        ///
        taskSettings.setBackground(Color.BLACK);
        if(type == "PROJECT"){
            taskSettings.setLayout(new GridLayout(3,1));
            taskSettings.setPreferredSize(new Dimension(Integer.MAX_VALUE,90));
            currentPanel.putClientProperty("taskList", taskList);
            currentPanel.putClientProperty("bottomPanel", bottomPanel);
            currentPanel.putClientProperty("taskSettings", taskSettings);
        }else{
            taskSettings.setLayout(new GridLayout(1,1));
            taskSettings.setPreferredSize(new Dimension(Integer.MAX_VALUE,30));
        }
        
        JPanel Duplicate = bf.createButton("Duplicate");
        JPanel AddTask = bf.createButton("Add Task");
        JPanel AddProject = bf.createButton("Add Project");
        
        Duplicate.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        Duplicate.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                TaskClient client = new TaskClient(task);
                Task cloneTask = client.cloneTask();
                cloneTask.setTitle(cloneTask.getTitle()+" Copy");
//                listOfTasks.add(cloneTask);
                JPanel emptySimpleTask_panel = createNewTaskPanel(cloneTask,type,width);
                boolean isComponentOfFolder = false;
                for(int i = 0; i < sidebarFactory.listOfTasks.size() ; i++){
                    if(sidebarFactory.listOfTasks.get(i) instanceof ProjectTask&&((ProjectTask)sidebarFactory.listOfTasks.get(i)).checkComponent(task)){
                        System.out.println(sidebarFactory.listOfTasks.get(i));
                        isComponentOfFolder=true;
                        ((ProjectTask)sidebarFactory.listOfTasks.get(i)).addComponent(cloneTask);
                    }
                }
                if(isComponentOfFolder){
                    System.out.println("is in Folder");
                    udc.addInFolder(emptySimpleTask_panel);
                    taskControl.setCommand(udc);
                    taskControl.clickedButton();
                    System.out.println("AIST");
                    
                    JPanel tList = (JPanel)currentPanel.getParent();
                    JPanel bPanel = (JPanel)tList.getParent();
                    JPanel cPanel = (JPanel)bPanel.getParent();
                    System.out.println(tList.getPreferredSize().height);
                    
                    tList.setPreferredSize(new Dimension((int)(width*0.875),tList.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));
                    bPanel.setPreferredSize(new Dimension((int)(width*0.875),bPanel.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));
                    cPanel.setPreferredSize(new Dimension((int)(width*0.875),cPanel.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));

                    tList.add(emptySimpleTask_panel);
                    
                    BorderLayout layout = (BorderLayout) emptySimpleTask_panel.getLayout();
                    Component eastComponent = layout.getLayoutComponent(BorderLayout.EAST);
                    Component southComponent = layout.getLayoutComponent(BorderLayout.SOUTH);
//                      
                    eastComponent.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e){
                            int taskHeight = southComponent.getPreferredSize().height;
                            
                            System.out.println("WORKING"+taskHeight);
                            if(emptySimpleTask_panel.getPreferredSize().height>30){
                                System.out.println(">"+taskList.getPreferredSize().height+" INCREASE HEIGHT by");
                                cPanel.setPreferredSize(new Dimension((int)(width*0.875),cPanel.getPreferredSize().height+southComponent.getPreferredSize().height));
                                bPanel.setPreferredSize(new Dimension((int)(width*0.875),bPanel.getPreferredSize().height+southComponent.getPreferredSize().height));
                                tList.setPreferredSize(new Dimension((int)(width*0.875),tList.getPreferredSize().height+southComponent.getPreferredSize().height));
                            }else{
                                System.out.println(">"+taskList.getPreferredSize().height+" DECREASE HEIGHT by");
                                tList.setPreferredSize(new Dimension((int)(width*0.875),tList.getPreferredSize().height-30));
                                cPanel.setPreferredSize(new Dimension((int)(width*0.875),cPanel.getPreferredSize().height-30));
                                bPanel.setPreferredSize(new Dimension((int)(width*0.875),bPanel.getPreferredSize().height-30));
                            }
                            currentPanel.revalidate();
                            currentPanel.repaint(); 
                        }
                    });
                    
                    bottomPanel.revalidate();
                    bottomPanel.repaint();
                }else{
                    udc.addToSidebar(emptySimpleTask_panel);
                    taskControl.setCommand(udc);
                    taskControl.clickedButton();
                    System.out.println("ASD");
                }
                System.out.println("E");
            }
        });
        taskSettings.add(Duplicate);
        
        if(type == "PROJECT"){
            taskSettings.add(AddTask);
            taskSettings.add(AddProject);
            AddTask.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    Task emptySimpleTask = TaskFactory.newTask("SIMPLE");
                    //Task task = newTask(JOptionPane.showInputDialog("Enter Simple Task Name:"),"SIMPLE");
                    //currentlySelectedTask = task;

                    NewTaskCommand newTaskCommand = new NewTaskCommand(emptySimpleTask);
                    taskControl.setCommand(newTaskCommand);
                    taskControl.clickedButton();
                    
                    System.out.println(emptySimpleTask.getTitle());
                    System.out.println(emptySimpleTask.getDescription());
                    
                    ((ProjectTask)task).addComponent(emptySimpleTask);
                    
                    sidebarFactory.listOfTasks.add(emptySimpleTask);
                    
                    JPanel emptySimpleTask_panel = createNewTaskPanel(emptySimpleTask,"SIMPLE",width);
                    
                    taskList.add(emptySimpleTask_panel);
                    taskList.setPreferredSize(new Dimension((int)(width*0.875),taskList.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));
                    currentPanel.setPreferredSize(new Dimension((int)(width*0.875),currentPanel.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));
                    bottomPanel.setPreferredSize(new Dimension((int)(width*0.875),bottomPanel.getPreferredSize().height+emptySimpleTask_panel.getPreferredSize().height));
                    
                    BorderLayout layout = (BorderLayout) emptySimpleTask_panel.getLayout();
                    Component eastComponent = layout.getLayoutComponent(BorderLayout.EAST);
                    Component southComponent = layout.getLayoutComponent(BorderLayout.SOUTH);
//                      
                    eastComponent.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e){
                            int taskHeight = southComponent.getPreferredSize().height;
                            
                            System.out.println("WORKING"+taskHeight);
                            if(emptySimpleTask_panel.getPreferredSize().height>30){
                                System.out.println(">"+taskList.getPreferredSize().height+" INCREASE HEIGHT by");
                                currentPanel.setPreferredSize(new Dimension((int)(width*0.875),currentPanel.getPreferredSize().height+southComponent.getPreferredSize().height));
                                bottomPanel.setPreferredSize(new Dimension((int)(width*0.875),bottomPanel.getPreferredSize().height+southComponent.getPreferredSize().height));
                                taskList.setPreferredSize(new Dimension((int)(width*0.875),taskList.getPreferredSize().height+southComponent.getPreferredSize().height));
                            }else{
                                System.out.println(">"+taskList.getPreferredSize().height+" DECREASE HEIGHT by");
                                taskList.setPreferredSize(new Dimension((int)(width*0.875),taskList.getPreferredSize().height-30));
                                currentPanel.setPreferredSize(new Dimension((int)(width*0.875),currentPanel.getPreferredSize().height-30));
                                bottomPanel.setPreferredSize(new Dimension((int)(width*0.875),bottomPanel.getPreferredSize().height-30));
                            }
                            currentPanel.revalidate();
                            currentPanel.repaint(); 
                        }
                        });
                    
                    currentPanel.revalidate();
                    currentPanel.repaint(); 
                    }
            });
        }
        //
        task_icon.setPreferredSize(new Dimension(28,28));
        task_icon.setBackground(Color.DARK_GRAY); //--------------CHANGE
        task_icon.setOpaque(true);
        System.out.println(type);
        switch(type){
            case "SIMPLE":
//                task_icon.setIcon(unknown_simpletask);
                break;
            case "PROJECT":
//                task_icon.setIcon(unknown_projecttask);
                break;
            default:
//                task_icon.setIcon(unknown_simpletask);
                break;
        }
        task_icon.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        //
        dropdownButton.setPreferredSize(new Dimension(28,28));
        dropdownButton.setBackground(Color.DARK_GRAY); //--------------CHANGE
        dropdownButton.setOpaque(true);
//        dropdownButton.setIcon(dropdown);
        dropdownButton.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        
        dropdownButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                dropdownButton.setBackground(Color.LIGHT_GRAY); 
                dropdownButton.setOpaque(true);
                dropdownButton.repaint();
            };
            @Override
            public void mouseExited(MouseEvent e){
                dropdownButton.setOpaque(false);
                dropdownButton.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("EXTEND");
                
                BorderLayout layout = (BorderLayout) bottomPanel.getLayout();
                Component northComponent = layout.getLayoutComponent(BorderLayout.NORTH);
                
                if(northComponent==taskSettings){
                    currentPanel.setPreferredSize(new Dimension((int)(width*0.875),30));
                    bottomPanel.setPreferredSize(new Dimension((int)(width*0.875),0));
                    bottomPanel.remove(northComponent);
                }else{
                    currentPanel.setPreferredSize(new Dimension((int)(width*0.875),currentPanel.getPreferredSize().height    
                                                                                         +taskSettings.getPreferredSize().height
                                                                                         +taskList.getPreferredSize().height));
                    bottomPanel.setPreferredSize(new Dimension((int)(width*0.875),bottomPanel.getPreferredSize().height
                                                                                        +taskSettings.getPreferredSize().height
                                                                                        +taskList.getPreferredSize().height));
                    bottomPanel.add(taskSettings,BorderLayout.NORTH);
                }
                
                currentPanel.revalidate();
                currentPanel.repaint();
            }
        });
        
        bottomPanel.add(taskList, BorderLayout.SOUTH);
        
        currentPanel.add(bottomPanel,BorderLayout.SOUTH);
        currentPanel.add(dropdownButton,BorderLayout.EAST);
        currentPanel.add(task_icon,BorderLayout.WEST);
        //FOLDER LABEL //////////////////////////////////////////////////////////////
        
        folder_name.setText(task.getTitle());
        folder_name.setForeground(Color.WHITE);
        folder_name.setHorizontalAlignment(SwingConstants.CENTER);
        folder_name.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        
        folder_name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                folder_name.setBackground(Color.LIGHT_GRAY); 
                folder_name.setOpaque(true);
                folder_name.repaint();
            };
            @Override
            public void mouseExited(MouseEvent e){
                folder_name.setOpaque(false);
                folder_name.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                System.out.println("THIS ONE");
                
                SelectTaskCommand stc = new SelectTaskCommand(task,currentPanel);
                taskControl.setCommand(stc);
                taskControl.clickedButton();
                }
            });
        
        currentPanel.add(folder_name, BorderLayout.CENTER);
        
        return(currentPanel);
    }    

    public JPanel attachExistingTaskToProjectPanel(ProjectTask projectTask, JPanel projectPanel, SimpleTask simpleTask, int width){
        if(projectPanel == null || simpleTask == null){
            return null;
        }
        JPanel taskList = (JPanel) projectPanel.getClientProperty("taskList");
        if(taskList == null){
            return null;
        }
        Integer storedWidth = (Integer) projectPanel.getClientProperty("panelWidth");
        int panelWidth = storedWidth != null ? storedWidth : width;
        JPanel simplePanel = createNewTaskPanel(simpleTask, "SIMPLE", panelWidth);
        taskList.add(simplePanel);

        JPanel bottomPanel = (JPanel) projectPanel.getClientProperty("bottomPanel");
        JPanel taskSettings = (JPanel) projectPanel.getClientProperty("taskSettings");
        int simpleHeight = simplePanel.getPreferredSize().height;
        int targetWidth = projectPanel.getPreferredSize().width > 0 ? projectPanel.getPreferredSize().width : (int)(panelWidth*0.875);

        Dimension listPref = taskList.getPreferredSize();
        taskList.setPreferredSize(new Dimension(targetWidth, listPref.height + simpleHeight));

        if(bottomPanel != null){
            if(taskSettings != null){
                BorderLayout layout = (BorderLayout) bottomPanel.getLayout();
                if(layout.getLayoutComponent(BorderLayout.NORTH) == null){
                    bottomPanel.add(taskSettings, BorderLayout.NORTH);
                }
            }
            Dimension bottomPref = bottomPanel.getPreferredSize();
            bottomPanel.setPreferredSize(new Dimension(targetWidth, bottomPref.height + simpleHeight));
            bottomPanel.revalidate();
            bottomPanel.repaint();
        }

        Dimension panelPref = projectPanel.getPreferredSize();
        projectPanel.setPreferredSize(new Dimension(targetWidth, panelPref.height + simpleHeight));
        taskList.revalidate();
        taskList.repaint();
        projectPanel.revalidate();
        projectPanel.repaint();

        return simplePanel;
    }
}
