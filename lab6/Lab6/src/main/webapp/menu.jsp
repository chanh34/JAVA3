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
        font-family: 'Segoe UI', Arial, sans-serif;
    }

    /* Thanh menu */
    .navbar {
        width: 100%;
        background: linear-gradient(135deg, #1e3c72, #2a5298);
        padding: 12px 0;
        box-shadow: 0 3px 10px rgba(0,0,0,0.2);
        position: sticky;
        top: 0;
        z-index: 999;
    }

    .nav-container {
        width: 90%;
        max-width: 1200px;
        margin: auto;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    /* Logo */
    .nav-logo {
        font-size: 22px;
        font-weight: bold;
        color: white;
        letter-spacing: 1px;
        text-decoration: none;
        transition: 0.3s;
    }

    .nav-logo:hover {
        opacity: 0.7;
    }

    /* Các link */
    .nav-links a {
        color: #ecf0f1;
        text-decoration: none;
        font-size: 17px;
        padding: 10px 18px;
        margin-left: 10px;
        border-radius: 8px;
        transition: 0.3s ease;
    }

    .nav-links a:hover {
        background: rgba(255, 255, 255, 0.2);
        transform: translateY(-2px);
        backdrop-filter: blur(3px);
    }
</style>

</head>
<body>
<nav class="navbar">
    <div class="nav-container">

        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/nhanvien">Quản lý nhân viên</a>
            <a href="/Lab6/Departments/DepartmentsGui.jsp">Quản lý phòng ban</a>
        </div>
    </div>
</nav>
</body>
</html>