package poly.com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import poly.com.model.Staff;
@MultipartConfig
@WebServlet("/add")
public class staffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public staffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/views/form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			request.setCharacterEncoding("utf-8");
			
			DateTimeConverter dtc = new DateConverter(new Date());
			dtc.setPattern("dd/MM/yyyy");
			ConvertUtils.register(dtc, Date.class);
			
			
			Staff staff = new Staff();
			
			BeanUtils.populate(staff, request.getParameterMap()); // 
			
			
			
			
			File dir = new File(request.getServletContext().getRealPath("/files"));
			if(!dir.exists()) {
				dir.mkdir();
				//System.out.print(request.getServletContext() + "anhtu");
			}
			
			Part photo = request.getPart("photo_file");
			File photoFile = new File(dir, photo.getSubmittedFileName());
			photo.write(photoFile.getAbsolutePath());
			
			staff.setPhoto_file(photoFile.getName());
			
			request.setAttribute("bean", staff);
			// lấy sở thích cách 2
			
			String lay=Arrays.toString(staff.getHobbies());
			String catchuoi=lay.substring(1, lay.lastIndexOf("]")) +".";
			
			request.setAttribute("st", catchuoi);
			//
			 
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.getRequestDispatcher("/views/result.jsp").forward(request, response);
	}
}
