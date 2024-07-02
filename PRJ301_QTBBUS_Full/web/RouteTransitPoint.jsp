<%-- 
    Document   : RouteTransitPoint
    Created on : Mar 8, 2024, 10:28:34 PM
    Author     : admin
--%>

<%@page import="Model.TransitPoint"%>
<%@page import="Dal.DAO"%>
<%@page import="Model.RouteTransitPoint"%>
<%@page import="Model.BusRoute"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <%
                ArrayList<RouteTransitPoint> liste = (ArrayList<RouteTransitPoint>) request.getAttribute("list");
                DAO dao = new DAO();
                if (liste == null || liste.size() == 0) {
                    out.println("Emplty list. <br/>");
                } else {
                int count = 1;
            %>
            <h1>TransitPoint of BusRoute ID=<%= liste.get(1).getRouteID()%></h1>

            <%
                for (int i = 0; i < liste.size(); i++) {
                    TransitPoint tp = dao.getTransitPointByID(liste.get(i).getTransitPointID());
                    if (i == 0) {

            %>
            <h3 style="color: chocolate">Bến: <input type="text" style="width: 350px" value="<%=tp.getName()%>"/></h3><br/>
                <%
                } else if (i == liste.size() - 1) {
                %>
            <h3 style="color: chocolate">Bến: <input type="text" style="width: 350px" value="<%=tp.getName()%>"/></h3> <br/>
                <%
                } else {
                  
                %>
            TransitPoint<%=count%>: <input style="width: 350px" type="text" value="<%=tp.getName()%>"/> <a href="deleteroutetransitpoint?tID=<%=tp.getTransitPointID() %>&rId=<%=liste.get(1).getRouteID()%>">Delete</a><br/>
            <% 
                count+=1;
                        }
                    }
                }
            %>
            <form action="addroutetransitpoint" method="get">
                Enter number of TransitPoint to add: <input name="numt" type="number"/>
                <input name="rid" value="<%=liste.get(1).getRouteID()%>" hidden/>
                <input type="submit" value="Add"> 
            </form>     

        </div>
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
