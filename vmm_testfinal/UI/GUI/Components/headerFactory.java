///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.vmm_landingpage.gui.components;
//
//import com.mycompany.vmm_landingpage.gui.utils.DarkModeToggle;
//import com.mycompany.vmm_landingpage.gui.utils.ImageLoader;
//import com.mycompany.vmm_landingpage.gui.utils.ThemeManager;
//import com.mycompany.vmm_landingpage.gui.utils.ThemedPanel;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.Image;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.function.Consumer;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
///**
// *
// * @author lenovo
// */
//public class headerFactory {
//    static ImageIcon logo = new ImageIcon(ImageLoader.getInstance().getLogo().getImage().getScaledInstance(180, 80, Image.SCALE_SMOOTH));
//    
//    public static JPanel createHeader(int height, Consumer<String> buttonClicked){
//        
//        // HEADER
//        
//        JPanel Header = new JPanel();
//        Header.setPreferredSize(new Dimension(Integer.MAX_VALUE,height));
//        Header.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        Header.setLayout(new BorderLayout());
//        Header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ThemeManager.getInstance().getComponentColor()));
//        
//        // HEADER: LOGO
//        
//        JPanel Header_Left = new JPanel();
//        Header_Left.setPreferredSize(new Dimension(230,Integer.MAX_VALUE));
//        Header_Left.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        
//        JLabel Header_Logo = new JLabel();
//        Header_Logo.setPreferredSize(new Dimension(200,90));
//        Header_Logo.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        Header_Logo.setHorizontalAlignment(JLabel.CENTER);
//        Header_Logo.setIcon(logo);
//        Header_Logo.setOpaque(true);
//        
//        Header_Left.add(Header_Logo);
//
//        // HEADER : CENTER BUTTONS
//        
//        JPanel Header_Center = new JPanel();
//        Header_Center.setPreferredSize(new Dimension(230,Integer.MAX_VALUE));
//        Header_Center.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        Header_Center.setLayout(new FlowLayout(FlowLayout.LEFT));
//        Header_Center.setBorder(BorderFactory.createEmptyBorder(-5,0,-5,-5));
//        
//        JLabel FAQ = createHeaderButtons("Landing", buttonClicked);
//        JLabel BLOG = createHeaderButtons("Blog", buttonClicked);
//        JLabel ABOUT_US = createHeaderButtons("AboutUs", buttonClicked);
//        JLabel LOGOUT = createHeaderButtons("Log Out", buttonClicked);
//        
//        Header_Center.add(FAQ);
//        Header_Center.add(BLOG);
//        Header_Center.add(ABOUT_US);
//        Header_Center.add(LOGOUT);
//        // HEADER: SIGN OUT
//        JPanel Header_Right = new JPanel();
//        Header_Right.setPreferredSize(new Dimension(100,Integer.MAX_VALUE));
//        Header_Right.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        Header_Right.setLayout(new BorderLayout());
//        Header_Right.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
//        
//        // HEADER: DARK MODE
//        JPanel DMPanel = new ThemedPanel();
//        DMPanel.add(new DarkModeToggle());
//        
//        Header_Right.add(DMPanel,BorderLayout.CENTER);
//        //
//        Header.add(Header_Left,BorderLayout.WEST);
//        Header.add(Header_Right,BorderLayout.EAST);
//        Header.add(Header_Center,BorderLayout.CENTER);
//        ///
//        
//        return Header;
//    }
//    
//    private static Font Roboto(int fontSize){
//        Font Roboto = new Font("Roboto",Font.BOLD,fontSize);
//        return Roboto; 
//    }
//    private static JLabel createHeaderButtons(String text, Consumer<String> buttonClicked){
//        JLabel label = new JLabel();
//        label.setPreferredSize(new Dimension(170,100));
//        label.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        label.setForeground(ThemeManager.getInstance().getForegroundColor());
//        label.setText(text);
//        label.setFont(Roboto(30));
//        //label.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
//        label.setVerticalAlignment(JLabel.CENTER);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setOpaque(true);
//        
//        label.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseEntered(MouseEvent e){
//                label.setBackground(ThemeManager.getInstance().getBackgroundSelectColor());
//                label.setForeground(ThemeManager.getInstance().getForegroundSelectColor());
//                label.repaint();
//            };
//            @Override
//            public void mouseExited(MouseEvent e){
//                label.setBackground(ThemeManager.getInstance().getBackgroundColor());
//                label.setForeground(ThemeManager.getInstance().getForegroundColor());
//                label.repaint();
//            }
//            @Override
//            public void mouseClicked(MouseEvent e){
//                buttonClicked.accept(text);
//            }
//        });
//        
//        return label;
//    }
//}
