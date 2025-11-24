package Lab2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Bai4")
public class Bai4 extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	User bean = new User();
	bean.setFullname("Nguyễn Văn Tèo");
	bean.setGender(true);
	bean.setCountry("VN");
	req.setAttribute("user", bean);
	req.setAttribute("editabled", true);
	req.getRequestDispatcher("/Bai4.jsp").forward(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String fullname = req.getParameter("fullname");
	System.out.println(fullname);
	req.getRequestDispatcher("/Bai4.jsp").forward(req, resp);
	}
}
