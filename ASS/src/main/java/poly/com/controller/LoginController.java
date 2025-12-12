package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.UserDAO;
import poly.com.entity.User;

/**
 * Controller xử lý đăng nhập - hiển thị form và xác thực thông tin đăng nhập
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private UserDAO userDAO;

    /**
     * Khởi tạo UserDAO khi servlet được load
     */
    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    /**
     * Hiển thị trang đăng nhập
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/public/login.jsp").forward(request, response);
    }

    /**
     * Xử lý thông tin đăng nhập từ form
     * Nếu đăng nhập thành công, lưu user vào session và chuyển đến dashboard
     * Nếu thất bại, hiển thị thông báo lỗi
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDAO.findByEmailAndPassword(email, password);
            
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                request.setAttribute("error", "Email hoặc mật khẩu không chính xác!");
                request.getRequestDispatcher("/views/public/login.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã có lỗi hệ thống xảy ra!");
            request.getRequestDispatcher("/views/public/login.jsp").forward(request, response);
        }
    }
}