package Lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Bai3")
public class Bai3 extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	Map<String, Object> map = new HashMap<>();
	map.put("fullname", "Che Tram Anh");
	map.put("gender", true);
	map.put("country", "VN");
	req.setAttribute("user", map);
	req.setAttribute("editabled", true);
	req.getRequestDispatcher("/Bai3.jsp").forward(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String fullname = req.getParameter("fullname");
	System.out.println(fullname);
	req.getRequestDispatcher("/Bai3.jsp").forward(req, resp);
	}
}
