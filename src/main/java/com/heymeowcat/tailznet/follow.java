package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "follow", urlPatterns = {"/follow"})
public class follow extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int sender = 0;
            int receiver = 0;
            try {
                sender = Integer.parseInt(request.getParameter("x"));
                receiver = Integer.parseInt(request.getParameter("y"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }

            PreparedStatement ins = DB.prepare(
                    "INSERT INTO follow (sender, receiver) VALUES (?, ?)");
            ins.setInt(1, sender);
            ins.setInt(2, receiver);
            ins.executeUpdate();

            PreparedStatement notif = DB.prepare(
                    "INSERT INTO notification (notificationfor, notificationfrom, `notification-type`, status, target) VALUES (?, ?, '1', '0', '0')");
            notif.setInt(1, receiver);
            notif.setInt(2, sender);
            notif.executeUpdate();

            out.write("followed");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error following user");
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
