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
import org.apache.commons.codec.digest.DigestUtils;


/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "laststep", urlPatterns = {"/laststep"})
public class laststep extends HttpServlet {

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
            String email = request.getParameter("email");
            String hash = request.getParameter("hash");
            String usn = request.getParameter("usn");
            String pass = DigestUtils.md5Hex(request.getParameter("psn"));
            String conpass = DigestUtils.md5Hex(request.getParameter("conpsn"));
            int uid;
            boolean exist = false;
            ResultSet rs2 = DB.search("SELECT username FROM `user_login` where username='" + usn + "' ");
            if (rs2.next()) {
                exist=false;
            } else if (!rs2.isBeforeFirst()) {
                exist=true;
            }
            if (pass.equals(conpass) && exist){
                ResultSet rs = DB.search("select idusers from users where email='" + email + "' and hash='" + hash + "' and status='1'");
                if (rs.next()) {
                    uid = rs.getInt(1);
                    DB.iud("INSERT INTO `user_login` (`username`, `password`, `users_idusers`) VALUES ('" + usn + "', '" + conpass + "', '" + uid + "');");
                    response.sendRedirect("firstupdateprofile.jsp?uid=" + uid);
                }
            } else {
                response.sendRedirect("registerdetails.jsp?mail=" + email + "&hash=" + hash);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
