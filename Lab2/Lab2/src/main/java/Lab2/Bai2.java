package Lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet ("/Bai2")
public class Bai2 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	req.setAttribute("message", "Welcome to FPT Polytechnic");
	Map<String, Object> map = new HashMap<>();
	map.put("fullname", "Tram Anh");
	map.put("gender", "Female");
	map.put("country", "Việt Nam");
	req.setAttribute("user", map);
	req.getRequestDispatcher("/Bai2.jsp").forward(req, resp);
	
}
}