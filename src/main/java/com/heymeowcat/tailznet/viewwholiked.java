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
@WebServlet(name = "viewwholiked", urlPatterns = {"/viewwholiked"})
public class viewwholiked extends HttpServlet {

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
            int postid = Integer.parseInt(request.getParameter("q"));
            int loggeduid = Integer.parseInt(request.getParameter("loggedusr"));
            String Acolor = "";
            String Bcolor = "";
            String Ccolor = "";
            String Dcolor = "";
            String Ecolor = "";
            java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + loggeduid + "' ");
            if (themers.next()) {
                if (themers.getString(1).equals("pinkdark")) {
                    Acolor = "black";
                    Bcolor = "pink";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("pinklight")) {
                    Acolor = "white";
                    Bcolor = "pink lighten-4";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "red lighten-5";
                } else if (themers.getString(1).equals("bluelight")) {
                    Acolor = "white";
                    Bcolor = "light-blue lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "light-blue lighten-5";
                } else if (themers.getString(1).equals("bluedark")) {
                    Acolor = "black";
                    Bcolor = "blue";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("yellowlight")) {
                    Acolor = "white";
                    Bcolor = "yellow lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "yellow lighten-4";
                } else if (themers.getString(1).equals("yellowdark")) {
                    Acolor = "black";
                    Bcolor = "yellow darken-4";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("greenlight")) {
                    Acolor = "white";
                    Bcolor = "light-green lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "light-green lighten-4";
                } else if (themers.getString(1).equals("greendark")) {
                    Acolor = "black";
                    Bcolor = "green";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("purplelight")) {
                    Acolor = "white";
                    Bcolor = "purple lighten-3";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "purple lighten-5";
                } else if (themers.getString(1).equals("purpledark")) {
                    Acolor = "black";
                    Bcolor = "purple";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                }
            }
            out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + "'>close</i>");
            out.write("<ul class='" + Acolor + " collapsible 'style='border-color: " + Ccolor + "'>");
            out.write("<li class='active'>");
            out.write("<div class='collapsible-header " + Acolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'><b>People Reacted</b></div>");
            out.write("<div class='collapsible-body " + Ecolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'>");

            out.write("<table class='highlight " + Acolor + "'>");
            java.sql.ResultSet senderids = DB.search("SELECT likedby FROM `post_rank` where post_idpost ='" + postid + "'");
            while (senderids.next()) {
                java.sql.ResultSet senderss = DB.search("SELECT `firstname`,`lastname`,`idusers` FROM `users` WHERE `idusers`='" + senderids.getString(1) + "'");
                while (senderss.next()) {
                    java.sql.ResultSet senderimg = DB.search("SELECT `image` FROM `user_profile_pic` WHERE `users_idusers`='" + senderss.getString(3) + "'");
                    while (senderimg.next()) {
                        out.write("\n");
                        out.write("                                <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                        out.print(senderimg.getString(1));
                        out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle\" ><h6 >");
                        out.print(senderss.getString(1) + " " + senderss.getString(2));
                        out.write("</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('");
                        out.print(senderids.getString(1));
                        out.write("', '");
                        out.print(loggeduid);
                        out.write("');$('#peekprofile').modal('open');\" class=\"");
                        out.print(Dcolor);
                        out.write("\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>\n");
                        out.write("                                        ");
                    }
                }
            }
            out.write("</table>");
            java.sql.ResultSet meowrs = DB.search("SELECT likedby FROM `post_rank` where post_idpost ='" + postid + "'");
            if (!meowrs.isBeforeFirst()) {
                out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
            }

            out.write("</div>");
            out.write("</li>");
            out.write("</ul>");

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
