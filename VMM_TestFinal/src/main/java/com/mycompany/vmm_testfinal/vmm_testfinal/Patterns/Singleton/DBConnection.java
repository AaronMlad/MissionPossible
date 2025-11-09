/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vmm_testfinal.Patterns.Singleton;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection con;

    private final String url = "jdbc:mysql://localhost:3306/MissionPossible";
    private final String username = "root";
    private final String password = "stoic";

    private DBConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected.");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Database Driver not found.", ex);
        }
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null || instance.getCon().isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getCon() {
        return con;
    }
}
