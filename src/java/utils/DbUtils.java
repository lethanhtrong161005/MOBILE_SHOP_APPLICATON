/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class DbUtils {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=MobileShop";
    private static final String USER = "sa";
    private static final String PASSWORD = "Admin1234@!";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (Exception e) {
            System.out.println("Connection: " + e.getMessage());
        }
        return connection;
    }
    
}
