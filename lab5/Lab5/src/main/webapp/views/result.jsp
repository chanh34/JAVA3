<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="div">
		<h1>Information</h1>
		<img src="/Lab5/${bean.photo_file}" class="img-thumbnail" alt="...">
		<div class="content">
			<h2>Fullname: </h2>
			<h2 class="success">${bean.fullname}</h2>
		</div>
		<div class="content">
			<h2>Birthday: </h2>
			<h2 class="success">${bean.birthday}</h2>
		</div>
		<div class="content">
			<h2>Gender: </h2>
			<c:choose>
				<c:when test="${bean.gender}">
					<h2 class="success">Male</h2>
				</c:when>
				<c:otherwise>
					<h2 class="success">Female</h2>
				</c:otherwise>
			</c:choose>
			
			}
		</div>
		<div class="content">
			<h2>Married: </h2>
			<c:choose>
				<c:when test="${bean.married}">
					<h2 class="success">Married</h2>
				</c:when>
				<c:otherwise>
					<h2 class="success">Not married</h2>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="content">
			<h2>Country: </h2>
			<h2 class="success">${bean.country}</h2>
		</div>
		<div class="content">
			<h2>Hobbies: </h2>
			<div>
				<c:forEach items="${bean.hobbies}" var="tu">
					<h2 class="success">${tu}</h2>
				</c:forEach>
				
			</div>
		
			
		</div>
		<div class="content note">
			<h2>Note: </h2>
			<h2 class="success">${bean.note}</h2>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
		<h2>so thich anh tu ${st}</h2>
		<a href="/Lab5">trang chủ</a>
</body>
</html>