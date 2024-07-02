<%-- 
    Document   : BusRouteManager
    Created on : Mar 7, 2024, 8:49:40 AM
    Author     : admin
--%>

<%@page import="Model.BusRoute"%>
<%@page import="Model.TransitPoint"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                <link rel="stylesheet" type="text/css" href="css/admin.css">

        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete BusRoute with id = " + id + "?")) {
                    window.location = "deletebusroute?tID=" + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>List of BusRoute</h1>
            <%
                ArrayList<BusRoute> liste = (ArrayList<BusRoute>)request.getAttribute("listbr");
                if (liste==null || liste.size() == 0){
                    out.println("Emplty list. <br/>");
                }
                else {
            %>
            <table border="1px solid" style="border-collapse: collapse">
                <tr>
                    <th>RouteID</th>
                    <th>StartPointID</th>
                    <th>EndPointID</th>
                    <th>Frequency</th>
                    <th>StartTime</th>
                    <th>EndTime</th>
                    <th>Name</th>
                    <th>Action</th>
                </tr>
                <%
                for (BusRoute e : liste){
                %>
                <tr>
                    <td><%=e.getRouteID()%></td>
                    <td><%=e.getStartPointID()%></td>
                    <td><%= e.getEndPointID() %></td>
                    <td><%=e.getFrequency() %></td>
                    <td><%=e.getStartTime() %></td>
                    <td><%=e.getEndTime() %></td>
                    <td><%=e.getName() %></td>
                    <td>
                        <a class="button" style="    width: 70px; height: 15px;text-align: center;" href="editbusroute?tID=<%=e.getRouteID()%>">Edit</a> &nbsp;&nbsp;
                        <a class="button"  style="    width: 70px; height: 15px;text-align: center;" onclick="doDelete('<%=e.getRouteID()%>')">Delete</a> &nbsp;&nbsp;
                        <a class="button"  style="    width: 70px; height: 15px;text-align: center;"  href="routetranstpoint?tID=<%=e.getRouteID()%>">TransitPoint</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table><br/>
            <a href="addbusroute"><input class="button" type="submit" value="Add BusRoute"></a>
                <% 
                }
                %>
                    <c:choose>
            <c:when test="${sessionScope.account.getRole().equals('Admin')}">
                <a href="admin"><input class="button" type="submit" value="BACK"></a><br/><br/>
            </c:when>
            <c:otherwise>
                <a href="Employee.jsp"><input class="button" type="submit" value="BACK"></a><br/><br/>
            </c:otherwise>
        </c:choose>
        </div>
    
    </body>
</html>
