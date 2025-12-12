package poly.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
	protected static Connection conn;

    public Connect() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;"
                       + "databaseName=lab6_java3_csdl;"
                       + "encrypt=true;"
                       + "trustServerCertificate=true";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa2", "Password.1");

            System.out.println(" Kết nối thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void insertDeparments(String id, String name,String description) {
    String sql = "INSERT INTO Departments (id, Name, Description) VALUES (?, ?, ?)";
    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, name);
        ps.setString(3, description);

        ps.executeUpdate();
        System.out.println("Thêm phòng ban thành công!");
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    
}
    
    public void deleteDepartment(String id) {
        String sql = "DELETE FROM Departments WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int row = ps.executeUpdate();

            if (row > 0)
                System.out.println(" Xoá phòng ban thành công!");
            else
                System.out.println(" Không tìm thấy phòng ban để xoá!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateDepartment(String id, String name, String description) {
        String sql = "UPDATE Departments SET Name = ?, Description = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, id);

            int row = ps.executeUpdate();
            if (row > 0)
                System.out.println("Cập nhật phòng ban thành công!");
            else
                System.out.println(" Không tìm thấy phòng ban để cập nhật!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void printAllDepartments() {
        String sql = "SELECT * FROM Departments";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.isBeforeFirst()) {
                System.out.println("❌ Không có phòng ban nào!");
                return;
            }

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("Name");
                String description = rs.getString("Description");

                System.out.println(
                    "ID: " + id +
                    ", Name: " + name +
                    ", Description: " + description
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
