<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý phòng ban</title>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f7f7f7;
        margin: 0;
        padding: 0;
    }

    /* Khung chính – căn giữa toàn bộ nội dung */
    .container {
        width: 70%;
        margin: 40px auto;
        background: white;
        padding: 30px 40px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
    }

    h1 {
        text-align: center;
        margin-bottom: 25px;
    }

    /* Form */
    label {
        font-weight: bold;
        display: block;
        margin-bottom: 6px;
    }

    input[type=text] {
        width: 100%;
        padding: 10px;
        border: 1px solid #aaa;
        border-radius: 6px;
        margin-bottom: 15px;
        font-size: 15px;
    }

    button, input[type=submit] {
        padding: 10px 20px;
        margin: 4px;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-size: 15px;
    }

    button:hover, input[type=submit]:hover {
        opacity: 0.85;
    }

    .btn-add {
        background: #28a745; color: white;
    }

    .btn-update {
        background: #ffc107; color: white;
    }

    .btn-delete {
        background: #dc3545; color: white;
    }

    /* Search */
    .search-box {
        display: flex;
        gap: 10px;
        margin-top: 10px;
    }

    .search-box input[type=text] {
        flex: 1;
    }

    /* Bảng danh sách */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 30px;
    }

    th, td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
        font-size: 15px;
    }

    th {
        background: #f0f0f0;
        font-weight: bold;
    }

    .actions a {
        display: inline-block;
        padding: 6px 12px;
        border: 1px solid #007bff;
        color: #007bff;
        border-radius: 6px;
        text-decoration: none;
        margin-right: 5px;
        transition: 0.2s;
    }

    .actions a:hover {
        background: #007bff;
        color: white;
    }
</style>


</head>
<body>
    <%@ include file="/menu.jsp" %>

	
<div class="container">

        <h1>Danh sách Phòng Ban</h1>

        <form method="post" action="${pageContext.request.contextPath}/Departments">

            <label>Mã Phòng ban:</label>
            <input type="text" name="id" value="${departmentEdit.id}">

            <label>Tên phòng ban:</label>
            <input type="text" name="name" value="${departmentEdit.name}">

            <label>Mô tả:</label>
            <input type="text" name="description" value="${departmentEdit.description}">

            <button type="submit" class="btn-add" name="action" value="add">Thêm Mới</button>
            <button type="submit" class="btn-update" name="action" value="update">Cập nhật</button>
            <button type="submit" class="btn-delete" name="action" value="delete">Xóa</button>

            <div class="search-box">
                <input type="text" name="keyword" placeholder="Tìm theo mã phòng ban">
                <input type="submit" name="action" value="find">
            </div>

        </form>

	<!-- Danh sách -->
	<table>
		<tr>
			<th>Mã phòng ban</th>
			<th>Tên phòng ban</th>
			<th>Mô tả</th>
			<th>Hành động</th>
		</tr>

		<c:choose>
			<c:when test="${not empty departments}">
				<c:forEach var="d" items="${departments}">
					<tr>
						<td>${d.id}</td>
						<td>${d.name}</td>
						<td>${d.description}</td>
						<td class="actions"><a
							href="${pageContext.request.contextPath}/Departments/edit?id=${d.id}">Edit</a>
							<a
							href="${pageContext.request.contextPath}/Departments/delete?id=${d.id}">Delete</a>

						</td>
					</tr>
				</c:forEach>
			</c:when>

			<c:otherwise>
				<tr>
					<td colspan="4">Không có phòng ban nào!</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
	</div>

</body>
</html>
