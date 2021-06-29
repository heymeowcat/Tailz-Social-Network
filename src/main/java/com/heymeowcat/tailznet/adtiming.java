package com.heymeowcat.tailznet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@WebServlet(name = "adtiming", urlPatterns = {"/adtiming"})
public class adtiming extends HttpServlet {

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
            int adid = Integer.parseInt(request.getParameter("adid"));
            PreparedStatement upd = DB.prepare(
                    "UPDATE ads SET status='4' WHERE Adid=?");
            upd.setInt(1, adid);
            upd.executeUpdate();

            int forhowmanyhours = 0;
            PreparedStatement gethoursPs = DB.prepare(
                    "SELECT forhowmanyhours FROM ads WHERE Adid=?");
            gethoursPs.setInt(1, adid);
            ResultSet gethoursrs = gethoursPs.executeQuery();
            if (gethoursrs.next()) {
                forhowmanyhours = gethoursrs.getInt(1);
            }
            String interval = forhowmanyhours + ":0:0";
            PreparedStatement ins = DB.prepare(
                    "INSERT INTO adtiming (Ads_Adid, adstartedtime, adendtime) VALUES (?, CURRENT_TIMESTAMP, ADDTIME(CURRENT_TIMESTAMP, ?))");
            ins.setInt(1, adid);
            ins.setString(2, interval);
            ins.executeUpdate();
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
