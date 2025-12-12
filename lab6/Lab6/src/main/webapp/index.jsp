<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        margin: 0;
        font-family: Arial, Helvetica, sans-serif;
        background: #f2f2f2;
    }

    nav {
        background-color: #2c3e50;
        padding: 15px 20px;
        display: flex;
        gap: 25px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
    }

    nav a {
        color: #ecf0f1;
        text-decoration: none;
        font-size: 17px;
        padding: 8px 12px;
        border-radius: 5px;
        transition: 0.3s;
    }

    nav a:hover {
        background-color: #3498db;
        color: white;
    }
</style>
</head>
<body>
 <nav>
    	<a href="${pageContext.request.contextPath}/nhanvien">Quản lý nhân viên</a>

        <a href="/Lab6/Departments/DepartmentsGui.jsp">Quản lý phòng ban</a>
    </nav>

</body>
</html>