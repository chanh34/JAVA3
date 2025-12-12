package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 * Xử lý đăng xuất
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Lấy session hiện tại (không tạo mới nếu không có)
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // 2. Xóa session (xóa "user")
            session.invalidate();
        }
        
        // 3. Chuyển hướng về trang chủ
        response.sendRedirect(request.getContextPath() + "/home");
    }
}