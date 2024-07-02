<%-- 
    Document   : EditEmployee
    Created on : Mar 4, 2024, 10:32:09 PM
    Author     : admin
--%>

<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link rel="stylesheet" type="text/css" href="css/edit.css">
    </head>
    <body>
        <%
            User u = (User) request.getAttribute("newu");
            if (u == null) {
                response.sendRedirect("admin");
            }
        %>
        <h1>Edit Employee ID <%=u.getUserID()%></h1>
        <form action="editemployee" method="post">
            Employee ID: <input type="number" name="eid" value="<%=u.getUserID()%>" readonly=/><br/>
            UserName: <input type="text" name="username" value="<%=u.getUsername()%>"/><br/>
            Password <input type="text" name="password" value="<%=u.getPassword()%>"/><br/>
            <input type="submit" value="Edit Employee"/><br/>
            <input type="reset" value="Reset"/>
        </form>
    <c:choose>
        <c:when test="${sessionScope.account.getRole().equals('Admin')}">
            <a href="admin"><input type="submit" value="BACK"></a><br/><br/>
        </c:when>
        <c:otherwise>
            <a href="Employee.jsp"><input type="submit" value="BACK"></a><br/><br/>
        </c:otherwise>
    </c:choose>
</body>
</html>
