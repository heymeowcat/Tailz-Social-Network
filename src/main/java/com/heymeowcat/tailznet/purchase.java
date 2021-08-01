package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "purchase", urlPatterns = {"/purchase"})
public class purchase extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int spaces = 0;
            int hours = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                spaces = Integer.parseInt(request.getParameter("spaces"));
                hours = Integer.parseInt(request.getParameter("hours"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }

            double rate = 0;
            int usersinsystem=0;
            PreparedStatement ratersPs = DB.prepare("Select idAPPHPI from apphpi");
            ResultSet raters = ratersPs.executeQuery();
            if (raters.next()) {
                rate = Double.parseDouble(raters.getString(1));
            }
            PreparedStatement usinsysrsPs = DB.prepare("Select count(email) from users where status='1' and user_type_iduser_type='2'");
            ResultSet usinsysrs = usinsysrsPs.executeQuery();
            if (usinsysrs.next()) {
                usersinsystem = Integer.parseInt(usinsysrs.getString(1));
            }
            int total = (int) (rate * hours);
            for (int i = 0; i < spaces; i++) {
                PreparedStatement purIns = DB.prepare("INSERT INTO `purchase_history` (`users_idusers`, `rate`, `total`, `status`,`hours`) VALUES (?, ?, ?, '1', ?)");
                purIns.setInt(1, uid);
                purIns.setDouble(2, rate);
                purIns.setInt(3, total);
                purIns.setInt(4, hours);
                purIns.executeUpdate();
                PreparedStatement adsIns = DB.prepare("INSERT INTO `ads` (`users_idusers`, `forhowmanyusers`,`forhowmanyhours`,`status`) VALUES (?, ?, ?, '2' )");
                adsIns.setInt(1, uid);
                adsIns.setInt(2, usersinsystem);
                adsIns.setInt(3, hours);
                adsIns.executeUpdate();
            }
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing purchase");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
        processRequest(request, response);
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
