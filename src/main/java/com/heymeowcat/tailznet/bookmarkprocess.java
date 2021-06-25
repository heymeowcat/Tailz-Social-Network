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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "bookmarkprocess", urlPatterns = {"/bookmarkprocess"})
public class bookmarkprocess extends HttpServlet {

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
            int x = Integer.parseInt(request.getParameter("x"));

            PreparedStatement chk = DB.prepare(
                    "SELECT post_idpost FROM user_bookmarks WHERE users_idusers=? AND post_idpost=?");
            chk.setInt(1, uid);
            chk.setInt(2, x);
            ResultSet likechech = chk.executeQuery();

            if (!likechech.isBeforeFirst()) {
                PreparedStatement ins = DB.prepare(
                        "INSERT INTO user_bookmarks (notice_time, users_idusers, post_idpost) VALUES (CURRENT_TIMESTAMP, ?, ?)");
                ins.setInt(1, uid);
                ins.setInt(2, x);
                ins.executeUpdate();
                out.write("bookmark");
            } else if (likechech.next()) {
                PreparedStatement del = DB.prepare(
                        "DELETE FROM user_bookmarks WHERE post_idpost=? AND users_idusers=?");
                del.setInt(1, x);
                del.setInt(2, uid);
                del.executeUpdate();
                out.write("bookmark_border");
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
