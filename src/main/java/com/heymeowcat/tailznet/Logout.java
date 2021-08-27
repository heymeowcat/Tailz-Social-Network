/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HEYMEOWCAT
 */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Integer uid = null;
            if (request.getSession().getAttribute("user") != null) {
                try {
                    uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user session");
                    return;
                }

                PreparedStatement ps = DB.prepare(
                        "SELECT idlogin_sessions FROM login_sessions "
                        + "WHERE user_login_iduser_login=(SELECT iduser_login FROM user_login WHERE users_idusers=? ORDER BY idlogin_sessions DESC LIMIT 1) "
                        + "ORDER BY idlogin_sessions DESC LIMIT 1");
                ps.setInt(1, uid);
                ResultSet themers = ps.executeQuery();

                if (themers.next()) {
                    PreparedStatement upd = DB.prepare(
                            "UPDATE login_sessions SET out_time=CURRENT_TIMESTAMP WHERE idlogin_sessions=?");
                    upd.setInt(1, themers.getInt(1));
                    upd.executeUpdate();
                }
            }

            HttpSession ses = request.getSession();
            ses.invalidate();

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("MEOWID")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
            }

            response.sendRedirect("login-register.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during logout");
            } catch (IOException e) {
                e.printStackTrace();
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
