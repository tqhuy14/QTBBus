<%-- 
    Document   : EditTransitPoint
    Created on : Mar 6, 2024, 9:05:31 PM
    Author     : admin
--%>

<%@page import="Model.TransitPoint"%>
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
        <div class="container">
              <%
            TransitPoint u = (TransitPoint) request.getAttribute("newt");
            if (u == null) {
                response.sendRedirect("manager_transitpoint");
            }
        %>
        <h1>Edit TransitPoint ID <%=u.getTransitPointID()%></h1>
        <form action="edittransitpoint" method="post">
            TransitPointID: <input type="number" name="eid" value="<%=u.getTransitPointID()%>" readonly=/><br/>
            Kind: 
            <select name="kind" >
                <option value="Bến xe" <%=u.getKind().equals("Bến xe") ?"selected":""%>>Bến xe</option>
                <option value="Điểm dừng"<%=u.getKind().equals("Điểm dừng") ?"selected":""%>>Điểm dừng</option>
            </select><br/>
            Name: <input type="text" name="name" value="<%=u.getName()%>"/><br/>
            Location: 
            <select name="loca" >
                <option value="TP Hà Nội" <%=u.getLocation().equals("TP Hà Nội") ?"selected":""%>>TP Hà Nội</option>
                <option value="TP Hồ Chí Minh"<%=u.getLocation().equals("TP Hồ Chí Minh") ?"selected":""%>>TP Hồ Chí Minh</option>
            </select><br/>
            <input type="submit" value="Edit TransitPoint"/><br/>
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
        </div>
    </body>
</html>
