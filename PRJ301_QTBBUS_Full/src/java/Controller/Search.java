/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.DAO;
import Model.Bus;
import Model.BusRoute;
import Model.RouteTransitPoint;
import Model.TransitPoint;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author V
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        String[] Location = request.getParameterValues("Location");
        String startPoint = request.getParameter("startPoint");
        String endPoint = request.getParameter("endPoint");
        DAO dao = new DAO();
        String temp = "a";
        for (String str : Location) {
            if (str.equalsIgnoreCase("TP Hà Nội")) {
                temp = "TP Hà Nội";
                break;
            } else {
                temp = "TP Hồ Chí Minh";
                break;
            }
        }

        int a = 0, b = 0;

        List<String> uniqueValuesList = new ArrayList<>();
        List<TransitPoint> list = dao.getTransitPointByLocation(temp);
        for (TransitPoint transitPoint : list) {
            if (transitPoint.getName().equalsIgnoreCase(startPoint.trim())
                    && !uniqueValuesList.contains(startPoint.trim())) {
                a = transitPoint.getTransitPointID();
                uniqueValuesList.add(startPoint.trim());
            }
            if (transitPoint.getName().equalsIgnoreCase(endPoint.trim())
                    && !uniqueValuesList.contains(endPoint.trim())) {
                b = transitPoint.getTransitPointID();
                uniqueValuesList.add(endPoint.trim());
            }
//           
        }
//        out.print(a);
//        out.print(b);

        List<Bus> listBus = new ArrayList<>();
        List<BusRoute> ListbusRoute = dao.getBusRouteByLocation(a, b);
        for (BusRoute bus : ListbusRoute) {
            Bus bs = dao.getBusByRouteID(bus.getRouteID());
            listBus.add(bs);
        }

        String er = "Không tìm thấy tuyến đường";

        if (ListbusRoute.isEmpty()) {
            List<RouteTransitPoint> ListRTP1 = dao.getRouteTransitPointByTransitPointID(a);
            List<RouteTransitPoint> ListRTP2 = dao.getRouteTransitPointByTransitPointID(b);
            if (ListRTP1 == null || ListRTP2 == null) {
                request.setAttribute("error", er);
                request.getRequestDispatcher("TransitPointServlet").forward(request, response);
            }
            int count = 0;
            //////co loi
            for (RouteTransitPoint RTP1 : ListRTP1) {
                for (RouteTransitPoint RTP2 : ListRTP2) {
                    if (RTP1.getRouteID() == RTP2.getRouteID()
                            && (RTP1.getArrivalTime().compareTo(RTP2.getArrivalTime()) < 0)
                            && a < b) {
                        BusRoute br = dao.getBusRouteByRouteID(RTP1.getRouteID());
                        ListbusRoute.add(br);
                        count++;
                    }
                    if (RTP1.getRouteID() == RTP2.getRouteID()
                            && (RTP1.getArrivalTime().compareTo(RTP2.getArrivalTime()) < 0)
                            && a > b) {
                        BusRoute br = dao.getBusRouteByRouteID(RTP1.getRouteID());
                        ListbusRoute.add(br);
                        count++;

                    }
                  if(count!=0){
                      break;
                  }
                }

            }
            //////
            for (BusRoute bus : ListbusRoute) {
                Bus bs = dao.getBusByRouteID(bus.getRouteID());
                listBus.add(bs);
            }
        }
       
        if (listBus.isEmpty() || ListbusRoute.isEmpty()) {
            request.setAttribute("error", er);
            request.getRequestDispatcher("TransitPointServlet").forward(request, response);
        } else {
            request.setAttribute("ListBus", listBus);
           
            request.setAttribute("ListbusRoute", ListbusRoute);
            request.getRequestDispatcher("ResultSearch.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
