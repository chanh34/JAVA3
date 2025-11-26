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
            background: #f5f6fa;
            padding: 30px;
        }

        h1 {
            text-align: center;
            color: #2d3436;
        }

        form {
            width: 450px;
            margin: auto;
            background: #ffffff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }

        .form-group {
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #2d3436;
        }

        input[type="text"],
        input[type="password"],
        input[type="file"],
        select,
        textarea {
            padding: 8px;
            border-radius: 6px;
            border: 1px solid #b2bec3;
            outline: none;
            transition: 0.3s;
        }

        input:focus,
        select:focus,
        textarea:focus {
            border-color: #0984e3;
        }

        .checkbox-group,
        .radio-group {
            display: flex;
            gap: 10px;
            align-items: center;
            flex-wrap: wrap;
        }

        button {
            width: 100%;
            background: #0984e3;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            transition: 0.3s;
        }

        button:hover {
            background: #74b9ff;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #0984e3;
            text-decoration: none;
        }
    </style>
</head>
<body>
<center><h1>Information of staff</h1></center>

    <form action="/Lab4/add" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label>Tên Đăng Nhập</label>
            <input name="fullName" type="text"/>
        </div>

        <div class="form-group">
            <label>Mật Khẩu</label>
            <input type="password" name="password"/>
        </div>

        <div class="form-group">
            <label>Ảnh đại diện</label>
            <input type="file" name="photo_file"/>
        </div>

        <div class="form-group">
            <label>Giới Tính</label>
            <input type="radio" name="gender" value="True"/> Male
            <input type="radio" name="gender" value="False"/> Female
        </div>

        <div class="form-group">
            <input type="checkbox" name="married"/> Đã có gia đình?
        </div>

        <div class="form-group">
            <label>Quốc gia</label>
            <select name="country">
                <option selected disabled>Country</option>
                <option value="Vietnamese">Vietnam</option>
                <option value="United States">HongKong</option>
                <option value="United Kingdom">Thailand</option>
            </select>
        </div>

        <div class="form-group">
            <label>Sở thích</label>
            <input type="checkbox" name="hobbies" value="Coding"/> Coding
            <input type="checkbox" name="hobbies" value="Travel"/> Travel
            <input type="checkbox" name="hobbies" value="Music"/> Music
            <input type="checkbox" name="hobbies" value="Other"/> Other
        </div>

        <div class="form-group">
            <label>Ghi chú</label>
            <textarea name="note"></textarea>
        </div>

        <button type="submit">Đăng Ký</button>
      <a href="http://localhost:8080/Lab4/ketqua.jps">Đăng nhập</a>
      </form>
</body>
</html>