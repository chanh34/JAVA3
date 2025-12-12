package poly.com.controller;

import java.io.IOException;

// SỬA LỖI: Dùng jakarta.servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminController
 * Hiển thị trang dashboard mặc định của admin
 */
@WebServlet("/admin/dashboard")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // (AuthFilter đã kiểm tra đăng nhập)
        
        // Đặt tiêu đề
        request.setAttribute("pageTitle", "Tổng quan");
        // Chỉ định file nội dung
        request.setAttribute("view", "/views/admin/dashboard.jsp");
        
        // SỬA LỖI: Forward sang "admin_layout.jsp" (tên file mới)
        request.getRequestDispatcher("/views/admin/admin_layout.jsp").forward(request, response);
    }
}