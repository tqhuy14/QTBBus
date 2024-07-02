<%-- 
    Document   : Admin
    Created on : Feb 29, 2024, 2:42:04 PM
    Author     : V
--%>

<%@page import="Model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List category</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("Are you sure to delete employee with id = " + id + "?")) {
                    window.location = "deleteemployee?eID=" + id;
                }
            }
        </script>
    </head>
    <body>
        <c:choose>
            <c:when test="${empty sessionScope.liste}">
                <%@include file="Welcome.jsp" %>
            </c:when>
            <c:otherwise>
                <a href="Welcome.jsp"><input type="submit" value="Home"></a><br/><br/>
                <div style="display: flex; justify-content: space-around">
                    <div>
                        <h1>List of Employees</h1>
                        <%
                            ArrayList<User> liste = (ArrayList<User>)session.getAttribute("liste");
                            if (liste==null || liste.size() == 0){
                                out.println("Emplty list. <br/>");
                            }
                            else {
                        %>
                        <table border="1px solid" style="border-collapse: collapse">
                            <tr>
                                <th>ID</th>
                                <th>UserName</th>
                                <th>Password</th>
                                <th>Action</th>
                            </tr>
                            <%
                            for (User e : liste){
                            %>
                            <tr>
                                <td><%=e.getUserID()%></td>
                                <td><%=e.getUsername()%></td>
                                <td><%=e.getPassword()%></td>
                                <td>
                                    <a href="editemployee?eID=<%=e.getUserID()%>">Edit</a> &nbsp;&nbsp;
                                    <a href="#" onclick="doDelete('<%=e.getUserID()%>')">Delete</a>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </table><br/>
                        <a href="AddEmployee.jsp"><input type="submit" value="Add Employee"></a>
                            <% 
                            }
                            %>
                    </div>
                    <div>
                        <h1>Manager Information</h1>
                        <a href="manager_bus?key=a"><input type="submit" value="Manager Bus"></a><br/><br/>
                        <a href="manager_transitpoint?key=a"><input type="submit" value="Manager TransitPoint"></a><br/><br/>
                        <a href="manager_busroute?key=a"><input type="submit" value="Manager BusRoute"></a>

                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
