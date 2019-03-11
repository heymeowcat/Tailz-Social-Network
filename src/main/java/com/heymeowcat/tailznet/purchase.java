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
@WebServlet(name = "purchase", urlPatterns = {"/purchase"})
public class purchase extends HttpServlet {

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
            int spaces = Integer.parseInt(request.getParameter("spaces"));
            int hours = Integer.parseInt(request.getParameter("hours"));
            double rate = 0;
            int usersinsystem=0;
            java.sql.ResultSet raters = DB.search("Select idAPPHPI from apphpi");
            if (raters.next()) {
                rate = Double.parseDouble(raters.getString(1));
            }
            java.sql.ResultSet usinsysrs = DB.search("Select count(email) from users where status='1' and user_type_iduser_type='2'");
            if (usinsysrs.next()) {
                usersinsystem = Integer.parseInt(usinsysrs.getString(1));
            }
            int total = (int) (rate * hours);
            for (int i = 0; i < spaces; i++) {
                DB.iud("INSERT INTO `purchase_history` (`users_idusers`, `rate`, `total`, `status`,`hours`) VALUES ('" + uid + "','" + rate + "','" + total + "','1','" + hours + "');");
                DB.iud("INSERT INTO `ads` (`users_idusers`, `forhowmanyusers`,`forhowmanyhours`,`status`) VALUES ('" + uid + "','" + usersinsystem + "','" + hours + "','2' );");
            }
            response.sendRedirect("dashboard.jsp");
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
