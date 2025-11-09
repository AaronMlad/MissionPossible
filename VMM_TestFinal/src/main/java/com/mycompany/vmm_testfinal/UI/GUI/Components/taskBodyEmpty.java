/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.GUI.Components;

import com.mycompany.vmm_testfinal.Patterns.Interfaces.Task;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author racme
 */
public class taskBodyEmpty extends JPanel{
    public static JPanel createBody(int width, int height){
        JPanel currentPanel = new JPanel();
        currentPanel.setBackground(Color.DARK_GRAY);
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.Y_AXIS));
        currentPanel.setBorder(new EmptyBorder(50, 100, 100, 100));
//        currentPanel.setOpaque(false);
//        
        JLabel titleLabel = new JLabel();
        titleLabel.setText("<html>Welcome to VMM </html>");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 35));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        JLabel descLabel = new JLabel();
        descLabel.setText("<html>Select a task from the sidebar or create a new one to get started");
        descLabel.setForeground(Color.LIGHT_GRAY);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        currentPanel.add(titleLabel);
        currentPanel.add(descLabel);
        return currentPanel;
    }
}
