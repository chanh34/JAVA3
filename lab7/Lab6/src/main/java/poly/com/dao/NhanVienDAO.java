package poly.com.dao;

	import java.sql.ResultSet;
	import java.util.ArrayList;
	import java.util.List;

	import poly.com.model.NhanVien;
	import poly.com.util.Jdbc; // Đảm bảo lớp Jdbc của bạn có các phương thức executeQuery và executeUpdate

	public class NhanVienDAO {

	    // --- CÁC PHƯƠNG THỨC THAO TÁC (INSERT, UPDATE, DELETE) ---

	    public void insert(NhanVien e) {
	        String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        try {
	            Jdbc.executeUpdate(sql, 
	                e.getId(), e.getPassword(), e.getFullname(), e.getPhoto(),
	                e.isGender(), new java.sql.Date(e.getBirthday().getTime()),
	                e.getSalary(), e.getDepartmentId());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Lỗi khi thêm nhân viên: " + ex.getMessage(), ex);
	        }
	    }

	    public void update(NhanVien e) {
	        String sql = "UPDATE Employees SET Password=?, Fullname=?, Photo=?, Gender=?, "
	                   + "Birthday=?, Salary=?, DepartmentId=? WHERE Id=?";
	        try {
	            Jdbc.executeUpdate(sql, 
	                e.getPassword(), e.getFullname(), e.getPhoto(),
	                e.isGender(), new java.sql.Date(e.getBirthday().getTime()),
	                e.getSalary(), e.getDepartmentId(), e.getId());
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Lỗi khi cập nhật nhân viên: " + ex.getMessage(), ex);
	        }
	    }

	    public void delete(String id) {
	        String sql = "DELETE FROM Employees WHERE Id=?";
	        try {
	            Jdbc.executeUpdate(sql, id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Lỗi khi xóa nhân viên: " + ex.getMessage(), ex);
	        }
	    }

	    // --- PHƯƠNG THỨC TRUY VẤN DỮ LIỆU ---

	    public List<NhanVien> selectAll() {
	        List<NhanVien> list = new ArrayList<>();
	        String sql = "SELECT * FROM Employees";
	        
	        // Sử dụng try-with-resources nếu Jdbc.executeQuery trả về ResultSet
	        try (ResultSet rs = Jdbc.executeQuery(sql)) { 
	            
	            System.out.println("DAO: Bắt đầu truy vấn database."); 

	            while (rs.next()) {
	            	// Lưu ý: Cần đảm bảo constructor của NhanVien (8 tham số) khớp chính xác kiểu dữ liệu
	                NhanVien e = new NhanVien(
	                    rs.getString("Id"),
	                    rs.getString("Password"),
	                    rs.getString("Fullname"),
	                    rs.getString("Photo"),
	                    rs.getBoolean("Gender"),
	                    rs.getDate("Birthday"),
	                    rs.getDouble("Salary"),
	                    rs.getString("DepartmentId")
	                );
	                list.add(e);
	            }

	            System.out.println("DAO: Số lượng nhân viên tìm thấy: " + list.size()); 
	            
	        } catch (Exception ex) {
	            // In Stack Trace nếu có lỗi (ví dụ: lỗi mapping kiểu dữ liệu)
	            ex.printStackTrace();
	            System.out.println("DAO: LỖI KHI ĐỌC DỮ LIỆU: " + ex.getMessage());
	        }

	        return list;
	    } 

	    public NhanVien findById(String id) {
	        String sql = "SELECT * FROM Employees WHERE Id=?";
	        
	        try (ResultSet rs = Jdbc.executeQuery(sql, id)) {
	            
	            if (rs.next()) {
	                return new NhanVien(
	                    rs.getString("Id"),
	                    rs.getString("Password"),
	                    rs.getString("Fullname"),
	                    rs.getString("Photo"),
	                    rs.getBoolean("Gender"),
	                    rs.getDate("Birthday"),
	                    rs.getDouble("Salary"),
	                    rs.getString("DepartmentId")
	                );
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Lỗi khi tìm nhân viên theo ID: " + ex.getMessage(), ex);
	        }

	        return null;
	    }
	}

