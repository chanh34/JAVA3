package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import poly.com.dao.Deparments;
import poly.com.model.Departments;

@WebServlet({"/Departments","/Departments/loadall","/Departments/add","/Departments/delete","/Departments/edit","/Departments/find","/Departments/update"})
public class DeparmentsController extends HttpServlet {

    // Hàm xóa dùng chung
    protected void deleteDepartments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Deparments dao = new Deparments();
        dao.deleteDepartment(id);
    }

    // Xử lý POST: add / update / delete / find

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String action = req.getParameter("action");
        Deparments dao = new Deparments();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if ("add".equals(action)) {

            Departments d = new Departments(id, name, description);
            dao.insert(d);
            resp.sendRedirect(req.getContextPath() + "/Departments");
            return;

        } else if ("update".equals(action)) {

            Departments d = new Departments(id, name, description);
            dao.update(d);
            resp.sendRedirect(req.getContextPath() + "/Departments");
            return;

        } else if ("delete".equals(action)) {

            dao.deleteDepartment(id);
            resp.sendRedirect(req.getContextPath() + "/Departments");
            return;

        } else if ("find".equals(action)) {

            Departments d = dao.findDepartmentById1(req.getParameter("keyword"));
            if (d != null) req.setAttribute("departmentEdit", d);
        }

        List<Departments> list = dao.selectAll();
        req.setAttribute("departments", list);

        req.getRequestDispatcher("/Departments/DepartmentsGui.jsp").forward(req, resp);
    }

    // Xử lý GET: edit / delete
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        Deparments dao = new Deparments();

        if (uri.contains("edit")) {

            String id = req.getParameter("id");
            if (id != null) {
                Departments d = dao.findDepartmentById1(id);
                req.setAttribute("departmentEdit", d);
                
            }

        } else if (uri.contains("delete")) {

            deleteDepartments(req, resp);
        }

        // Load danh sách
        List<Departments> list = dao.selectAll();
        req.setAttribute("departments", list);

        req.getRequestDispatcher("/Departments/DepartmentsGui.jsp").forward(req, resp);
    }
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Deparments dao = new Deparments();
    	List<Departments> list = dao.selectAll();
        req.setAttribute("departments", list);

        req.getRequestDispatcher("/Departments/DepartmentsGui.jsp").forward(req, resp);
    }
}
