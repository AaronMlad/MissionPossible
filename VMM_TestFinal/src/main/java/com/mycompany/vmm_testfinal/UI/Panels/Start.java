///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.vmm_testfinal.UI.Panels;
//
//import com.mycompany.vmm_landingpage.gui.components.footerFactory;
//import com.mycompany.vmm_landingpage.gui.components.headerFactory;
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Color;
//import javax.swing.JPanel;
//
///**
// *
// * @author lenovo
// */
//public class Start extends JPanel{
//    private CardLayout cardLayout;
//    private JPanel mainPanel;
//
//    public Start(){
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//        
//        this.setBackground(Color.YELLOW); //REMOVE THIS LATER
//        
//        initializePanel();
//    }
//
//    private void initializePanel(){
//        setLayout(new BorderLayout());
//        
//        JPanel footer = footerFactory.createFooter(100);
//        JPanel header = headerFactory.createHeader(100, this::handleSidebarEvent);
//        
//        mainPanel.add(new Landing(),"Landing");
//        mainPanel.add(new AboutUs(),"AboutUs");
//        mainPanel.add(new Blog(),"Blog");
//        
//        handleSidebarEvent("Landing");
//        
//        this.add(mainPanel,BorderLayout.CENTER);
//        
//        add(footer, BorderLayout.SOUTH);
//        add(header, BorderLayout.NORTH);
//    }
//    
//    private void handleSidebarEvent(String text){
//        System.out.println("Clicked "+text);
//        switch(text){
//            case "Landing":
//                cardLayout.show(mainPanel,"Landing");
//                break;
//            case "AboutUs":
//                cardLayout.show(mainPanel,"AboutUs");
//                break;
//            case "Blog":
//                cardLayout.show(mainPanel,"Blog");
//                break;
//        }
//    }
//}
