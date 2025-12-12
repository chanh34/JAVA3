package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.CategoryDAO;
import poly.com.dao.NewsDAO;
import poly.com.entity.Category;
import poly.com.entity.News;

/**
 * Controller xử lý trang danh sách tin theo loại tin (category)
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    private CategoryDAO categoryDAO;
    private NewsDAO newsDAO;

    /**
     * Khởi tạo các DAO khi servlet được load
     */
    @Override
    public void init() throws ServletException {
        categoryDAO = new CategoryDAO();
        newsDAO = new NewsDAO();
    }

    /**
     * Hiển thị danh sách tin tức theo loại tin được chọn
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String categoryId = request.getParameter("id");
            
            if (categoryId == null || categoryId.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            List<Category> listCategories = categoryDAO.findAll();
            List<News> listTop5HotNews = newsDAO.findTop5HotNews();
            List<News> listTop5NewestNews = newsDAO.findTop5Newest();
            
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<String> viewedNewsIds = (List<String>) session.getAttribute("viewedNewsIds");
            List<News> listViewedNews = getViewedNews(viewedNewsIds);
            
            Category category = categoryDAO.findById(categoryId);
            List<News> listNewsByCategory = newsDAO.findByCategoryId(categoryId);
            
            request.setAttribute("categories", listCategories);
            request.setAttribute("top5HotNews", listTop5HotNews);
            request.setAttribute("top5NewestNews", listTop5NewestNews);
            request.setAttribute("viewedNews", listViewedNews);
            request.setAttribute("categoryNews", listNewsByCategory);
            request.setAttribute("categoryName", (category != null) ? category.getName() : "Không tìm thấy");
            request.setAttribute("pageTitle", (category != null) ? category.getName() : "Danh sách tin");
            request.setAttribute("view", "/views/public/category-content.jsp");
            
            request.getRequestDispatcher("/views/public/layout.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
    
    /**
     * Lấy danh sách tin đã xem từ danh sách ID
     * @param viewedNewsIds List các ID tin đã xem (mới nhất ở đầu)
     * @return Danh sách News đã xem (theo thứ tự mới nhất trước)
     */
    private List<News> getViewedNews(List<String> viewedNewsIds) {
        List<News> listViewedNews = new ArrayList<>();
        if (viewedNewsIds != null && !viewedNewsIds.isEmpty()) {
            // Danh sách đã có thứ tự mới nhất ở đầu, chỉ cần lấy từ đầu
            for (String newsId : viewedNewsIds) {
                News news = newsDAO.findById(newsId);
                if (news != null) {
                    listViewedNews.add(news);
                }
            }
            // Giới hạn tối đa 5 tin để hiển thị trong sidebar
            if (listViewedNews.size() > 5) {
                listViewedNews = listViewedNews.subList(0, 5);
            }
        }
        return listViewedNews;
    }
}