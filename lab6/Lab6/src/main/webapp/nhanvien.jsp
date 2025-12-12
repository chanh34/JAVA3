<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, poly.com.model.NhanVien" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %> 
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý nhân viên</title>

<style>
body {
	font-family: Arial;
	background: #f2f2f2;
	margin: 0;
	padding: 20px;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

.container {
	width: 1100px;
	margin: auto;
	display: flex;
	gap: 20px;
}

.form-box, .table-box {
	background: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

.form-box {
	width: 35%;
}

.table-box {
	width: 65%;
}

input, select {
	width: 100%;
	padding: 8px;
	margin-top: 8px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

button {
	padding: 10px 15px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	margin-right: 5px;
}

.btn-save {
	background: #4caf50;
	color: white;
}

.btn-update {
	background: #2196F3;
	color: white;
}

.btn-delete {
	background: #f44336;
	color: white;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 15px;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}

th {
	background: #2196F3;
	color: #fff;
}
</style>
</head>

<body>
	<jsp:include page="index.jsp"></jsp:include>
	<h2>QUẢN LÝ NHÂN VIÊN</h2>

	<div class="container">

		<!-- FORM -->
		<div class="form-box">
			<h3>Thông tin nhân viên</h3>

			<form action="${pageContext.request.contextPath}/nhanvien" method="post">

    <label>Mã nhân viên</label>
    <input name="id" value="${item.id}" required>

    <label>Mật khẩu</label>
    <input name="password" value="${item.password}" required>

    <label>Họ tên</label>
    <input name="fullname" value="${item.fullname}" required>

    <label>Ảnh</label>
    <input name="photo" value="${item.photo}">

    <label>Giới tính</label>
    <select name="gender">
        <option value="true" ${item.gender ? "selected" : ""}>Nam</option>
        <option value="false" ${!item.gender ? "selected" : ""}>Nữ</option>
    </select>

    <label>Ngày sinh</label>
    <fmt:formatDate value="${item.birthday}" var="birth" pattern="yyyy-MM-dd"/>
    <input type="date" name="birthday" value="${birth}">

    <label>Lương</label>
    <input type="number" step="0.01" name="salary" value="${item.salary}">

    <label>Mã phòng</label>
    <input name="departmentId" value="${item.departmentId}">

    <br><br>

    <button formaction="${pageContext.request.contextPath}/nhanvien/create" class="btn-save">Thêm</button>
    <button formaction="${pageContext.request.contextPath}/nhanvien/update" class="btn-update">Cập nhật</button>
    <button formaction="${pageContext.request.contextPath}/nhanvien/delete" class="btn-delete">Xóa</button>
    </form>


		</div>

		<div class="table-box">
			<h3>Danh sách nhân viên</h3>
			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>Tên</th>
						<th>Ảnh</th>
						<th>Giới tính</th>
						<th>Lương</th>
						<th>Phòng</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="n" items="${items}">
						<tr>
                            <td>${n.id}</td>
							<td>${n.fullname}</td>
							<td>${n.photo}</td>
							<td>${n.gender ? "Nam" : "Nữ"}</td>
							<td>${n.salary}</td>
							<td>${n.departmentId}</td>
							<td><a href="nhanvien/edit?id=${n.id}">Edit</a></td>
						</tr>
					</c:forEach>
					</tbody>
			</table>
		</div>

	</div>

</body>
</html>