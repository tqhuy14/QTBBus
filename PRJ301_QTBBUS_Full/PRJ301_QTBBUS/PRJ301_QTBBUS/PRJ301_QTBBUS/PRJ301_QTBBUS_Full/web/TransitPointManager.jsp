<%-- 
    Document   : TransitPointManager
    Created on : Mar 6, 2024, 5:20:54 PM
    Author     : admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.TransitPoint"%>
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
                if (confirm("Are you sure to delete TransitPoint with id = " + id + "?")) {
                    window.location = "deletetransitpoint?tID=" + id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <h1>List of TransitPoint</h1>
            <%
                ArrayList<TransitPoint> liste = (ArrayList<TransitPoint>)request.getAttribute("listp");
                if (liste==null || liste.size() == 0){
                    out.println("Emplty list. <br/>");
                }
                else {
            %>
            <table border="1px solid" style="border-collapse: collapse">
                <tr>
                    <th>TransitPointID</th>
                    <th>Kind</th>
                    <th>Name</th>
                    <th>Location</th>
                    <th>Action</th>
                </tr>
                <%
                for (TransitPoint e : liste){
                %>
                <tr>
                    <td><%=e.getTransitPointID()%></td>
                    <td><%=e.getKind()%></td>
                    <td><%=e.getName()%></td>
                    <td><%=e.getLocation() %></td>
                    <td>
                        <a  style="    width: 70px; height: 15px;text-align: center;"class="button"   href="edittransitpoint?tID=<%=e.getTransitPointID()%>">Edit</a> &nbsp;&nbsp;
                        <a  style="    width: 70px; height: 15px;text-align: center;" class="button"  href="#" onclick="doDelete('<%=e.getTransitPointID() %>')">Delete</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </table><br/>
            <a href="addtransitpoint"><input type="submit"class="button"  value="Add TransitPoint"></a>
                <% 
                }
                %>
                 <h3 style="color: red">${msg}</h3>
        <c:choose>
            <c:when test="${sessionScope.account.getRole().equals('Admin')}">
                <a href="admin"><inputclass="button"  type="submit" value="BACK"></a><br/><br/>
                </c:when>
                <c:otherwise>
                <a href="Employee.jsp"><inputclass="button"  type="submit" value="BACK"></a><br/><br/>
                </c:otherwise>
            </c:choose>
        </div>
   
    </body>
</html>
