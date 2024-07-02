/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.DAO;
import Model.BusRoute;
import Model.TransitPoint;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name="EditBusRoute", urlPatterns={"/editbusroute"})
public class EditBusRoute extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditBusRoute</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBusRoute at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("tID"));
        BusRoute br = dao.getBusRouteByRouteID(id);
        request.setAttribute("newt", br);
        if(br == null){
            response.sendRedirect(request.getContextPath()+"/manager_busroute");
        }else{
        ArrayList<TransitPoint> tp = dao.getAllTransitPoint();
        request.setAttribute("liste", tp);
        request.getRequestDispatcher("EditBusRoute.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        String id_raw = request.getParameter("eid");
        String ops = request.getParameter("ops");
        String ope = request.getParameter("ope");
        String fre = request.getParameter("fre");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        String name = request.getParameter("name");
        int id, ids, ide;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            id = Integer.parseInt(id_raw);
            ids = Integer.parseInt(ops);
            ide = Integer.parseInt(ope);
            Date datef = sdf.parse(fre);
            Date dates = sdf.parse(stime);
            Date datee = sdf.parse(etime);
            Time timef = new Time(datef.getTime());
            Time times = new Time(dates.getTime());
            Time timee = new Time(datee.getTime());
            
            if (ops.equals(ope)) {
                String er = "StrartPointID and EndPointID cannot overlap";
                request.setAttribute("err", er);
                response.sendRedirect("manager_busroute");
            }else{
                BusRoute br = new BusRoute(id, ids, ide, timef, times, timee,name);
                dao.updateBusRoute(br);
                response.sendRedirect("manager_busroute");
            }

        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("errr", "Please enter the correct format!");
            response.sendRedirect("manager_busroute");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
