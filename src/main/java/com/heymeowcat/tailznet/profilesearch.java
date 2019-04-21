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
@WebServlet(name = "profilesearch", urlPatterns = {"/profilesearch"})
public class profilesearch extends HttpServlet {

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
            String s = request.getParameter("name");
            s = "%" + s + "%";
            int loggeduid = Integer.parseInt(request.getParameter("loggedid"));
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
            java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `idusers` FROM users WHERE firstname LIKE '" + s + "' OR lastname LIKE '" + s + "' OR  concat(firstname,' ',lastname) LIKE '" + s + "' OR  concat(firstname,lastname) LIKE '" + s + "' ) LIMIT 5");
            String text = "";
            while (rs.next()) {
                text += "<tr style='background-color:" + Ccolor + "' class=animated fadeIn>";
                text += "<td valign='middle' class='left'><img src=" + rs.getString(3) + " width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img '></td><td valign='middle'><h6 class='" + Dcolor + "'>" + rs.getString(1) + "  " + rs.getString(2) + "</h6></td><td><a onclick='fullviewprofile(" + rs.getString(4) + ")'><i class='material-icons right " + Dcolor + " waves-effect '>open_in_new</i></a></td>";
                text += "</tr>";
            }
            out.write(text);
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
