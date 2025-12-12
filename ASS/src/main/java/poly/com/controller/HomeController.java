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
 * Controller xử lý trang chủ - hiển thị tin tức trang nhất và các widget sidebar
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
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
     * Hiển thị trang chủ với danh sách tin trang nhất và các widget sidebar
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            List<Category> listCategories = categoryDAO.findAll();
            List<News> listTop5HotNews = newsDAO.findTop5HotNews();
            List<News> listTop5NewestNews = newsDAO.findTop5Newest();
            List<News> listHomeNews = newsDAO.findHomeNews();
            
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<String> viewedNewsIds = (List<String>) session.getAttribute("viewedNewsIds");
            List<News> listViewedNews = getViewedNews(viewedNewsIds);

            request.setAttribute("categories", listCategories);
            request.setAttribute("top5HotNews", listTop5HotNews);
            request.setAttribute("top5NewestNews", listTop5NewestNews);
            request.setAttribute("viewedNews", listViewedNews);
            request.setAttribute("homeNews", listHomeNews);
            request.setAttribute("pageTitle", "Trang chủ");
            request.setAttribute("view", "/views/public/home-content.jsp");
            
            request.getRequestDispatcher("/views/public/layout.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp"); 
        }
    }

    /**
     * Xử lý POST request - chuyển về doGet
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
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