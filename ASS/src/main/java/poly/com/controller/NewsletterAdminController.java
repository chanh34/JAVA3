package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.NewsletterDAO;
import poly.com.entity.Newsletter;
import poly.com.entity.User;

@WebServlet("/admin/newsletters")
public class NewsletterAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private NewsletterDAO newsletterDAO;

    @Override
    public void init() throws ServletException {
        newsletterDAO = new NewsletterDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!checkAdminRole(request, response)) return;

        String action = request.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "delete":
                        String email = request.getParameter("email");
                        if (email != null) newsletterDAO.delete(email);
                        break;
                    case "toggle":
                        String toggleEmail = request.getParameter("email");
                        Newsletter n = newsletterDAO.findById(toggleEmail);
                        if (n != null) {
                            n.setEnabled(!n.isEnabled());
                            newsletterDAO.update(n);
                        }
                        break;
                }
            }
            showList(request, response);
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
        List<Newsletter> list = newsletterDAO.findAll();
        request.setAttribute("newsletterList", list);
        request.setAttribute("pageTitle", "Quản lý Newsletter");
        request.setAttribute("view", "/views/admin/newsletter_crud.jsp");
        request.getRequestDispatcher("/views/admin/admin_layout.jsp").forward(request, response);
    }
}