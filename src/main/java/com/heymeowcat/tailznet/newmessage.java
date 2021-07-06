/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "newmessage", urlPatterns = {"/newmessage"})
public class newmessage extends HttpServlet {

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
            int uid = 0;
            int muid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                muid = Integer.parseInt(request.getParameter("muid"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/muid");
                return;
            }
            String msg = request.getParameter("msg");
            String src = request.getParameter("src");

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            boolean hasMsg = msg != null && !msg.isEmpty();
            boolean hasSrc = src != null && !src.equals("undefined");

            if (!hasMsg && !hasSrc) {
                // Nothing to send
            } else if (hasMsg && !hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (chat_text, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, 1, 0)");
                ps.setInt(1, uid);
                ps.setInt(2, muid);
                ps.executeUpdate();
            } else if (!hasMsg && hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (src, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, 1, 0)");
                ps.setString(1, src);
                ps.setInt(2, uid);
                ps.setInt(3, muid);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (chat_text, src, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, ?, 1, 0)");
                ps.setString(1, msg);
                ps.setString(2, src);
                ps.setInt(3, uid);
                ps.setInt(4, muid);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing message");
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
