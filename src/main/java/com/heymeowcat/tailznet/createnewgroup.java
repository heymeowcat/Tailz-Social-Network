package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "createnewgroup", urlPatterns = {"/createnewgroup"})
public class createnewgroup extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String groupname = request.getParameter("title");
            String groupimg = request.getParameter("fp");
            int uid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
                return;
            }
            if (groupname == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing group name");
                return;
            }
            String groupid = UUID.randomUUID().toString();
            if (!groupname.isEmpty()) {
                String imgToUse = "undefined".equals(groupimg) ? "img/ion-android-people.png" : (groupimg != null ? groupimg : "img/ion-android-people.png");

                PreparedStatement insGroup = DB.prepare(
                        "INSERT INTO groups (group_id, groupname, groupimg, groupadmin) VALUES (?, ?, ?, ?)");
                insGroup.setString(1, groupid);
                insGroup.setString(2, groupname);
                insGroup.setString(3, imgToUse);
                insGroup.setInt(4, uid);
                insGroup.executeUpdate();

                PreparedStatement insMember = DB.prepare(
                        "INSERT INTO group_members (Groups_group_id, members) VALUES (?, ?)");
                insMember.setString(1, groupid);
                insMember.setInt(2, uid);
                insMember.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating group");
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
