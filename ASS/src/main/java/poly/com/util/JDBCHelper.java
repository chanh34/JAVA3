package poly.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper class để quản lý kết nối và thao tác với database
 */
public class JDBCHelper {
    
    private static final String DB_NAME = "ABCNews";
    private static final String DB_USER = "sa2";
    private static final String DB_PASS = "Password.1";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "1433";

    private static String connectionUrl;
    private static String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * Khởi tạo connection URL và nạp JDBC driver khi class được load
     */
    static {
        connectionUrl = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;encrypt=false;trustServerCertificate=true;",
                DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASS);
        
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver JDBC: " + driverClass);
            throw new RuntimeException("Lỗi nạp driver JDBC", e);
        }
    }

    /**
     * Mở kết nối đến CSDL
     * @return Đối tượng Connection đã kết nối
     * @throws SQLException nếu kết nối thất bại
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    /**
     * Tạo đối tượng PreparedStatement với các tham số đã được gán
     * @param sql Câu lệnh SQL có tham số (dấu ?)
     * @param args Danh sách các tham số
     * @return PreparedStatement đã được gán tham số
     * @throws SQLException nếu có lỗi khi tạo PreparedStatement
     */
    public static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement pstmt;
        
        if (sql.trim().toLowerCase().startsWith("insert")) {
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        } else {
            pstmt = conn.prepareStatement(sql);
        }

        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    /**
     * Thực thi câu lệnh UPDATE, INSERT, DELETE
     * @param sql Câu lệnh SQL có tham số (dấu ?)
     * @param args Danh sách các tham số
     * @return Số hàng bị ảnh hưởng
     */
    public static int executeUpdate(String sql, Object... args) {
        try (PreparedStatement pstmt = getPreparedStatement(sql, args)) {
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thực thi executeUpdate", e);
        }
    }

    /**
     * Thực thi câu lệnh SELECT và trả về ResultSet
     * Lưu ý: Connection và PreparedStatement cần được đóng bởi phương thức gọi sau khi xử lý xong ResultSet
     * @param sql Câu lệnh SQL có tham số (dấu ?)
     * @param args Danh sách các tham số
     * @return Đối tượng ResultSet
     */
    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pstmt = getPreparedStatement(sql, args);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thực thi executeQuery", e);
        }
    }
    
    /**
     * Đóng kết nối và các tài nguyên liên quan
     * @param rs ResultSet
     * @param stmt Statement (hoặc PreparedStatement)
     * @param conn Connection
     */
    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Đóng kết nối và các tài nguyên liên quan (khi không có ResultSet)
     * @param stmt Statement (hoặc PreparedStatement)
     * @param conn Connection
     */
    public static void close(PreparedStatement stmt, Connection conn) {
        close(null, stmt, conn);
    }
}