package dao;
import database.MyConnection; // Our Package Database
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email) throws SQLException {
        Connection conn = MyConnection.getConnection();
        PreparedStatement p = conn.prepareStatement("Select email from users");
        ResultSet rs = p.executeQuery();
        while (rs.next()) {
            String e = rs.getString(1);
            if (e.equals(email))
                return true;
        }
        return false;
    }

    public static int saveUser(User user) throws SQLException {
        Connection conn = MyConnection.getConnection();
        PreparedStatement p = conn.prepareStatement("insert into users values(default, ?, ?)");
        p.setString(1, user.getName());
        p.setString(2, user.getEmail());
        return p.executeUpdate();
    }
}
