<%-- 
    Document   : AddBusRoute
    Created on : Mar 7, 2024, 9:02:09 AM
    Author     : admin
--%>

<%@page import="Model.TransitPoint"%>
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
        <h1>Add new BusRoute</h1>
        <form action="<%=request.getContextPath()%>/addbusroute" method="post">
            BusRouteID: <input type="number" name="brid" value="${param.brid}" required/><br/>
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
                <option value="<%=b.getTransitPointID()%>"><%= b.getTransitPointID()%></option>
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
                <option value="<%=b.getTransitPointID()%>"><%= b.getTransitPointID()%></option>
                <%
                        }
                    }
                %>
            </select><br/>
            <%
                }
            %> 
            Frequency: <input type="text" name="fre" value="${param.fre}" required/>(HH:mm:ss)<br/>
            StartTime: <input type="text" name="stime" value="${param.stime}" required/>(HH:mm:ss)<br/>
            EndTime: <input type="text" name="etime" value="${param.etime}" required/>(HH:mm:ss)<br/>
            NameBusRoute: <input type="text" name="name" value="${param.name}" required/><br/>
            <input type="submit" value="Add BusRoute"/><br/>
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
    </body>
</html>
