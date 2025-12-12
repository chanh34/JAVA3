package poly.com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import poly.com.dao.CategoryDAO;
import poly.com.dao.NewsDAO;
import poly.com.entity.Category;
import poly.com.entity.News;
import poly.com.entity.User;

@WebServlet("/admin/news")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class NewsAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private NewsDAO newsDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        newsDAO = new NewsDAO();
        categoryDAO = new CategoryDAO();
    }

    /**
     * Xử lý request GET: Hiển thị danh sách tin hoặc form chỉnh sửa
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

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
            showNewsList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Xử lý request POST: Tạo mới hoặc cập nhật tin tức
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

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
            response.sendRedirect(request.getContextPath() + "/admin/news");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Hiển thị danh sách tin tức với phân quyền: Admin thấy tất cả, Phóng viên chỉ thấy tin của mình
     */
    private void showNewsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        List<News> newsList;
        if (user.isRole()) {
            newsList = newsDAO.findAll();
        } else {
            newsList = newsDAO.findByAuthor(user.getId());
        }
        
        List<Category> categoriesList = categoryDAO.findAll();
        request.setAttribute("newsList", newsList);
        request.setAttribute("categoriesList", categoriesList);
        request.setAttribute("pageTitle", "Quản lý Tin tức");
        request.setAttribute("view", "/views/admin/news_crud.jsp");
        request.getRequestDispatcher("/views/admin/admin_layout.jsp").forward(request, response);
    }
    
    /**
     * Hiển thị form chỉnh sửa tin tức
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        News newsItem = newsDAO.findById(id);
        
        request.setAttribute("newsItem", newsItem);
        showNewsList(request, response);
    }

    /**
     * Xóa một tin tức, kiểm tra phân quyền: Phóng viên chỉ được xóa tin của mình
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        if (id != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            if (user != null && !user.isRole()) {
                News news = newsDAO.findById(id);
                if (news != null && !news.getAuthor().equals(user.getId())) {
                    response.sendRedirect(request.getContextPath() + "/admin/news");
                    return;
                }
            }
            
            newsDAO.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/admin/news");
    }

    /**
     * Tạo mới một tin tức
     */
    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        News entity = new News();
        User user = (User) request.getSession().getAttribute("user");
        
        BeanUtils.populate(entity, request.getParameterMap());
        
        String imageUrl = saveImage(request);
        if (imageUrl != null) {
            entity.setImage(imageUrl);
        }
        
        entity.setAuthor(user.getId());
        entity.setPostedDate(new Date());
        entity.setViewCount(0);
        entity.setHome(request.getParameter("home") != null); 
        
        newsDAO.insert(entity);
    }

    /**
     * Cập nhật thông tin tin tức, kiểm tra phân quyền: Phóng viên chỉ được sửa tin của mình
     */
    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            return;
        }
        
        News entity = newsDAO.findById(id);
        if (entity == null) {
            return;
        }
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && !user.isRole() && !entity.getAuthor().equals(user.getId())) {
            return;
        }
        
        BeanUtils.populate(entity, request.getParameterMap());
        
        String imageUrl = saveImage(request);
        if (imageUrl != null) {
            entity.setImage(imageUrl);
        } else {
            String existingImage = request.getParameter("image");
            if (existingImage != null && !existingImage.isEmpty()) {
                entity.setImage(existingImage);
            }
        }
        
        entity.setHome(request.getParameter("home") != null);
        
        newsDAO.update(entity);
    }

    /**
     * Lưu file hình ảnh được upload vào thư mục /uploads trên server
     * @param request HttpServletRequest chứa file upload
     * @return Đường dẫn URL của file đã lưu, hoặc null nếu không có file
     * @throws IOException nếu có lỗi khi ghi file
     * @throws ServletException nếu có lỗi khi xử lý request
     */
    private String saveImage(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("imageFile");
        
        if (filePart != null && filePart.getSize() > 0 
            && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isEmpty()) {
            
            String uploadDir = request.getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = uploadDir + File.separator + fileName;
            
            filePart.write(filePath);
            
            return request.getContextPath() + "/uploads/" + fileName;
        }
        return null;
    }

}