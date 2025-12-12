<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
	<mark style="color: red;">${message}</mark>
	<form action="/Lab5/login" method="post">
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">Username </span> 
			<input name="username" value="${username}"
				type="text" class="form-control" placeholder=""
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">Password </span> 
			<input name="password" value="${password}"
				type="password" class="form-control" placeholder=""
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="form-check form-switch">
		  <input name="remember" class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault">
		  <label class="form-check-label" for="flexSwitchCheckDefault">Remember me?</label>
		</div>
		<div class="wrap">
		  <button type="submit" class="button">Login</button>
		</div>
	</form>
	<a href="/Lab5">trang chủ</a>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>