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
@WebServlet(name = "embedpost", urlPatterns = {"/embedpost"})
public class embedpost extends HttpServlet {

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
            int uid = 0;
            uid = Integer.parseInt(request.getSession().getAttribute("user").toString());

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String fp = request.getParameter("link");

            int privacy = 1;
            java.sql.ResultSet privacyrs = DB.search("SELECT `privacy_name` FROM user_privacy WHERE users_idusers='" + uid + "' ");
            if (privacyrs.next()) {
                if (privacyrs.getString(1).equals("private")) {
                    privacy = 2;
                }
            }

            if (fp != null) {
                DB.iud("INSERT INTO `post` ( `post_heading`, `post_img_area`, `post_detial`, `post_time`, `users_idusers`, `post_type_idpost_type`,Post_Privacy) VALUES ( '" + ENCDEC.encrypt(title, new KEY().secretKey) + "', '" + ENCDEC.encrypt(fp, new KEY().secretKey) + "','" + ENCDEC.encrypt(description, new KEY().secretKey) + "', CURRENT_TIMESTAMP, '" + uid + "', '2','"+privacy+"');");
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("index.jsp");
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
