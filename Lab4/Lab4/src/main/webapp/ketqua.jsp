<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kết quả</title>
</head>
<body>
<h1>Kết quả đăng ký</h1>

<p><strong>Tên Đăng Nhập:</strong> ${fullname}</p>

<p><strong>Mật Khẩu:</strong> ${password}</p>

<p><strong>Giới Tính:</strong>
    <c:choose>
        <c:when test="${gender == 'True'}">Nam</c:when>
        <c:otherwise>Nữ</c:otherwise>
    </c:choose>
</p>

<p><strong>Đã có gia đình:</strong> ${married}</p>

<p><strong>Quốc gia:</strong> ${country}</p>

<p><strong>Sở thích:</strong>
    <c:if test="${not empty hobbies}">
        <ul>
            <c:forEach var="hobby" items="${hobbies}">
                <li>${hobby}</li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty hobbies}">
        Không có sở thích được chọn
    </c:if>
</p>

<p><strong>Ghi chú:</strong>
    <c:if test="${not empty note}">
        ${note}
    </c:if>

    <c:if test="${empty note}">
        Không có ghi chú
    </c:if>
</p>

<p><strong>Ảnh đại diện:</strong></p>
<img src="${photoURL}" alt="Ảnh đại diện" style="max-width: 200px;">

<br><br>
<a href="/form.jsp">Trở lại trang chủ</a>


</body>
</html>
