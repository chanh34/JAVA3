<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/index.jsp"></jsp:include>
<hr/>

<c:url value="calculate/pheptinh" var="cal" />

<form method="post">
    <input name="a"><br>
    <input name="b"><br>
    <input class="output" value="${message}" readonly>

    <button formaction="/Lab4/${cal}/add">+</button>
    <button formaction="/Lab4/${cal}/sub">-</button>
</form>



</body>
</html>