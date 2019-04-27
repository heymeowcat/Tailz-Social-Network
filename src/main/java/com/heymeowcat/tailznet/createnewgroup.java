/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "createnewgroup", urlPatterns = {"/createnewgroup"})
public class createnewgroup extends HttpServlet {

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
            String groupname = request.getParameter("title");
            String groupimg = request.getParameter("fp");
            int uid = Integer.parseInt(request.getParameter("uid"));
            String groupid = UUID.randomUUID().toString();
            if (!groupname.isEmpty()) {
                if (groupimg.equals("undefined")) {
                    DB.iud("INSERT INTO `groups` (`group_id`, `groupname`, `groupimg`, `groupadmin`) VALUES ('" + groupid + "', '" + groupname + "', 'https://imgplaceholder.com/420x420/cccccc/757575/ion-android-people', '" + uid + "');");
                    DB.iud("INSERT INTO `group_members` (`Groups_group_id`, `members`) VALUES ('" + groupid + "', '" + uid + "');");
                } else {
                    DB.iud("INSERT INTO `groups` (`group_id`, `groupname`, `groupimg`, `groupadmin`) VALUES ('" + groupid + "', '" + groupname + "', '" + groupimg + "', '" + uid + "');");
                    DB.iud("INSERT INTO `group_members` (`Groups_group_id`, `members`) VALUES ('" + groupid + "', '" + uid + "');");
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
