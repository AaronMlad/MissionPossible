/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.UI.GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author racme
 */
public class buttonFactory extends JPanel{
    public JPanel createButton(String name){
        JPanel button = new JPanel();
        JLabel button_text = new JLabel();
        
        button.setLayout(new BorderLayout(0,0));
        button.setBackground(Color.BLACK); //COLOR
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
        
        button_text.setHorizontalAlignment(SwingConstants.CENTER);
        button_text.setText(name);
        button_text.setForeground(Color.WHITE); //COLOR
        button_text.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        button.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                button.setBackground(Color.DARK_GRAY); //COLOR
                button.repaint();
            };
            @Override
            public void mouseExited(MouseEvent e){
                button.setBackground(Color.BLACK);  //COLOR
                button.repaint();
            }
            @Override
            public void mouseClicked(MouseEvent e){
                button.setBackground(Color.DARK_GRAY); //COLOR
            }
        });
        button.add(button_text);
        return button;
    }
}
