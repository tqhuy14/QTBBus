<%-- 
    Document   : AddTransitPoint
    Created on : Mar 6, 2024, 7:49:40 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add new TransitPoint</h1>
        <form action="<%=request.getContextPath()%>/addtransitpoint" method="post">
            TransitPointID: <input type="number" name="eid" value="${param.eid}" required/><br/>
            Kind: 
            <select name="kind" >
                <option value="Bến xe" ${param.kind.equalsIgnoreCase("Bến xe")?"selected":""}>Bến xe</option>
                <option value="Điểm dừng"  ${param.kind.equalsIgnoreCase("Điểm dừng")?"selected":""}>Điểm dừng</option>
            </select><br/>
            Name: <input type="text" name="name" value="${param.name}" required/><br/>
            
            Location: 
            <select name="loca" >
                <option value="TP Hà Nội" ${param.loca.equalsIgnoreCase("TP Hà Nội")?"selected":""}>TP Hà Nội</option>
                <option value="TP Hồ Chí Minh"  ${param.loca.equalsIgnoreCase("TP Hồ Chí Minh")?"selected":""}>TP Hồ Chí Minh</option>
            </select><br/>
            <input type="submit" value="Add TransitPoint"/><br/>
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
