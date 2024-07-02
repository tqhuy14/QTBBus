<%-- 
    Document   : addEmployee
    Created on : Mar 4, 2024, 10:14:14 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add new Employee</h1>
        <form action="<%=request.getContextPath()%>/addemployee" method="post">
            Employee ID: <input type="number" name="eid" value="${param.userID}" required/><br/>
            UserName: <input type="text" name="username" value="${param.username}" required/><br/>
            Password <input type="text" name="password" value="${param.password}" required/><br/>
            <input type="submit" value="Add Employee"/><br/>
            <input type="reset" value="Reset"/>
            <h3 style="color: red">${err}</h3>
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
