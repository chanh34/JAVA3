<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
    <h1>${global.title}</h1>
</header>

<nav>
    <hr>
    <c:url value="/home" var="path" />
    <a href="${path}/index">Home</a> |
    <a href="${path}/about">About Us</a> |
    <a href="${path}/contact">Contact Us</a>
    |
    <a href="?lang=en">English</a> |
    <a href="?lang=vi">Tiếng Việt</a>
    <hr>
</nav>

<main>
    <jsp:include page="${view}" />
</main>

<footer>
    <hr>
    © 2024 FPT Polytechnic
</footer>
</body>
</html>