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
 * Controller xử lý trang chi tiết tin tức - hiển thị nội dung đầy đủ và các tin liên quan
 */
@WebServlet("/detail")
public class DetailController extends HttpServlet {
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
     * Hiển thị chi tiết tin tức, tăng lượt xem và lưu vào danh sách đã xem
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            String newsId = request.getParameter("id");
            
            if (newsId == null || newsId.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }

            News newsDetail = newsDAO.findById(newsId);
            
            if (newsDetail == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            
            newsDAO.incrementViewCount(newsId);
            
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            List<String> viewedNewsIds = (List<String>) session.getAttribute("viewedNewsIds");
            if (viewedNewsIds == null) {
                viewedNewsIds = new ArrayList<>();
            }
            viewedNewsIds.remove(newsId);
            viewedNewsIds.add(0, newsId);
            if (viewedNewsIds.size() > 10) {
                viewedNewsIds = viewedNewsIds.subList(0, 10);
            }
            session.setAttribute("viewedNewsIds", viewedNewsIds);
            
            List<Category> listCategories = categoryDAO.findAll();
            List<News> listTop5HotNews = newsDAO.findTop5HotNews();
            List<News> listTop5NewestNews = newsDAO.findTop5Newest();
            List<News> listViewedNews = getViewedNews(viewedNewsIds);
            List<News> listRelatedNews = newsDAO.findRelatedNews(newsDetail.getCategoryId(), newsId);

            request.setAttribute("categories", listCategories);
            request.setAttribute("top5HotNews", listTop5HotNews);
            request.setAttribute("top5NewestNews", listTop5NewestNews);
            request.setAttribute("viewedNews", listViewedNews);
            request.setAttribute("news", newsDetail);
            request.setAttribute("relatedNews", listRelatedNews);
            request.setAttribute("pageTitle", newsDetail.getTitle());
            request.setAttribute("view", "/views/public/detail-content.jsp");
            
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