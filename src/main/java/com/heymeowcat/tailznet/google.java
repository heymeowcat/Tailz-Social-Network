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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "google", urlPatterns = {"/google"})
public class google extends HttpServlet {

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
            String id = DigestUtils.md5Hex(request.getParameter("id"));
            String fullname = request.getParameter("name");
            String[] nameparts = fullname.split("\\s+");
            String profilepic = request.getParameter("profilepic");
            String newemail = request.getParameter("email");
            java.sql.ResultSet regrs = DB.search("Select email from `users` where email= '" + newemail + "' and status='1' ");
            if (!regrs.isBeforeFirst()) {
                DB.iud("INSERT INTO `users` (`email`, `status`, `hash`, `user_type_iduser_type`) VALUES ('" + newemail + "', '1', '" + id + "', '2');");
                java.sql.ResultSet regidrs = DB.search("Select idusers from `users` where email= '" + newemail + "' and status='1' ");
                if (regidrs.next()) {
                    int uid = regidrs.getInt(1);
                    DB.iud("UPDATE `users` SET `firstname` = '" + nameparts[0] + "', `lastname` = '" + nameparts[1] + "' WHERE `users`.`idusers` = '" + uid + "';");
                    DB.iud("INSERT INTO `user_profile_pic` (`image`, `users_idusers`) VALUES ('" + profilepic + "?sz=180', '" + uid + "');");
                    DB.iud("INSERT INTO `app_theme` (`themename`, `users_idusers`) VALUES ('purplelight', '" + uid + "');");
                    DB.iud("INSERT INTO `app_layout` (`users_idusers`, `layout`) VALUES ('" + uid + "', 1);");
                    DB.iud("INSERT INTO `uap` (`Preference`, `users_idusers`) VALUES ('1', '" + uid + "');");
                    DB.iud("INSERT INTO `user_privacy` (`privacy_name`, `users_idusers`) VALUES ('public', '" + uid + "');");
                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", uid);
                    out.write("<div class='fixed-action-btn'>");
                    out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                    out.write("<i class='large material-icons black-text'>skip_next</i>");
                    out.write("</a>");
                    out.write("</div>");
                }
            } else {
                java.sql.ResultSet regidrs = DB.search("Select idusers from `users` where email= '" + newemail + "' and status='1' ");
                if (regidrs.next()) {
                    int uid = regidrs.getInt(1);
                    DB.iud("UPDATE `user_profile_pic` SET `image` = '" + profilepic + "?sz=180' WHERE `user_profile_pic`.`users_idusers` = '"+uid+"';");
                    DB.iud("UPDATE `users` SET `firstname` = '" + nameparts[0] + "', `lastname` = '" + nameparts[1] + "' WHERE `users`.`idusers` = '"+uid+"';");
                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", uid);
                    out.write("<div class='fixed-action-btn'>");
                    out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                    out.write("<i class='large material-icons black-text'>skip_next</i>");
                    out.write("</a>");
                    out.write("</div>");
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
