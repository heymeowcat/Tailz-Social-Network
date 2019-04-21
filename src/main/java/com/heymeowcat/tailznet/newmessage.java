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
@WebServlet(name = "newmessage", urlPatterns = {"/newmessage"})
public class newmessage extends HttpServlet {

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
            int muid = Integer.parseInt(request.getParameter("muid"));
            String msg = request.getParameter("msg");
            String src = request.getParameter("src");
            if (msg.isEmpty() && src.equals("undefined")) {
            } else if (src.equals("undefined")) {
                DB.iud("INSERT INTO `chat` (`chat_text`,`user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('" + ENCDEC.encrypt(msg, new KEY().secretKey) + "','" + uid + "', '" + muid + "',1,0);");
            } else if (msg.isEmpty()) {
                DB.iud("INSERT INTO `chat` (`src`, `user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('" + ENCDEC.encrypt(src, new KEY().secretKey) + "', '" + uid + "', '" + muid + "',1,0);");
            } else {
                DB.iud("INSERT INTO `chat` (`chat_text`, `src`, `user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES ('" + ENCDEC.encrypt(msg, new KEY().secretKey) + "','" + src + "', '" + uid + "', '" + muid + "',1,0);");
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
