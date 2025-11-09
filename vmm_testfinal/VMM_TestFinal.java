/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vmm_testfinal;

import com.mycompany.vmm_testfinal.Patterns.Singleton.DBConnection;
import com.mycompany.vmm_testfinal.UI.Panels.TaskList;
import java.sql.*;


public class VMM_TestFinal {
    

    public static void main(String[] args) {
        System.out.println("VMM_TestFinal BEGIN");
        try {
            Connection con = DBConnection.getInstance().getCon();
            // Use 'con' for queries
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TaskList taskList = new TaskList();
    }
}
