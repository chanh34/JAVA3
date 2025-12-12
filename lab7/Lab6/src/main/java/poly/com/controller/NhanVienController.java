package poly.com.controller;

	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.List;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	import poly.com.dao.NhanVienDAO;
	import poly.com.model.NhanVien;

	@WebServlet({"/nhanvien", "/nhanvien/create", "/nhanvien/update", "/nhanvien/delete", "/nhanvien/edit"})
	public class NhanVienController extends HttpServlet {

	    private NhanVienDAO dao = new NhanVienDAO();
	    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String uri = req.getRequestURI();

	        if (uri.contains("delete")) {
	            delete(req, resp);
	        } else if (uri.contains("edit")) {
	            edit(req, resp);
	        } else {
	            list(req, resp);
	        }
	        
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String uri = req.getRequestURI();

	        if (uri.contains("create")) {
	            create(req, resp);
	        } else if (uri.contains("update")) {
	            update(req, resp);
	        }
	    }

	    private void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

	        List<NhanVien> list = dao.selectAll();
	        req.setAttribute("items", list);
	        req.getRequestDispatcher("/nhanvien.jsp").forward(req, resp);
	    }

	    private void edit(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        String id = req.getParameter("id");
	        NhanVien nv = dao.findById(id);
	        req.setAttribute("item", nv);

	        List<NhanVien> list = dao.selectAll();
	        req.setAttribute("items", list);

	        req.getRequestDispatcher("/nhanvien.jsp").forward(req, resp);
	    }


	    private void create(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        NhanVien nv = getForm(req);
	        dao.insert(nv);
	        resp.sendRedirect(req.getContextPath() + "/nhanvien");
	    }

	    private void update(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        NhanVien nv = getForm(req);
	        dao.update(nv);
	        resp.sendRedirect(req.getContextPath() + "/nhanvien");
	    }

	    private void delete(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {

	        String id = req.getParameter("id");
	        dao.delete(id);
	        resp.sendRedirect(req.getContextPath() + "/nhanvien");
	    }

	    private NhanVien getForm(HttpServletRequest req) {
	        try {
	            NhanVien e = new NhanVien();
	            e.setId(req.getParameter("id"));
	            e.setPassword(req.getParameter("password"));
	            e.setFullname(req.getParameter("fullname"));
	            e.setPhoto(req.getParameter("photo"));
	            e.setGender(Boolean.parseBoolean(req.getParameter("gender")));
	            e.setBirthday(sdf.parse(req.getParameter("birthday")));
	            e.setSalary(Double.parseDouble(req.getParameter("salary")));
	            e.setDepartmentId(req.getParameter("departmentId"));
	            return e;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }
	    
	    
	}
	    
	
	
