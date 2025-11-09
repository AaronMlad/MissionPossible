///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.vmm_testfinal.UI.GUI;
//
//import com.mycompany.vmm_testfinal.UI.GUI.panels.Start;
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Toolkit;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
///**
// *
// * @author lenovo
// */
//public class CurrentFrame extends JFrame{
//    private static CardLayout cardLayout;
//    private static JPanel centralPanel;
//    
//    private Dimension screenSize;
//    private int screenWidth;
//    private int screenHeight;
//    
//    final Color VMM_Maroon = new Color(0xBB0000); //REMOVE THIS LATER
//    final Color VMM_Green = new Color(0x2E8B57); //REMOVE THIS LATER
//    final Color VMM_Beige = new Color(0xF5DEB3); //REMOVE THIS LATER
//    final Color VMM_Red = new Color(0xFF4500); //REMOVE THIS LATER
//    final Color VMM_Grey = new Color(0xD3D3D3); //REMOVE THIS LATER
//    
//    public CurrentFrame(){
//        initializeFrame();
//        setupUI();
//        //ThemeManager.getInstance().applyThemeToContainer(this);
//    }
//    
//    public void initializeFrame(){
//        this.setTitle("VMM_LandingPage");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setMinimumSize(new Dimension(800,600));
//        
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        
//        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        screenWidth = screenSize.width * 3/4;
//        screenHeight = screenSize.height * 2/3;
//        
//        setSize(screenWidth,screenHeight);
//        
//        //this.setResizable(false);
//        //this.getContentPane().setBackground(VMM_Grey);
//        //this.setLayout(new BorderLayout(0,0));
//    }
//    public void setupUI(){
//        cardLayout = new CardLayout();
//        centralPanel = new JPanel(cardLayout);
//        //DARKMODE TOGGLE
//        //centralPanel.add(new JPanel(borderLayout),"START");
//        //
//        //
//        
//        centralPanel.add(new Start());
//        
//        this.add(centralPanel,BorderLayout.CENTER);
//        this.showCard("Landing");
//        
//        //this.setContentPane(centralPanel);
//        //centralPanel.add(this)
//    }
//    public static void showCard(String text){
//        cardLayout.show(centralPanel, text);
//    }   
//}
