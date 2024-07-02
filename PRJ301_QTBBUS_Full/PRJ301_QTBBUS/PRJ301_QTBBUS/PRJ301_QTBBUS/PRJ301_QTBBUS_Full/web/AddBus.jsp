<%-- 
    Document   : addBus
    Created on : Mar 5, 2024, 8:27:48 PM
    Author     : admin
--%>

<%@page import="Model.BusRoute"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>Add new Bus</h1>
        <form action="<%=request.getContextPath()%>/addbus" method="post">
            BusID: <input type="number" name="bid" value="${param.busID}" required/><br/>
            <%
            ArrayList<BusRoute> list = (ArrayList<BusRoute>)request.getAttribute("liste");
                    if (list==null || list.size() == 0){
                        out.println("Emplty list. <br/>");
                    }
                    else {
            %>
            Route Name: <select name="op">
                <%
                    for (BusRoute b : list){
                %>
                <option value="<%=b.getRouteID()%>"><%= b.getName() %></option>
                <%
                    }
                }
                %>    
            </select><br/>
            BusNumber: <input type="text" name="num" value="${param.busNumber}" required/><br/>
            <input type="submit" value="Add Bus"/><br/>
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
