<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>QTBBus - Đăng Nhập</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        body {
                  background-image: url('img/update.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: auto;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            margin-top: 100px;
        }
        .title {
            color: #333;
        }
        .home-link {
            color: #333;
            text-decoration: none;
        }
        .error-message {
            color: red;
        }
        .login-form {
            margin-top: 20px;
        }
        .input-group {
            margin-bottom: 15px;
            text-align: left;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        .input-group input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .button-group {
            text-align: center;
        }
        .login-button, .reset-button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: #fff;
        }
        .login-button {
            background-color: #007bff;
            margin-right: 10px;
        }
        .reset-button {
            background-color: #dc3545;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="title">Chào mừng đến với QTBBus</h1>
        <nav>
<a style="font-size: 25px; color: blue; font-family: sans-serif; text-align: center; display: block; margin: 0 auto;" href="TransitPointServlet" class="home-link">Trang Chủ</a>
        </nav>
        <hr/>
        <H5>Dành cho nhân viên / Quản lý</h5>
        <h3 class="error-message">${requestScope.error}</h3>
          <%
            String username = (String) request.getAttribute("username");
            String password =(String) request.getAttribute("password");
            String temp =(String) request.getAttribute("temp");
        %>
        <form action="LoginServlet" method="get" class="login-form">
            <div class="input-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="name" value="<%= username!=null?username:"" %>">
            </div>
            <div class="input-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" value="<%= password!=null?password:"" %>">
            </div>
            <div class="button-group">
                <input type="submit" value="Đăng Nhập" class="login-button">
                <input type="reset" value="Nhập Lại" class="reset-button">
            </div>
        </form>
    </div>
</body>
</html>
