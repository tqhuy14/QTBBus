<%-- 
    Document   : EditBusRoute
    Created on : Mar 7, 2024, 9:34:25 PM
    Author     : admin
--%>

<%@page import="Model.TransitPoint"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.BusRoute"%>
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
            BusRoute u = (BusRoute) request.getAttribute("newt");
            System.out.print(u);
            if (u == null) {
                response.sendRedirect("manager_busroute");
            }
        %>
        <h1>Edit BusRoute ID <%= u.getRouteID() %></h1>
        <form action="editbusroute" method="post">
            BusRouteID: <input type="number" name="eid" value="<%=u.getRouteID()%>" readonly=/><br/>
            <%
                ArrayList<TransitPoint> list = (ArrayList<TransitPoint>) request.getAttribute("liste");
                if (list == null || list.size() == 0) {
                    out.println("Emplty list. <br/>");
                } else {
            %>
            StrartPointID: <select name="ops">
                <%
                    for (TransitPoint b : list) {
                        if (b.getKind().equals("Bến xe")) {
                %>
                <option value="<%=b.getTransitPointID()%>" <%=b.getTransitPointID()==u.getStartPointID()?"selected":""%>><%= b.getTransitPointID()%></option>
                <%
                        }
                    }
                %>
            </select><br/>


            EndPointID: <select name="ope">
                <%
                    for (TransitPoint b : list) {
                        if (b.getKind().equals("Bến xe")) {
                %>
                <option value="<%=b.getTransitPointID()%>"<%=b.getTransitPointID()==u.getEndPointID()?"selected":""%>><%= b.getTransitPointID()%></option>
                <%
                        }
                    }
                %>
            </select><br/>
            <%
                }
            %> 
            Frequency: <input type="text" name="fre" value="<%=u.getFrequency() %>"/>(HH:mm:ss)<br/>
            StartTime: <input type="text" name="stime" value="<%=u.getStartTime() %>"/>(HH:mm:ss)<br/>
            EndTime: <input type="text" name="etime" value="<%=u.getEndTime() %>"/>(HH:mm:ss)<br/>
            NameBusRoute: <input type="text" name="name" value="<%=u.getName()%>"/><br/>
            <input type="submit" value="Edit BusRoute"/><br/>
            <input type="reset" value="Reset"/>
            <h3 style="color: red">${err}</h3>
            <h3 style="color: red">${errr}</h3>
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
