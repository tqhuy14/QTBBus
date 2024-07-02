<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.TransitPoint"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>QTBBus</title>
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <style>
        body {
                  background-image: url('img/update.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        select {
            width: calc(100% - 22px);
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        button {
            width: calc(100% - 22px);
            padding: 15px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            margin-bottom: 10px;
        }

        .switch-button {
            width: calc(100% - 22px);
            padding: 15px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            margin-bottom: 10px;
        }

        .search-button {
            width: calc(100% - 22px);
            padding: 15px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            margin-bottom: 10px;
        }
    </style>
    <script>
        function switchPoints() {
            var startPoint = document.getElementsByName("startPoint")[0];
            var endPoint = document.getElementsByName("endPoint")[0];

            // Lưu giá trị của Điểm Đi
            var temp = startPoint.value;

            // Gán giá trị Điểm Đến cho Điểm Đi và ngược lại
            startPoint.value = endPoint.value;
            endPoint.value = temp;
        }
    </script>
</head>
<body>
    <h1>Tìm đường xe buýt</h1>

    <form action="Search" method="get">
        <h3 class="error-message">${requestScope.error}</h3>
        Chọn vùng: <select name="Location">

            <%
                if (request.getAttribute("listTransitPoint") != null) {
                List<TransitPoint> listTransitPoint = (List<TransitPoint>) request.getAttribute("listTransitPoint");
                List<String> uniqueValuesList = new ArrayList<>();
                for (TransitPoint t : listTransitPoint) {
                    if (!uniqueValuesList.contains(t.getLocation())) {
                    uniqueValuesList.add(t.getLocation());
            %>
            <option value="<%=t.getLocation()%>"><%=t.getLocation()%></option>
            <%
                }
             }
            }
            %>

        </select><br>

        <!-- Điểm Đi -->
        <label for="startPoint">Điểm Đi:</label>
        <input name="startPoint" list="listOp" type="text" placeholder="Nhập địa chỉ"><br>

        <!-- Điểm Đến -->
        <label for="endPoint">Điểm Đến:</label>
        <input name="endPoint" list="listOp" type="text" placeholder="Nhập địa chỉ"><br>

        <!-- Datalist -->
        <datalist id="listOp">
            <c:forEach var="c" items="${requestScope.listTransitPoint}">
                <option value="${c.getName()}"></option>
            </c:forEach>
        </datalist>
        
        <!-- Nút Chuyển Điểm Đi và Điểm Đến -->
        <button type="button" onclick="switchPoints()" class="switch-button">Chuyển</button><br>
        
        <!-- Nút Search -->
        <input type="submit" value="Tìm Kiếm" class="search-button">
    </form>
</body>
</html>
