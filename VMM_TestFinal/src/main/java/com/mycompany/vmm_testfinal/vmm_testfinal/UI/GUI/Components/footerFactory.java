///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.vmm_landingpage.gui.components;
//
//import com.mycompany.vmm_landingpage.gui.utils.ImageLoader;
//import com.mycompany.vmm_landingpage.gui.utils.ThemeManager;
//import java.awt.Cursor;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Image;
//import java.awt.Insets;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
///**
// *
// * @author lenovo
// */
//public class footerFactory {
//    public static JPanel createFooter(int height){
//
//        ImageIcon twitterIcon = new ImageIcon(ImageLoader.getInstance().getTwitterIcon().getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//        ImageIcon facebookIcon = new ImageIcon(ImageLoader.getInstance().getFacebookIcon().getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//        ImageIcon instagramIcon = new ImageIcon(ImageLoader.getInstance().getInstagramIcon().getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
//         // FOOTER
//        
//        JPanel Footer = new JPanel(new GridBagLayout());
//        Footer.setPreferredSize(new Dimension(Integer.MAX_VALUE, height));
//        Footer.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        Footer.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, ThemeManager.getInstance().getComponentColor()));
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 20, 10, 20);
//        gbc.gridy = 0;
//
//        //FOOTER: COMPANY LABEL
//        
//        JLabel companyLabel = new JLabel("© 2025 VMM Corporation");
//        companyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        companyLabel.setForeground(ThemeManager.getInstance().getForegroundColor());
//        gbc.gridx = 0;
//        gbc.anchor = GridBagConstraints.WEST;
//        Footer.add(companyLabel, gbc);
//        
//        // FOOTER: Social media icons
//        JPanel socialPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
//        socialPanel.setBackground(ThemeManager.getInstance().getBackgroundColor());
//        
//        socialPanel.add(new JLabel(twitterIcon));
//        socialPanel.add(new JLabel(facebookIcon));
//        socialPanel.add(new JLabel(instagramIcon));
//
//        gbc.gridx = 1;
//        gbc.anchor = GridBagConstraints.CENTER;
//        Footer.add(socialPanel, gbc);
//
//        // FOOTER : CONTACT LINKS
//        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
//        linkPanel.setBackground(ThemeManager.getInstance().getBackgroundColor());
//
//        JLabel contact = new JLabel("Contact Us");
//        contact.setForeground(ThemeManager.getInstance().getForegroundColor()); //CHANGE THIS LATER
//        contact.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        contact.setFont(new Font("SansSerif", Font.PLAIN, 14));
//
//        JLabel privacy = new JLabel("Privacy Policy");
//        privacy.setForeground(ThemeManager.getInstance().getForegroundColor()); //CHANGE THIS LATER
//        privacy.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        privacy.setFont(new Font("SansSerif", Font.PLAIN, 14));
//
//        linkPanel.add(contact);
//        linkPanel.add(privacy);
//
//        gbc.gridx = 2;
//        gbc.anchor = GridBagConstraints.EAST;
//        Footer.add(linkPanel, gbc);
//        
//        return Footer;
//    }
//}
