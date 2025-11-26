package poly.com.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet("/add")
public class Bai3controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Đường dẫn lưu ảnh
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển đến form.jsp
        req.getRequestDispatcher("form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String fullname = request.getParameter("fullName");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String married = request.getParameter("married");
        String country = request.getParameter("country");
        String[] hobbies = request.getParameterValues("hobbies");
        String note = request.getParameter("note");

        // Xử lý file ảnh đại diện
        Part filePart = request.getPart("photo_file"); // Lấy ảnh đại diện
        String fileName = filePart.getSubmittedFileName(); // Tên file

        // Tạo đường dẫn lưu file
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Lưu file lên server
        filePart.write(uploadPath + File.separator + fileName);

        // Gửi dữ liệu qua trang kết quả
        request.setAttribute("fullname", fullname);
        request.setAttribute("password", password);
        request.setAttribute("gender", gender);
        request.setAttribute("married", married != null ? "Yes" : "No");
        request.setAttribute("country", country);
        request.setAttribute("hobbies", hobbies);
        request.setAttribute("note", note);
        request.setAttribute("photo", fileName);

        request.getRequestDispatcher("ketqua.jsp").forward(request, response);
}
}
