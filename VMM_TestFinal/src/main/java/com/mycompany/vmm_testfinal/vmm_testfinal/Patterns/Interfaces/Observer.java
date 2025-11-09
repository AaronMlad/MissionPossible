
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mycompany.vmm_testfinal.Patterns.Interfaces;

import javax.swing.JPanel;

/**
 *
 * @author racme
 */
public interface Observer {
    void update(String title,
                String description,
                String status,
                String deadline);
}
