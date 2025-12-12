package poly.com.dao;

public class Maintest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Deparments dedao= new Deparments();

//dedao.insertDeparments("04", "IT", "Phong cong nghe thong tin");
//dedao.deleteDepartment("04");
dedao.findDepartmentById("01");
dedao.printAllDepartments();
	}

}
