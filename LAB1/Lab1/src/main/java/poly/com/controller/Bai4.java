package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet({"/bai4","/Crud/create","/Crud/update","/Crud/delete","/Crud/edit"})
public class Bai4 extends  HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String uri = req.getRequestURI();

	// Kiểm tra từng đường dẫn và xuất thông báo tương ứng
	if (uri.contains("/Crud/create")) {
	    resp.getWriter().println("<h1>Creating a new record...</h1>");
	} else if (uri.endsWith("/Crud/update")) {
	    resp.getWriter().println("<h1>Updating an existing record...</h1>");
	} else if (uri.endsWith("/Crud/delete")) {
	    resp.getWriter().println("<h1>Deleting a record...</h1>");
	}else if (uri.endsWith("/Crud/edit")) {
		resp.getWriter().println("<h1>Editing a record...</h1>");    
	} else {
	    resp.getWriter().println("<h1>Not known</h1>");
	}
}
}
