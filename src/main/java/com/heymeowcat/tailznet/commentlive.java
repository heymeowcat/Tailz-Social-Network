package com.heymeowcat.tailznet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.heymeowcat.tailznet.DB;
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
            int pid = Integer.parseInt(request.getParameter("pid"));
            String uid = request.getParameter("uid");
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
                        Fcolor = "#e91e63";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "#f8bbd0";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "#4fc3f7";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#2196F3";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "#fff176";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#f57f17";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "#aed581";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#4CAF50";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "#ce93d8";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#9c27b0";
                    }
                }

            java.sql.ResultSet cmntsrs = DB.search("Select * from `post_comment` where post_idpost='" + pid + "' ORDER BY `post_comment`.`datetime` DESC ");
            if (!cmntsrs.isBeforeFirst()) {
                out.write("<div class='center' style='top:40%; position:relative'><img src ='img/commentlive.png' class='animated pulse responsiveimg '></div>");
            } else {
                while (cmntsrs.next()) {
                    String cmpic = "";
                    String cmfn = "";
                    String cmln = "";
                    java.sql.ResultSet imguserincmnt = DB.search("Select image From user_profile_pic where users_idusers='" + cmntsrs.getInt(3) + "' ");
                    if (imguserincmnt.next()) {
                        cmpic = imguserincmnt.getString(1);
                    }
                    java.sql.ResultSet cmnfirstn = DB.search("Select firstname From users where idusers='" + cmntsrs.getInt(3) + "' ");
                    if (cmnfirstn.next()) {
                        cmfn = cmnfirstn.getString(1);
                    }
                    java.sql.ResultSet cmnlastn = DB.search("Select lastname From users where idusers='" + cmntsrs.getInt(3) + "' ");
                    if (cmnlastn.next()) {
                        cmln = cmnlastn.getString(1);
                    }
                    out.write("<li class='collection-item avatar "+Acolor+" "+Dcolor+"' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + cmfn + " " + cmln + "</span>");
                    out.write("<p>" + cmntsrs.getString(5) + "<br>");
                    out.write("" + cmntsrs.getString(2) + " ");
                    out.write("</p>");
                    out.write("</li>");
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
