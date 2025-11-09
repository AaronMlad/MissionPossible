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
import com.mycompany.vmm_testfinal.Patterns.Singleton.DBConnection;
import com.mycompany.vmm_testfinal.Patterns.Tasks.ProjectTask;
import com.mycompany.vmm_testfinal.Patterns.Tasks.SimpleTask;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author racme
 */
public class sidebarFactory {
    
    public static Vector<Task> listOfTasks = new Vector<>();
    private static final String DESCRIPTION_PREFIX = " Description : ";
    private static final String STATUS_PREFIX = " Status : ";
    private static final String DEADLINE_PREFIX = " Deadline : ";
    
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

        loadExistingTasks(width, taskControl, tpf, udc);
        //RETURN
        return Sidebar;
    }

    private static void loadExistingTasks(int width, CommandControl taskControl, taskPanelFactory tpf, UpdateSidebarCommand udc) {
        listOfTasks.clear();

        Map<Integer, ProjectTask> projectTaskMap = new LinkedHashMap<>();
        Map<Integer, JPanel> projectPanelMap = new LinkedHashMap<>();

        try (Connection con = DBConnection.getInstance().getCon()) {
            String projectSql = "SELECT project_id, name, descr, deadline, status FROM projects ORDER BY project_id";
            try (PreparedStatement ps = con.prepareStatement(projectSql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ProjectTask projectTask = new ProjectTask();
                    int projectId = rs.getInt("project_id");
                    projectTask.setId(projectId);
                    projectTask.setTitle(defaultTitle(rs.getString("name"), "Unnamed Project"));
                    projectTask.setDescription(ensurePrefixed(DESCRIPTION_PREFIX, rs.getString("descr")));
                    projectTask.setStatus(formatStatus(rs.getBoolean("status")));
                    projectTask.setDeadline(formatDeadline(rs.getDate("deadline")));

                    JPanel projectPanel = tpf.createNewTaskPanel(projectTask, "PROJECT", width);

                    projectTaskMap.put(projectId, projectTask);
                    projectPanelMap.put(projectId, projectPanel);
                    listOfTasks.add(projectTask);

                    udc.addToSidebar(projectPanel);
                    taskControl.setCommand(udc);
                    taskControl.clickedButton();
                }
            }

            String todoSql = "SELECT id, project_id, title, descr, due_date, status FROM todos ORDER BY id";
            try (PreparedStatement ps = con.prepareStatement(todoSql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SimpleTask simpleTask = new SimpleTask();
                    int todoId = rs.getInt("id");
                    int projectId = rs.getInt("project_id");

                    simpleTask.setId(todoId);
                    simpleTask.setTitle(defaultTitle(rs.getString("title"), "Unnamed Task"));
                    simpleTask.setDescription(ensurePrefixed(DESCRIPTION_PREFIX, rs.getString("descr")));
                    simpleTask.setStatus(formatStatus(rs.getBoolean("status")));
                    simpleTask.setDeadline(formatDeadline(rs.getDate("due_date")));

                    if (projectId > 0 && projectTaskMap.containsKey(projectId)) {
                        ProjectTask parentProject = projectTaskMap.get(projectId);
                        parentProject.addComponent(simpleTask);
                        listOfTasks.add(simpleTask);

                        JPanel projectPanel = projectPanelMap.get(projectId);
                        tpf.attachExistingTaskToProjectPanel(parentProject, projectPanel, simpleTask, width);
                    } else {
                        simpleTask.setProjectId(0);
                        listOfTasks.add(simpleTask);

                        JPanel simplePanel = tpf.createNewTaskPanel(simpleTask, "SIMPLE", width);
                        udc.addToSidebar(simplePanel);
                        taskControl.setCommand(udc);
                        taskControl.clickedButton();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static String defaultTitle(String dbValue, String fallback) {
        return (dbValue == null || dbValue.trim().isEmpty()) ? fallback : dbValue.trim();
    }

    private static String formatStatus(boolean isDone) {
        return ensurePrefixed(STATUS_PREFIX, isDone ? "Completed" : "Ongoing");
    }

    private static String formatDeadline(Date dueDate) {
        if (dueDate == null) {
            return DEADLINE_PREFIX;
        }
        return ensurePrefixed(DEADLINE_PREFIX, dueDate.toLocalDate().toString());
    }

    private static String ensurePrefixed(String prefix, String value) {
        String cleaned = stripExistingPrefix(prefix, value);
        if (cleaned.isEmpty()) {
            return prefix;
        }
        return prefix + cleaned;
    }

    private static String stripExistingPrefix(String prefix, String value) {
        if (value == null) {
            return "";
        }
        String trimmed = value.trim();
        String normalizedPrefix = prefix.trim().toLowerCase();
        String normalizedValue = trimmed.toLowerCase();
        if (normalizedValue.startsWith(normalizedPrefix)) {
            return trimmed.substring(prefix.trim().length()).trim();
        }
        return trimmed;
    }

}
