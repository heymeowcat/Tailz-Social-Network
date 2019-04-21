package com.heymeowcat.tailznet;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HEYMEOWCAT
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String newemail = request.getParameter("newemail");
            Random random = new Random();
            random.nextInt(999999);
            String myhash = DigestUtils.md5Hex("" + random);
            java.sql.ResultSet regrs = DB.search("Select email from `users` where email= '" + newemail + "' and status='1' ");
            java.sql.ResultSet regrss = DB.search("Select email from `users` where email= '" + newemail + "'");
            if (!regrs.isBeforeFirst()) {
                SendingEmail se = new SendingEmail();
                se.sendMail(newemail, myhash);
                if (!regrss.isBeforeFirst()) {
                    DB.iud("INSERT INTO `users` (`email`, `status`, `hash`, `user_type_iduser_type`) VALUES ('" + newemail + "', '0', '" + myhash + "', '2');");
                }
                response.sendRedirect("verify.jsp");
            } else {
                response.sendRedirect("login-register.jsp");
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
