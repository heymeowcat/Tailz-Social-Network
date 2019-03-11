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
@WebServlet(name = "peekprofile", urlPatterns = {"/peekprofile"})
public class peekprofile extends HttpServlet {

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
            int uid = Integer.parseInt(request.getParameter("q"));
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

            String up = "";
            String fn = "";
            String ln = "";
            java.sql.ResultSet ufl = DB.search("Select firstname,lastname FROM `users` where idusers='" + uid + "' ");
            if (ufl.next()) {
                fn = ufl.getString(1);
                ln = ufl.getString(2);
            }

            String usrpostcount = "";
            String followercount = "";
            String followingcount = "";
            java.sql.ResultSet usp = DB.search("Select image FROM `user_profile_pic` where users_idusers=" + uid + " ");
            if (!usp.isBeforeFirst()) {
                up = "img/Profile_avatar_placeholder_large.png";
            } else if (usp.next()) {
                up = usp.getString(1);
            }
            out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + "'>close</i>");
            out.write("<div class='center'>");
            out.write("<div class='" + Ecolor + " card-panel'>");

            out.write("<img style='height: 150px; width: 150px' src=' " + up + " '  class='circle responsive-img hide-on-small-and-down animated fadeIn'>");
            out.write("<img style='height: 100px; width: 100px' src='" + up + "'  class='circle responsive-img hide-on-med-and-up animated fadeIn'>");
            out.write("<br>");
            out.write("<h4 class='hide-on-small-and-down'>" + fn + " " + ln + "</h4>");
            out.write("<h5 class='hide-on-med-and-up'>" + fn + " " + ln + "</h5>");

            out.write("<br>");
            out.write("<br>");
            out.write("<div class='row center'>");
            try {
                java.sql.ResultSet postcount = DB.search("SELECT COUNT(`users_idusers`) FROM `post` WHERE `users_idusers`='" + uid + "' ");
                if (postcount.next()) {
                    usrpostcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Posts</span><br><b>" + usrpostcount + "</b></div>");
            try {
                java.sql.ResultSet postcount = DB.search("SELECT DISTINCT COUNT(`sender`) FROM `follow` WHERE `receiver`='" + uid + "' ");
                if (postcount.next()) {
                    followercount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Followers</span><br><b>" + followercount + "</b></div>");
            try {
                java.sql.ResultSet postcount = DB.search("SELECT DISTINCT COUNT(`receiver`) FROM `follow` WHERE `sender`='" + uid + "' ");
                if (postcount.next()) {
                    followingcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Following</span><br><b>" + followingcount + "</b></div>");
            out.write("</div>");
            out.write("</div>");

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
