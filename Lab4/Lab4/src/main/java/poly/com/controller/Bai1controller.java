package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/account/login")
public class Bai1controller extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	req.setAttribute("message", "enter username");
	req.getRequestDispatcher("/account/login.jsp").forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if (username.equalsIgnoreCase("chanh") && password.equals("123")) {
        req.setAttribute("message", "Login successfully");
    } else {
        req.setAttribute("message", "Invalid username or password");
    }

    req.getRequestDispatcher("/account/login.jsp").forward(req, resp);
}

}
