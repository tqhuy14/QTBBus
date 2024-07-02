<%@page import="java.util.List"%>
<%@page import="Model.BusRoute"%>
<%@page import="Model.Bus"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>QTBBus - Kết quả tìm kiếm</title>
    <style>
        body {
                  background-image: url('img/update.jpg');
            background-size: cover;
      
          
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 80%; 
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
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
            margin: 20px auto;
            padding: 15px 30px; 
            border: none;
            border-radius: 8px; 
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            font-size: 18px;
            transition: background-color 0.3s; 
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Kết quả tìm kiếm!</h1>

    <table>
        <thead>
            <tr>
                <th>BusNumber</th>
                <th>StartTime</th>
                <th>EndTime</th>
                <th>Frequency</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (request.getAttribute("ListBus") != null) {
                    List<Bus> Listbus = (List<Bus>) request.getAttribute("ListBus");
                    int temp=0;
                    for (int j=0; j<Listbus.size(); j++) {
            %>
            <tr style="background:  white">
                <td><a href="LoTrinh?RouteID=<%= Listbus.get(j).getBusRoute()%>"><%= Listbus.get(j).getBusNumber()%></a></td>

                <%
                    if (request.getAttribute("ListbusRoute") != null) {
                        List<BusRoute> ListbusRoute = (List<BusRoute>) request.getAttribute("ListbusRoute");
                        for (int i=0; i<ListbusRoute.size(); i++) {
                        temp++;
                %>
                <%
                if(temp==1){
                %>

                <td><%=ListbusRoute.get(i).getStartTime()%></td>
                <td><%=ListbusRoute.get(i).getEndTime()%></td>
                <td><%=ListbusRoute.get(i).getFrequency()%></td>
            </tr>
            <tr>
            <%
                }
                   if(temp!=1){
                %>
                <td><%=ListbusRoute.get(temp-1).getStartTime()%></td>
                <td><%=ListbusRoute.get(temp-1).getEndTime()%></td>
                <td><%=ListbusRoute.get(temp-1).getFrequency()%></td>
            </tr>
            <%
                } 
            
            break;
            }
                   }
                    }
                }
            %>
        </tbody>
    </table>
    
    <div style="text-align: center;">
        <button><a href="TransitPointServlet" style="color: #fff;">HOME</a></button>
    </div>
        
</body>
</html>
