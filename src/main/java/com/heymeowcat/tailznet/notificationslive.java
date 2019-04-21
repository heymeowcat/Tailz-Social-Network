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
@WebServlet(name = "notificationslive", urlPatterns = {"/notificationslive"})
public class notificationslive extends HttpServlet {

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
            System.gc();
            int uid = Integer.parseInt(request.getParameter("uid"));
         String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor = "";
                String Fcolor = "";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
                if (themers.next()) {
                    if (themers.getString(1).equals("pinkdark")) {
                        Acolor = "black";
                        Bcolor = "pink";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    }
                }
            java.sql.ResultSet rsop = DB.search("Select * FROM `notification` where notificationfor = '" + uid + "' ORDER BY time DESC");
            if (!rsop.isBeforeFirst()) {

                out.write("\n");
                out.write("\n");
                out.write("<div class='center'><img style=\"margin-top: 100px\" src='img/notifications-silenced.png' class='responsiveimg' style=\"margin-top: 100px\"></div>\n");
                    out.write("   <div class=\"grey-text center\">Icons made by <a href=\"https://www.freepik.com/\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a>");
            } else {
                out.write("\n");
                out.write("                <a class=\"");
                out.print(Bcolor);
                out.write(' ');
                out.print(Dcolor);
                out.write(" btn\" onclick=\"clearnotifications(");
                out.print(uid);
                out.write(")\">Mark All as Read</a>\n");
            }

            out.write("\n");
            out.write("                <ul class=\"");
            out.print(Acolor);
            out.write(" collection\" style=\"border-color: ");
            out.print(Ccolor);
            out.write("\" >\n");
            out.write("                    ");
            try {
                String commenttext = "";
                String notificationtxt = "";
                java.sql.ResultSet rs = DB.search("Select * FROM `notification` where notificationfor = '" + uid + "' ORDER BY time DESC");
                while (rs.next()) {
                    if (rs.getString(4).equals("1")) {
                        commenttext = "started following you";
                    } else if (rs.getString(4).equals("2")) {
                        commenttext = "commented on your post";
                    }
                    String imgup = "";
                    String fnamepost = "";
                    String lnamepost = "";
                    java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(3) + "' ");
                    if (imgpostuser.next()) {
                        imgup = imgpostuser.getString(1);
                    }
                    java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(3) + "' ");
                    if (firstimguser.next()) {
                        fnamepost = firstimguser.getString(1);
                    }
                    java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(3) + "' ");
                    if (lastimguser.next()) {
                        lnamepost = lastimguser.getString(1);
                    }
                    notificationtxt = fnamepost + " " + lnamepost + " " + commenttext;
                    if (rs.getInt(6) == 0) {
                        out.write("<li class='" + Bcolor + " " + Dcolor + " collection-item avatar' style='border-color: " + Ccolor + "'>");
                        out.write("<img src=" + imgup + " class='circle' >");
                        out.write("<span class='title'>" + notificationtxt + "</span>");
                        out.write("<p>");
                        out.write("" + rs.getString(5) + "");
                        out.write("</p>");
                        out.write("</li>");
                    } else if (rs.getInt(6) == 1) {
                        out.write("<li class='" + Acolor + " " + Dcolor + " collection-item avatar' style='border-color: " + Ccolor + "'>");
                        out.write("<img src=" + imgup + " class='circle' >");
                        out.write("<span class='title'>" + notificationtxt + "</span>");
                        out.write("<p>");
                        out.write("" + rs.getString(5) + "");
                        out.write("</p>");
                        out.write("</li>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.write("\n");
            out.write("\n");
            out.write("                </ul>\n");
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
