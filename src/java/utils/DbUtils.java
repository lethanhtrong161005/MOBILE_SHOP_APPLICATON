
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=MobileShop";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

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
