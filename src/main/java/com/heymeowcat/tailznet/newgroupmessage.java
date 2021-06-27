/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "newgroupmessage", urlPatterns = {"/newgroupmessage"})
public class newgroupmessage extends HttpServlet {

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
            int uid = Integer.parseInt(request.getParameter("uid"));
            String muid = request.getParameter("muid");
            String msg = request.getParameter("msg");
            String src = request.getParameter("src");

            boolean hasMsg = msg != null && !msg.isEmpty();
            boolean hasSrc = src != null && !src.equals("undefined");

            if (!hasMsg && !hasSrc) {
                // Nothing to send
            } else if (hasMsg && !hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO group_chat (Groups_group_id, chat_text, users_idusers, chatstatus) VALUES (?, ?, ?, 0)");
                ps.setString(1, muid);
                ps.setString(2, ENCDEC.encrypt(msg, new KEY().secretKey));
                ps.setInt(3, uid);
                ps.executeUpdate();
            } else if (!hasMsg && hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO group_chat (Groups_group_id, src, users_idusers, chatstatus) VALUES (?, ?, ?, 0)");
                ps.setString(1, muid);
                ps.setString(2, ENCDEC.encrypt(src, new KEY().secretKey));
                ps.setInt(3, uid);
                ps.executeUpdate();
            } else {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO group_chat (Groups_group_id, chat_text, src, users_idusers, chatstatus) VALUES (?, ?, ?, ?, 0)");
                ps.setString(1, muid);
                ps.setString(2, ENCDEC.encrypt(msg, new KEY().secretKey));
                ps.setString(3, ENCDEC.encrypt(src, new KEY().secretKey));
                ps.setInt(4, uid);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
