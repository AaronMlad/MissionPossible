/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.Panels;

import com.mycompany.vmm_testfinal.Patterns.Command.UpdateBodyCommand;
import com.mycompany.vmm_testfinal.Patterns.Factory.TaskFactory;
import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import com.mycompany.vmm_testfinal.UI.GUI.Components.sidebarFactory;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskBodyEmpty;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskBodyFactory;
import com.mycompany.vmm_testfinal.UI.GUI.Components.taskPanelFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
/**
 *
 * @author racme
 */
public class TaskList extends JFrame{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth ;
    int screenHeight ;    
    int sideBarWidth = 220;
    
    JPanel Sidebar;
    JPanel Body_Card;
//    JPanel Body;
    
    public TaskList(){
        //<editor-fold defaultstate="collapsed" desc="INITIALIZE UI - REMOVE THIS LATER">
        //INITIALIZE UI ----------- REMOVE THIS LATER
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800,600));

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width * 3/4;
        screenHeight = screenSize.height * 2/3;

        setSize(screenWidth,screenHeight);
        //</editor-fold>
        
        this.setLayout(new BorderLayout());    
        
        
        //<editor-fold defaultstate="collapsed" desc="HEADER">
        JPanel Header = new JPanel();
        Header.setPreferredSize(new Dimension(Integer.MAX_VALUE,100));
        Header.setBackground(Color.RED); //--------------CHANGE
        Header.setLayout(new BorderLayout());
        //</editor-fold>
        
        taskPanelFactory tpf = new taskPanelFactory();
        
        Task task = TaskFactory.newTask("SIMPLE");
        
        Sidebar = sidebarFactory.createSidebar(220);
        
        CardLayout cardLayout = new CardLayout();
        Body_Card = new JPanel(cardLayout);
        
        JPanel noTasksAvailable_Body = taskBodyEmpty.createBody(screenWidth, screenHeight);
        JPanel currentTasks_Body = taskBodyFactory.createBody(task,screenWidth,screenHeight);
        
        Body_Card.add(currentTasks_Body, "INFO");
        Body_Card.add(noTasksAvailable_Body, "EMPTY");
        
        cardLayout.show(Body_Card, "EMPTY");

        UpdateBodyCommand ubc = new UpdateBodyCommand(Body_Card);
        
        this.add(Sidebar,BorderLayout.WEST);
        this.add(Header,BorderLayout.NORTH);
        this.add(Body_Card,BorderLayout.CENTER);
        
        this.setVisible(true);
    }
}
