<%-- 
    Document   : AddRouteTransitPoint.jsp
    Created on : Mar 11, 2024, 12:45:11 PM
    Author     : admin
--%>


<%@page import="Model.RouteTransitPoint"%>
<%@page import="Dal.DAO"%>
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
        <form action="addroutetransitpoint" method="post">

            <%
                DAO dao = new DAO();
                ArrayList<TransitPoint> list = (ArrayList<TransitPoint>) request.getAttribute("list");
                ArrayList<RouteTransitPoint> listtp = (ArrayList<RouteTransitPoint>) request.getAttribute("listtp");
                int n = (Integer) request.getAttribute("num");
                int rid = (Integer) request.getAttribute("rid");
                if (list == null || list.size() == 0) {
                    out.println("Emplty list. <br/>");
                } else {
            %>
            <input name="numt" value="<%=n%>" hidden/>
            <input name="rid" value="<%=rid%>" hidden/>
            <h2>Add TransitPoint of RouteID <%=rid%></h2>
            <%
                int count = 1;
                for (int i = 0; i < listtp.size(); i++) {
                    TransitPoint tp = dao.getTransitPointByID(listtp.get(i).getTransitPointID());
                    if (i == 0) {
            %>
            <h3 style="color: chocolate">Bến: <input type="text" style="width: 338px" value="<%=tp.getName()%>"/> Arrival Time:<input value="<%=listtp.get(i).getArrivalTime()%>" type="text"/></h3><br/>
                <%
                } else if (i > 0 && i < listtp.size() - 1) {
                %>
            TransitPoint <%=count%>: <input style="width: 338px" type="text" value="<%=tp.getName()%>"/> Arrival Time:<input value="<%=listtp.get(i).getArrivalTime()%>" type="text"/>(HH:mm:ss)<br/>
            <%
                        count++;
                    }

                }
            %>

            <%
                for (int i = 0 + listtp.size() - 2; i < n + listtp.size() - 2; i++) {
            %>
            TransitPoint <%=i + 1%>: <select style="width: 345px" name="op<%=i%>">
                <%
                    for (TransitPoint tp : list) {
                        if (tp.getKind().equals("Điểm dừng")) {
                %>
                <option value="<%=tp.getTransitPointID()%>"><%=tp.getName()%></option>
                <%
                        }
                    }
                %>
            </select>  Arrival Time:<input name="artime<%=i%>" type="text"/>(HH:mm:ss)<br/>
            <%
                    }
                }
                TransitPoint tpe = dao.getTransitPointByID(listtp.get(listtp.size() - 1).getTransitPointID());
            %>

            <h3 style="color: chocolate">Bến: <input type="text" style="width: 338px" value="<%=tpe.getName()%>"/> Arrival Time:<input value="<%=listtp.get(listtp.size() - 1).getArrivalTime()%>" type="text"/></h3><br/>

            <br/><input type="submit" value="Add"/>
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
