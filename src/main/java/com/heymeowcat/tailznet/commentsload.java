/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "commentsload", urlPatterns = {"/commentsload"})
public class commentsload extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int x = 0;
            int y = 0;
            try {
                x = Integer.parseInt(request.getParameter("x"));
                y = Integer.parseInt(request.getParameter("y"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid x/y parameters");
                return;
            }
            String z = request.getParameter("z");
            String[] themeColors = ThemeHelper.getThemeColors(y);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            PreparedStatement piduidrsPs = DB.prepare("Select users_idusers from post where idpost= ?");
            piduidrsPs.setInt(1, x);
            ResultSet piduidrs = piduidrsPs.executeQuery();
            int piduid = 0;
            if (piduidrs.next()) {
                piduid = piduidrs.getInt(1);
            }
            if (z != null && !z.isEmpty()) {
                PreparedStatement cmntIns = DB.prepare("INSERT INTO `post_comment` ( `comment`, `users_idusers`, `post_idpost`) VALUES (?, ?, ?)");
                cmntIns.setString(1, z);
                cmntIns.setInt(2, y);
                cmntIns.setInt(3, x);
                cmntIns.executeUpdate();
            }
            if (piduid != y) {
                PreparedStatement notifIns = DB.prepare("INSERT INTO `notification` (`notificationfor`, `notificationfrom`,`notification-type`,`status`,`target`) VALUES (?, ?, '2', '0', ?)");
                notifIns.setInt(1, piduid);
                notifIns.setInt(2, y);
                notifIns.setInt(3, x);
                notifIns.executeUpdate();
            }
            PreparedStatement cmntsrsPs = DB.prepare("Select pc.idpost_comment, pc.post_idpost, pc.users_idusers, u.firstname, u.lastname, u.image, pc.datetime, pc.likes FROM post_comment pc JOIN users u ON pc.users_idusers = u.idusers WHERE pc.post_idpost=? ORDER BY pc.datetime DESC");
            cmntsrsPs.setInt(1, x);
            ResultSet cmntsrs = cmntsrsPs.executeQuery();
            if (!cmntsrs.isBeforeFirst()) {
                out.write("<div class='center' style='top:40%; position:relative'><img src ='img/commentlive.png' class='animated pulse responsiveimg '></div>");
            } else {
                out.write("<ul class='collection' style='width: 100%;height: 55vh;overflow: scroll; border-color:"+Ccolor+"' >");
                while (cmntsrs.next()) {
                    String cmpic = "";
                    String cmfn = cmntsrs.getString(4);
                    String cmln = cmntsrs.getString(5);
                    java.sql.ResultSet imguserincmnt;
                    PreparedStatement imgPs = DB.prepare("Select image From user_profile_pic where users_idusers=?");
                    imgPs.setInt(1, cmntsrs.getInt(6));
                    imguserincmnt = imgPs.executeQuery();
                    if (imguserincmnt.next()) {
                        cmpic = imguserincmnt.getString(1);
                    }
                    out.write("<li class='collection-item avatar "+Acolor+" "+Dcolor+"' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + cmfn + " " + cmln + "</span>");
                    out.write("<p>" + esc(cmntsrs.getString(1)) + "<br>");
                    out.write("" + esc(cmntsrs.getString(2)) + " ");
                    out.write("</p>");
                    out.write("</li>");
                }
                out.write("</ul>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading comments");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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