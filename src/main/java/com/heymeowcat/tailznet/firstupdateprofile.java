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
@WebServlet(name = "firstupdateprofile", urlPatterns = {"/firstupdateprofile"})
public class firstupdateprofile extends HttpServlet {

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
            try {
                String uid = request.getParameter("uid");
                String fn = request.getParameter("fn");
                String ln = request.getParameter("ln");
                String fp = "img/Profile_avatar_placeholder_large.png";
                DB.iud("UPDATE `users` SET `firstname` = '" + fn + "', `lastname` = '" + ln + "' WHERE `users`.`idusers` = '" + uid + "';");
                DB.iud("INSERT INTO `user_profile_pic` (`image`, `users_idusers`) VALUES ('" + fp + "', '" + uid + "');");
                DB.iud("INSERT INTO `app_theme` (`themename`, `users_idusers`) VALUES ('purplelight', '" + uid + "');");
                DB.iud("INSERT INTO `app_layout` (`users_idusers`, `layout`) VALUES ('" + uid + "', 1);");
                DB.iud("INSERT INTO `uap` (`Preference`, `users_idusers`) VALUES ('1', '" + uid + "');");
                DB.iud("INSERT INTO `user_privacy` (`privacy_name`, `users_idusers`) VALUES ('public', '" + uid + "');");
                response.sendRedirect("login-register.jsp");
            } catch (Exception e) {
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
