package poly.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poly.com.entity.User;
import poly.com.util.JDBCHelper;

public class UserDAO {

    /**
     * Thêm một người dùng mới vào CSDL
     * @param entity Đối tượng User chứa thông tin người dùng
     */
    public void insert(User entity) {
        String sql = "INSERT INTO Users (Id, Password, Fullname, Birthday, Gender, Mobile, Email, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql, 
                entity.getId(), 
                entity.getPassword(),
                entity.getFullname(), 
                entity.getBirthday(), 
                entity.isGender(), 
                entity.getMobile(), 
                entity.getEmail(), 
                entity.isRole());
    }

    /**
     * Cập nhật thông tin người dùng trong CSDL
     * @param entity Đối tượng User chứa thông tin cần cập nhật
     */
    public void update(User entity) {
        String sql = "UPDATE Users SET Password = ?, Fullname = ?, Birthday = ?, Gender = ?, Mobile = ?, Email = ?, Role = ? WHERE Id = ?";
        JDBCHelper.executeUpdate(sql, 
                entity.getPassword(), 
                entity.getFullname(), 
                entity.getBirthday(), 
                entity.isGender(), 
                entity.getMobile(), 
                entity.getEmail(), 
                entity.isRole(), 
                entity.getId());
    }

    /**
     * Xóa một người dùng khỏi CSDL
     * @param id Mã người dùng cần xóa
     */
    public void delete(String id) {
        String sql = "DELETE FROM Users WHERE Id = ?";
        JDBCHelper.executeUpdate(sql, id);
    }

    /**
     * Lấy tất cả người dùng từ CSDL
     * @return Danh sách (List) các đối tượng User
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return selectBySql(sql);
    }

    /**
     * Tìm một người dùng theo mã
     * @param id Mã người dùng cần tìm
     * @return Đối tượng User tìm thấy, hoặc null nếu không tìm thấy
     */
    public User findById(String id) {
        String sql = "SELECT * FROM Users WHERE Id = ?";
        List<User> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }
    
    /**
     * Tìm một người dùng theo email và mật khẩu (dùng cho đăng nhập)
     * @param email Email đăng nhập
     * @param password Mật khẩu đăng nhập
     * @return Đối tượng User tìm thấy, hoặc null nếu không tìm thấy
     */
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        List<User> list = selectBySql(sql, email, password);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Phương thức nội bộ để thực thi câu lệnh SELECT và ánh xạ kết quả
     * @param sql Câu lệnh SQL
     * @param args Các tham số cho câu lệnh (nếu có)
     * @return Danh sách các đối tượng User
     */
    private List<User> selectBySql(String sql, Object... args) {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = JDBCHelper.getPreparedStatement(sql, args);
            conn = pstmt.getConnection();
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                User entity = new User();
                entity.setId(rs.getString("Id"));
                entity.setPassword(rs.getString("Password"));
                entity.setFullname(rs.getString("Fullname"));
                entity.setBirthday(rs.getDate("Birthday"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setMobile(rs.getString("Mobile"));
                entity.setEmail(rs.getString("Email"));
                entity.setRole(rs.getBoolean("Role"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi truy vấn dữ liệu", e);
        } finally {
            JDBCHelper.close(rs, pstmt, conn);
        }
        return list;
    }
}