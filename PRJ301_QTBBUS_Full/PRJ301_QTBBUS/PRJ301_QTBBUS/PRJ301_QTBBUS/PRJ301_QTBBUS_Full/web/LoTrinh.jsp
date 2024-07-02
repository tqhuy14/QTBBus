<%@page import="Dal.DAO"%>
<%@page import="Model.RouteTransitPoint"%>
<%@page import="java.util.List"%>
<%@page import="Model.Bus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>QTBBus - Lộ Trình</title>
    <style>
        body {
            background-image: url('img/update.jpg');
            background-size: cover;
            background-repeat: no-repeat;
             background-attachment: fixed;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            font-size: 36px;
            margin-top: 50px;
        }

        table {
            width: 70%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            transform: scale(1.1);
        }

        th, td {
            padding: 15px 20px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        button {
            display: block;
            margin: 30px auto;
            padding: 20px 40px;
            border: none;
            border-radius: 8px;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            font-size: 20px;
            transition: background-color 0.3s;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            transform: scale(1.1);
        }

        button:hover {
            background-color: #0056b3;
        }
        img{
              width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            transform: scale(1.1);
        }
    </style>
</head>
<body>
    <h1>Lộ Trình Điều Hành</h1>
    <div  style="display: flex;">
        <div style="  flex: 60%;">
        <table>
        <thead>
            <tr>
                <th>Điểm Dừng</th>
                <th>Thời Gian Đến (Dự Kiến)</th>
            </tr>
        </thead>
        <tbody>
            <%
                DAO dao = new DAO();
                if (request.getAttribute("listRouteTransitPoint") != null) {
                    List<RouteTransitPoint> listRouteTransitPoint = (List<RouteTransitPoint>) request.getAttribute("listRouteTransitPoint");
                    if (listRouteTransitPoint.get(0).getArrivalTime().compareTo(listRouteTransitPoint.get(1).getArrivalTime()) < 0) {
                        for (int i = 0; i < listRouteTransitPoint.size(); i++) {
            %>
            <tr>
                <td><%=dao.getTransitPointByID(listRouteTransitPoint.get(i).getTransitPointID()).getName() %></td>
                <td><%=listRouteTransitPoint.get(i).getArrivalTime()%></td>
            </tr>
            <%
                }
            } else {
                for (int i = listRouteTransitPoint.size()-1; i >= 0; i--) {
            %>
            <tr>
                <td><%=dao.getTransitPointByID(listRouteTransitPoint.get(i).getTransitPointID()).getName()%></td>
                <td><%=listRouteTransitPoint.get(i).getArrivalTime()%></td>
            </tr>
            <%
                        }
                    }
                }
            %>
        </tbody>
    </table>
    </div >
    
    <div style="  flex: 40%; margin-right: 100px">
            <%
            if(request.getAttribute("b")!=null){
                Bus b = (Bus) request.getAttribute("b");
                if(b.getBusID()==1){
                %>
                <img  src="img/MyDinh-GiapBat.png" style="margin-left: 30%">
                <%
            }
                if(b.getBusID()==2){
                %>
                <img src="img/MyDinh-NuocNgam.png" style="margin-left: 30%">
                <%
               }if(b.getBusID()==3){
                %>
                <img src="img/hcm.png" style="margin-left: 30%">
                <%
                }
            }
        %>
        
        </div>
    </div>
    
    <div style="text-align: center;">
        <button><a href="TransitPointServlet" style="color: #fff;">Trang Chủ</a></button>
    </div>
        
</body>
</html>
