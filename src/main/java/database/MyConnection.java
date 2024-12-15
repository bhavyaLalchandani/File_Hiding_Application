package database;
import java.sql.*;
public class MyConnection {
    public static Connection conn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // USE YOUR DB_NAME (FileHider), USER_NAME and PASSWORD
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FileHider?useSSL=false", "root", "your_password");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connection with DB Established Successfully");
        return  conn;
    }

    public static void closeConnection() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyConnection.getConnection();
    }
}
