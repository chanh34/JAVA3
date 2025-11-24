<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }


        /* Thanh menu */
        nav {
            background-color: #ff6600;
            text-align: center;
            padding: 10px 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            margin: 0 20px;
            font-weight: bold;
            font-size: 18px;
            transition: all 0.3s ease;
        }

        /* Hiệu ứng hover */
        nav a:hover {
            color: #000;
            background-color: #fff;
            padding: 8px 15px;
            border-radius: 5px;
        }

        /* Đường kẻ phân cách logo và menu */
        hr {
            border: none;
            border-top: 1px solid #ccc;
            margin: 0;
        }
    </style>
</head>

<body>
<img style = "display: block;margin: 20px auto;height: 80px;"src="Img/Logo.png" alt="Logo"/>

    <nav>
    	<a href="/Lab2/dangnhap.jsp">Đăng nhập</a>
        <a href="/Lab2/index.jsp">Trang chủ</a>
        <a href="/Lab2/sanpham.jsp">Sản phẩm</a>
        <a href="#">Giới thiệu</a>
        <a href="#">Liên lạc</a>
        <a href="#">Hỏi đáp</a>
        <a href="/Lab2/dangxuat.jsp">Đăng xuất</a>
    </nav>

    <hr>
</body>
</html>