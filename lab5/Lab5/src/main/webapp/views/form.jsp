<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Information of staff</h1>
	<form action="/Lab5/add" method="post" enctype="multipart/form-data">
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">Fullname </span> 
			<input name="fullname"
				type="text" class="form-control" placeholder=""
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group mb-3">
		  <input type="file" name="photo_file" class="form-control" id="inputGroupFile02">
		  <label class="input-group-text" for="inputGroupFile02">Image</label>
		</div>
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1">Birthday </span> 
			<input name="birthday"
				type="date" class="form-control" placeholder=""
				aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="item">
			<div class="item_item">
				<label class="label">Gender: </label>
		        <input class="radio" type="radio" id="checkbox-circle1" name="gender" value="True">
		        <label for="checkbox-circle1">Nam </label>
		        <input class="radio" type="radio" id="checkbox-circle1" name="gender" value="False">
		        <label for="checkbox-circle1">Nữ</label>
			</div> 
			<div class="item_item item2">
		        <input type="checkbox" id="checkbox-circle1" name="married">
		        <label for="checkbox-circle1">Married ?</label>
			</div> 
	    </div>
	    <div class="selectdiv">
	      <select name="country">
	          <option selected> Country </option>
	          <option value="Vietnamese">Việt Nam</option>
	          <option value="United States">Mỹ</option>
	          <option value="United Kingdom">Hongkong</option>
	      </select>
		</div>
		<div class="item">
			<div class="item_item">
				<label class="label">Hobbies: </label>
		        <input class="radio" type="checkbox" id="checkbox-circle1" name="hobbies" value="Coding">
		        <label for="checkbox-circle1">Coding</label>
		        <input class="radio" type="checkbox" id="checkbox-circle1" name="hobbies" value="Travel">
		        <label for="checkbox-circle1">Travel</label>
		        <input class="radio" type="checkbox" id="checkbox-circle1" name="hobbies" value="Music">
		        <label for="checkbox-circle1">Music</label>
		        <input class="radio" type="checkbox" id="checkbox-circle1" name="hobbies" value="Other">
		        <label for="checkbox-circle1">Other</label>
			</div> 
	    </div>
	    <div class="input-group note">
		  <span class="input-group-text">Notes</span>
		  <textarea name="note" class="form-control" aria-label="With textarea"></textarea>
		</div>
		<div class="wrap">
		  <button type="submit" class="button">Add</button>
		</div>
	</form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
		<a href="/Lab5">trang chủ</a>
</body>
</html>