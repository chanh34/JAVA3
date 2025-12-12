package poly.com.controller;

import java.io.IOException;

// BẮT BUỘC dùng jakarta
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.NewsletterDAO;
import poly.com.entity.Newsletter;

/**
 * Servlet implementation class NewsletterController
 * Xử lý form đăng ký nhận bản tin từ sidebar
 */
@WebServlet("/newsletter")
public class NewsletterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private NewsletterDAO newsletterDAO;

    @Override
    public void init() throws ServletException {
        newsletterDAO = new NewsletterDAO();
    }

    /**
     * Xử lý khi người dùng gửi form (method="post")
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Lấy email từ form
            request.setCharacterEncoding("UTF-8");
            String email = request.getParameter("email");

            if (email != null && !email.isEmpty()) {
                // Tạo đối tượng Newsletter
                // (Date sẽ được CSDL tự động gán bằng GETDATE() theo thiết kế của NewsletterDAO)
                Newsletter entity = new Newsletter(email, true, null); 
                
                // Lưu vào CSDL
                newsletterDAO.insert(entity);
                
                // (Tùy chọn) Có thể đặt thông báo thành công vào session
                // request.getSession().setAttribute("message", "Đăng ký nhận tin thành công!");
            }
            
            // Quay trở lại trang chủ (hoặc trang trước đó)
            // Lấy trang mà người dùng vừa gửi form
            String referer = request.getHeader("Referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer); // Quay lại trang vừa đứng
            } else {
                response.sendRedirect(request.getContextPath() + "/home"); // Mặc định về trang chủ
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi (ví dụ trùng email - PK violation), chuyển sang trang lỗi
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Nếu ai đó truy cập /newsletter bằng GET, cứ đưa họ về trang chủ
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}