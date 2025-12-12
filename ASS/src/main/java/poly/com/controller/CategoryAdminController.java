package poly.com.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.CategoryDAO;
import poly.com.entity.Category;
import poly.com.entity.User;

/**
 * Controller quản lý loại tin (Category) - chỉ dành cho Admin
 * Xử lý CRUD: Create, Read, Update, Delete các loại tin
 */
@WebServlet("/admin/categories")
public class CategoryAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private CategoryDAO categoryDAO;

    /**
     * Khởi tạo CategoryDAO khi servlet được load
     */
    @Override
    public void init() throws ServletException {
        categoryDAO = new CategoryDAO();
    }

    /**
     * Xử lý request GET: Hiển thị danh sách loại tin hoặc form chỉnh sửa
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!checkAdminRole(request, response)) return;
        
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "delete":
                        handleDelete(request, response);
                        return;
                    case "edit":
                        showEditForm(request, response);
                        return;
                }
            }
            showCategoryList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Xử lý request POST: Tạo mới hoặc cập nhật loại tin
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!checkAdminRole(request, response)) return;
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "create":
                        handleCreate(request, response);
                        break;
                    case "update":
                        handleUpdate(request, response);
                        break;
                }
            }
            response.sendRedirect(request.getContextPath() + "/admin/categories");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
    
    /**
     * Kiểm tra quyền Admin - chỉ Admin mới được truy cập
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return true nếu là Admin, false nếu không phải
     * @throws IOException nếu có lỗi khi redirect
     */
    private boolean checkAdminRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && user.isRole()) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        return false;
    }

    /**
     * Hiển thị danh sách tất cả loại tin
     */
    private void showCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoriesList = categoryDAO.findAll();
        request.setAttribute("categoriesList", categoriesList);
        request.setAttribute("pageTitle", "Quản lý Loại tin");
        request.setAttribute("view", "/views/admin/category_crud.jsp");
        request.getRequestDispatcher("/views/admin/admin_layout.jsp").forward(request, response);
    }
    
    /**
     * Hiển thị form chỉnh sửa loại tin
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category categoryItem = categoryDAO.findById(id);
        request.setAttribute("categoryItem", categoryItem);
        showCategoryList(request, response);
    }
    
    /**
     * Xóa một loại tin khỏi database
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id != null) categoryDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }
    
    /**
     * Tạo mới một loại tin
     */
    private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Category entity = new Category();
        BeanUtils.populate(entity, request.getParameterMap());
        categoryDAO.insert(entity);
    }

    /**
     * Cập nhật thông tin loại tin
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Category entity = categoryDAO.findById(id);
        if (entity != null) {
            BeanUtils.populate(entity, request.getParameterMap());
            categoryDAO.update(entity);
        }
    }
}