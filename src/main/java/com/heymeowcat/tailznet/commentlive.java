/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.PostComment;
import com.heymeowcat.tailznet.service.CommentService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
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
@WebServlet(name = "commentlive", urlPatterns = {"/commentlive"})
public class commentlive extends HttpServlet {

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
            int pid = 0;
            try {
                pid = Integer.parseInt(request.getParameter("pid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid pid parameter");
                return;
            }
            String uid = request.getParameter("uid");
            String[] themeColors = ThemeHelper.getThemeColors(uid != null ? Integer.parseInt(uid) : 0);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            CommentService commentService = new CommentService();
            UserService userService = new UserService();
            UserProfilePicService picService = new UserProfilePicService();

            java.util.List<PostComment> cmntsrs = commentService.getCommentsForPost(pid);
            if (cmntsrs == null || cmntsrs.isEmpty()) {
                out.write("<div class='center' style='top:40%; position:relative'><img src ='img/commentlive.png' class='animated pulse responsiveimg '></div>");
            } else {
                out.write("<ul class='collection' style='width: 100%;height: 55vh;overflow: scroll; border-color:"+Ccolor+"' >");
                for (PostComment c : cmntsrs) {
                    String cmpic = picService.getProfilePicPath(c.getUserId());
                    String cmfn = esc(userService.getUserFirstName(c.getUserId()));
                    String cmln = esc(userService.getUserLastName(c.getUserId()));
                    out.write("<li class='collection-item avatar "+Acolor+" "+Dcolor+"' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + cmfn + " " + cmln + "</span>");
                    out.write("<p>" + esc(c.getCommentText()) + "<br>");
                    out.write("" + esc(c.getDatetime()) + " ");
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

    private String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
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