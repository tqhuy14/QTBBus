<%@ page import="Model.BusRoute" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bus" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Bus</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css">

</head>
<body>
    <div class="container">
        <%
            Bus u = (Bus) request.getAttribute("newb");
            if (u == null) {
                response.sendRedirect("manager_bus");
            }
        %>
        <h1>Edit Bus ID <%= u.getBusID() %></h1>
        <form action="editbus" method="post">
            <input type="hidden" name="eid" value="<%= u.getBusID() %>"/>
            <%
                ArrayList<BusRoute> list = (ArrayList<BusRoute>) request.getAttribute("br");
                if (list == null || list.size() == 0) {
                    out.println("Empty list. <br/>");
                } else {
            %>
            <label for="op">RouteID:</label>
            <select name="op" id="op">
                <%
                    for (BusRoute b : list) {
                %>
                <option value="<%= b.getRouteID() %>" <%= b.getRouteID() == u.getBusRoute() ? "selected" : "" %>>
                    <%= b.getName() %>
                </option>
                <%
                    }
                }
                %>
            </select>
            <label for="num">BusNumber:</label>
            <input type="text" name="num" id="num" value="<%= u.getBusNumber() %>" required/>
            <input type="submit" value="Edit Bus"/>
            <input type="reset" value="Reset"/>
        </form>
        <c:choose>
            <c:when test="${sessionScope.account.getRole().equals('Admin')}">
                <a href="admin"><input type="submit" value="BACK"></a>
            </c:when>
            <c:otherwise>
                <a href="Employee.jsp"><input type="submit" value="BACK"></a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
