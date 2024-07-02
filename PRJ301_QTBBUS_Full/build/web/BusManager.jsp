<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #eaeaea;
        }
        .container {
            width: 80%;
            max-width: 600px;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 10px;
            text-align: center;
            display: flex;
            flex-direction: column; 
        }
        h1 {
            color: #333;
        }
        a.button {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px; 
            width: 200px; 
            background-color: #007bff;
            color: #ffffff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test="${empty sessionScope.account}">
            <%@include file="Welcome.jsp" %>
        </c:when>
        <c:otherwise>
            <h1>Manager Information</h1>
            <a href="Welcome.jsp" class="button">Home</a>
            <a href="manager_bus?key=e" class="button">Manager Bus</a>
            <a href="manager_transitpoint?key=e" class="button">Manager TransitPoint</a>
            <a href="manager_busroute?key=e" class="button">Manager BusRoute</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
