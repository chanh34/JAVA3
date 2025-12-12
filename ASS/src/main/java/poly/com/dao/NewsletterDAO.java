package poly.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poly.com.entity.Newsletter;
import poly.com.util.JDBCHelper;

public class NewsletterDAO {

    /**
     * Thêm một email đăng ký nhận tin
     * @param entity Đối tượng Newsletter
     */
    public void insert(Newsletter entity) {
        String sql = "INSERT INTO Newsletters (Email, Enabled, SubscribedDate) VALUES (?, ?, GETDATE())";
        JDBCHelper.executeUpdate(sql, entity.getEmail(), entity.isEnabled());
    }

    /**
     * Cập nhật trạng thái email (Enabled)
     * @param entity Đối tượng Newsletter
     */
    public void update(Newsletter entity) {
        String sql = "UPDATE Newsletters SET Enabled = ? WHERE Email = ?";
        JDBCHelper.executeUpdate(sql, entity.isEnabled(), entity.getEmail());
    }

    /**
     * Xóa một email
     * @param email Email cần xóa
     */
    public void delete(String email) {
        String sql = "DELETE FROM Newsletters WHERE Email = ?";
        JDBCHelper.executeUpdate(sql, email);
    }

    /**
     * Lấy tất cả các email đã đăng ký
     * @return Danh sách Newsletter
     */
    public List<Newsletter> findAll() {
        String sql = "SELECT * FROM Newsletters";
        return selectBySql(sql);
    }

    /**
     * Tìm email
     * @param email Email cần tìm
     * @return Đối tượng Newsletter hoặc null
     */
    public Newsletter findById(String email) {
        String sql = "SELECT * FROM Newsletters WHERE Email = ?";
        List<Newsletter> list = selectBySql(sql, email);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Phương thức nội bộ để ánh xạ dữ liệu
     */
    private List<Newsletter> selectBySql(String sql, Object... args) {
        List<Newsletter> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = JDBCHelper.getPreparedStatement(sql, args);
            conn = pstmt.getConnection();
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Newsletter entity = new Newsletter();
                entity.setEmail(rs.getString("Email"));
                entity.setEnabled(rs.getBoolean("Enabled"));
                entity.setSubscribedDate(rs.getTimestamp("SubscribedDate"));
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