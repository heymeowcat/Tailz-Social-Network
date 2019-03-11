/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "themechange", urlPatterns = {"/themechange"})
public class themechange extends HttpServlet {

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
            String x = request.getParameter("x");
            ResultSet likechech = DB.search("Select themename from `app_theme`  where users_idusers='" + uid + "' ");
            if (!likechech.isBeforeFirst()) {
                DB.iud("INSERT INTO `app_theme` ( `themename`, `users_idusers`) VALUES ('light', '" + uid + "')");
            } else if (likechech.next()) {
                if (x.equals("pinklight")) {
                    DB.iud("Update `app_theme` SET themename='pinklight'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("pinkdark")) {
                    DB.iud("Update `app_theme` SET themename='pinkdark'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("bluelight")) {
                    DB.iud("Update `app_theme` SET themename='bluelight'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("bluedark")) {
                    DB.iud("Update `app_theme` SET themename='bluedark'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("yellowlight")) {
                    DB.iud("Update `app_theme` SET themename='yellowlight'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("yellowdark")) {
                    DB.iud("Update `app_theme` SET themename='yellowdark'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("greenlight")) {
                    DB.iud("Update `app_theme` SET themename='greenlight'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("greendark")) {
                    DB.iud("Update `app_theme` SET themename='greendark'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("purplelight")) {
                    DB.iud("Update `app_theme` SET themename='purplelight'  WHERE `users_idusers` ='" + uid + "' ");
                } else if (x.equals("purpledark")) {
                    DB.iud("Update `app_theme` SET themename='purpledark'  WHERE `users_idusers` ='" + uid + "' ");
                }

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
