package poly.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poly.com.entity.News;
import poly.com.util.JDBCHelper;

public class NewsDAO {

    /**
     * Thêm bản tin mới
     */
    public void insert(News entity) {
        String sql = "INSERT INTO News (Id, Title, Summary, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JDBCHelper.executeUpdate(sql,
                entity.getId(),
                entity.getTitle(),
                entity.getSummary(),
                entity.getContent(),
                entity.getImage(),
                entity.getPostedDate(),
                entity.getAuthor(),
                entity.getViewCount(),
                entity.getCategoryId(),
                entity.isHome());
    }

    /**
     * Cập nhật bản tin
     */
    public void update(News entity) {
        String sql = "UPDATE News SET Title = ?, Summary = ?, Content = ?, Image = ?, PostedDate = ?, Author = ?, ViewCount = ?, CategoryId = ?, Home = ? WHERE Id = ?";
        JDBCHelper.executeUpdate(sql,
                entity.getTitle(),
                entity.getSummary(),
                entity.getContent(),
                entity.getImage(),
                entity.getPostedDate(),
                entity.getAuthor(),
                entity.getViewCount(),
                entity.getCategoryId(),
                entity.isHome(),
                entity.getId());
    }

    /**
     * Xóa bản tin
     */
    public void delete(String id) {
        String sql = "DELETE FROM News WHERE Id = ?";
        JDBCHelper.executeUpdate(sql, id);
    }

    /**
     * Tăng lượt xem cho bản tin (Yêu cầu khi xem chi tiết)
     */
    public void incrementViewCount(String id) {
        String sql = "UPDATE News SET ViewCount = ViewCount + 1 WHERE Id = ?";
        JDBCHelper.executeUpdate(sql, id);
    }

    /**
     * Lấy tất cả bản tin
     */
    public List<News> findAll() {
        String sql = "SELECT * FROM News ORDER BY PostedDate DESC";
        return selectBySql(sql);
    }

    /**
     * Tìm bản tin theo ID
     */
    public News findById(String id) {
        String sql = "SELECT * FROM News WHERE Id = ?";
        List<News> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }
    
    /**
     * Lấy các bản tin theo Mã loại tin (CategoryId)
     */
    public List<News> findByCategoryId(String categoryId) {
        String sql = "SELECT * FROM News WHERE CategoryId = ? ORDER BY PostedDate DESC";
        return selectBySql(sql, categoryId);
    }
    
    /**
     * Lấy các bản tin được đánh dấu "Trang nhất" (Home = true)
     */
    public List<News> findHomeNews() {
        String sql = "SELECT * FROM News WHERE Home = 1 ORDER BY PostedDate DESC";
        return selectBySql(sql);
    }
    
    /**
     * Lấy 5 bản tin mới nhất (Yêu cầu Sidebar)
     */
    public List<News> findTop5Newest() {
        String sql = "SELECT TOP 5 * FROM News ORDER BY PostedDate DESC";
        return selectBySql(sql);
    }
    
    /**
     * Lấy 5 bản tin xem nhiều nhất (Yêu cầu Sidebar)
     */
    public List<News> findTop5HotNews() {
        String sql = "SELECT TOP 5 * FROM News ORDER BY ViewCount DESC";
        return selectBySql(sql);
    }

    /**
     * Lấy các bản tin cùng loại (trừ bản tin đang xem)
     */
    public List<News> findRelatedNews(String categoryId, String currentNewsId) {
        String sql = "SELECT TOP 5 * FROM News WHERE CategoryId = ? AND Id != ? ORDER BY PostedDate DESC";
        return selectBySql(sql, categoryId, currentNewsId);
    }
    
    /**
     * Lấy tất cả bản tin của một tác giả (Phóng viên)
     * @param authorId Mã tác giả
     * @return Danh sách tin tức của tác giả đó
     */
    public List<News> findByAuthor(String authorId) {
        String sql = "SELECT * FROM News WHERE Author = ? ORDER BY PostedDate DESC";
        return selectBySql(sql, authorId);
    }
    
    /**
     * Phương thức nội bộ để ánh xạ ResultSet sang đối tượng News
     */
    /**
     * Thực thi câu lệnh SELECT và ánh xạ kết quả sang đối tượng News
     * @param sql Câu lệnh SQL
     * @param args Các tham số cho câu lệnh
     * @return Danh sách các đối tượng News
     */
    private List<News> selectBySql(String sql, Object... args) {
        List<News> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = JDBCHelper.getPreparedStatement(sql, args);
            conn = pstmt.getConnection();
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                News entity = new News();
                entity.setId(rs.getString("Id"));
                entity.setTitle(rs.getString("Title"));
                entity.setSummary(rs.getString("Summary"));
                entity.setContent(rs.getString("Content"));
                entity.setImage(rs.getString("Image"));
                entity.setPostedDate(rs.getTimestamp("PostedDate"));
                entity.setAuthor(rs.getString("Author"));
                entity.setViewCount(rs.getInt("ViewCount"));
                entity.setCategoryId(rs.getString("CategoryId"));
                entity.setHome(rs.getBoolean("Home"));
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