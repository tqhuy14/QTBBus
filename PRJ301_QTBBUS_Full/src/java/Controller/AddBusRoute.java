/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.sql.Time;
import Dal.DAO;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddBusRoute", urlPatterns = {"/addbusroute"})
public class AddBusRoute extends HttpServlet {

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
            out.println("<title>Servlet AddBusRoute</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddBusRoute at " + request.getContextPath() + "</h1>");
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
        DAO dao = new DAO();
        ArrayList<TransitPoint> tp = dao.getAllTransitPoint();
        request.setAttribute("liste", tp);
        request.getRequestDispatcher("AddBusRoute.jsp").forward(request, response);
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
        String id_raw = request.getParameter("brid");
        String ops = request.getParameter("ops");
        String ope = request.getParameter("ope");
        String fre = request.getParameter("fre");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String name = request.getParameter("name");
        int id, ids, ide;
        DAO dao = new DAO();
        String er = "";
        int key = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            id = Integer.parseInt(id_raw);
            ids = Integer.parseInt(ops);
            ide = Integer.parseInt(ope);
            BusRoute u = dao.getBusRouteByRouteID(id);
            Date datef = sdf.parse(fre);
            Date dates = sdf.parse(stime);
            Date datee = sdf.parse(etime);
            Time timef = new Time(datef.getTime());
            Time times = new Time(dates.getTime());
            Time timee = new Time(datee.getTime());
            
            if (u != null) {
                er += "ID already exists ";
                key = 1;
            }

            if (ops.equals(ope)) {
                er += "StrartPointID and EndPointID cannot overlap";
                key = 1;
            }

            if (key == 0) {
                RouteTransitPoint rtps = new RouteTransitPoint(id, ids, 1, times);
                RouteTransitPoint rtpe = new RouteTransitPoint(id, ide, 999, timee);
                dao.insertBusRoute(new BusRoute(id, ids, ide, timef, times, timee,name));
                dao.insertRouteTransitPoint(rtps);
                dao.insertRouteTransitPoint(rtpe);
                response.sendRedirect("manager_busroute");
            } else {
                request.setAttribute("err", er);
                doGet(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("errr", "Please enter the correct format!");
            doGet(request, response);
        }

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
