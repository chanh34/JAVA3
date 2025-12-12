package poly.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

public class Jdbc {
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=lab6_java3_csdl;encrypt=false";
    static String user = "sa2"; 
    static String pass = "Password.1"; 
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            // Ném RuntimeException nếu không tìm thấy Driver
            throw new RuntimeException("Không tìm thấy SQL Server JDBC Driver!", e);
        }
    }
    public static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            Connection con = openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeUpdate(String sql, Object... args) {
        try (
            Connection con = openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
        ) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
