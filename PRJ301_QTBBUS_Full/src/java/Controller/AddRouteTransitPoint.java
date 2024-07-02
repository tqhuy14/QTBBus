/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.DAO;
import Model.RouteTransitPoint;
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
@WebServlet(name="AddRouteTransitPoint", urlPatterns={"/addroutetransitpoint"})
public class AddRouteTransitPoint extends HttpServlet {
   
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
            out.println("<title>Servlet AddRouteTransitPoint</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRouteTransitPoint at " + request.getContextPath () + "</h1>");
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
        try {
            DAO dao = new DAO();
            int n = Integer.parseInt(request.getParameter("numt"));
            int rid = Integer.parseInt(request.getParameter("rid"));
            ArrayList<TransitPoint> list = dao.getAllTransitPoint();
            request.setAttribute("num", n);
            request.setAttribute("rid", rid);
            request.setAttribute("list", list);
            ArrayList<RouteTransitPoint> listtp = dao.getRouteTranstPointByRouteID(rid);     
            request.setAttribute("list", list); 
            request.setAttribute("listtp", listtp); 
            request.getRequestDispatcher("AddRouteTransitPoint.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
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
        int rid=-1;
        try {
            DAO dao = new DAO();
            int n = Integer.parseInt(request.getParameter("numt"));
            System.out.println(n);
            rid = Integer.parseInt(request.getParameter("rid"));
            System.out.println(rid);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            ArrayList<RouteTransitPoint> listtp = dao.getRouteTranstPointByRouteID(rid);     
            for (int i = 0+listtp.size()-2; i < n+listtp.size()-2; i++) {
                int tid = Integer.parseInt(request.getParameter("op"+i));
                System.out.println(tid);
                String artime = request.getParameter("artime"+i);
                System.out.println(artime);
                Date datef = sdf.parse(artime);
                Time timear = new Time(datef.getTime());
                RouteTransitPoint rtp = new RouteTransitPoint(rid, tid, i+2, timear);
                dao.insertRouteTransitPoint(rtp);
            }
            response.sendRedirect("routetranstpoint?tID="+rid);
        } catch (Exception e) {
            
            response.sendRedirect("routetranstpoint?tID="+rid);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
