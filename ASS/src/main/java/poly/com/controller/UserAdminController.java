package poly.com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.UserDAO;
import poly.com.entity.User;

@WebServlet("/admin/users")
public class UserAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        
        // Cấu hình BeanUtils để xử lý Date (nếu form gửi chuỗi ngày tháng)
        DateConverter converter = new DateConverter(null);
        converter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(converter, Date.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Kiểm tra quyền Admin
        if (!checkAdminRole(request, response)) return;

        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "delete":
                        doDelete(request, response);
                        return;
                    case "edit":
                        showEditForm(request, response);
                        return;
                }
            }
            showList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!checkAdminRole(request, response)) return;

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "create":
                        doCreate(request, response);
                        break;
                    case "update":
                        doUpdate(request, response);
                        break;
                }
            }
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    private boolean checkAdminRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && user.isRole()) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        return false;
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = userDAO.findAll();
        request.setAttribute("userList", list);
        request.setAttribute("pageTitle", "Quản lý Người dùng");
        request.setAttribute("view", "/views/admin/user_crud.jsp");
        request.getRequestDispatcher("/views/admin/admin_layout.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userDAO.findById(id);
        request.setAttribute("userItem", user);
        showList(request, response);
    }

    // Sửa lỗi visibility: protected thay vì private để HttpServlet gọi được nếu cần (hoặc cứ để private nếu chỉ gọi nội bộ doGet)
    // Nhưng để an toàn và đúng chuẩn override nếu có, protected là tốt nhất. 
    // Tuy nhiên ở đây doDelete là hàm riêng ta tự viết, private cũng được, 
    // nhưng để tránh lỗi "reduce visibility" nếu lỡ trùng tên hàm cha, ta dùng protected.
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        // Không cho phép tự xóa chính mình
        User currentUser = (User) request.getSession().getAttribute("user");
        if (id != null && !id.equals(currentUser.getId())) {
            userDAO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        BeanUtils.populate(user, request.getParameterMap());
        
        // Xử lý checkbox Gender và Role
        user.setGender(request.getParameter("gender") != null && request.getParameter("gender").equals("true"));
        user.setRole(request.getParameter("role") != null && request.getParameter("role").equals("true"));
        
        userDAO.insert(user);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        User user = userDAO.findById(id);
        if (user != null) {
            BeanUtils.populate(user, request.getParameterMap());
            
            user.setGender(request.getParameter("gender") != null && request.getParameter("gender").equals("true"));
            user.setRole(request.getParameter("role") != null && request.getParameter("role").equals("true"));

            userDAO.update(user);
        }
    }
}