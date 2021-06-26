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

            PreparedStatement chk = DB.prepare(
                    "SELECT themename FROM app_theme WHERE users_idusers=?");
            chk.setInt(1, uid);
            ResultSet likechech = chk.executeQuery();

            if (!likechech.isBeforeFirst()) {
                PreparedStatement ins = DB.prepare(
                        "INSERT INTO app_theme (themename, users_idusers) VALUES ('light', ?)");
                ins.setInt(1, uid);
                ins.executeUpdate();
            } else if (likechech.next()) {
                // Only allow known theme names to prevent injection
                String[] validThemes = {"pinklight", "pinkdark", "bluelight", "bluedark",
                    "yellowlight", "yellowdark", "greenlight", "greendark", "purplelight", "purpledark"};
                boolean valid = false;
                for (String t : validThemes) {
                    if (t.equals(x)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    PreparedStatement upd = DB.prepare(
                            "UPDATE app_theme SET themename=? WHERE users_idusers=?");
                    upd.setString(1, x);
                    upd.setInt(2, uid);
                    upd.executeUpdate();
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
