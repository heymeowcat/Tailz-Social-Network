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
@WebServlet(name = "peekcomments", urlPatterns = {"/peekcomments"})
public class peekcomments extends HttpServlet {

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
            int pid = Integer.parseInt(request.getParameter("pid"));
            String up = "";
            String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor = "";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
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
            java.sql.ResultSet rs = DB.search("Select * FROM `post` where idpost=" + pid);
            if (rs.next()) {
                out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + " '>close</i>");
                out.write("<div class='" + Acolor + " card-panel' >");
                out.write("<div class='row'>");
                out.write("<div class='col s12 m6' style='max-height:100% ;overflow: scroll' > ");
                if (rs.getString(7).equals("1")) {
                    out.write("<img class='responsive-img materialboxed' src='" + rs.getString(3) + "' ");
                } else if (rs.getString(7).equals("2")) {
                    out.write("<span class='materialboxed'>");
                    out.write("<video class='responsive-video' controls>");
                    out.write("<source src='" + rs.getString(3) + "' type='video/mp4'>");
                    out.write("</video>");
                    out.write("</span>");
                }
                out.write("<span><h5>" + rs.getString(2) + "</h5></span>");
                out.write("<p>" + rs.getString(4) + "</p>");
                out.write("<br>");
                String imgup = "";
                String fnamepost = "";
                String lnamepost = "";
                String postdate = "";
                String posttime = "";
                String likecount = " ";
                java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(6) + "' ");
                if (imgpostuser.next()) {
                    imgup = imgpostuser.getString(1);
                }
                java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(6) + "' ");
                if (firstimguser.next()) {
                    fnamepost = firstimguser.getString(1);
                }
                java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(6) + "' ");
                if (lastimguser.next()) {
                    lnamepost = lastimguser.getString(1);
                }
                java.sql.ResultSet imgdate = DB.search("Select cast(post_time as date) From post where idpost='" + rs.getInt(1) + "' ");
                if (imgdate.next()) {
                    postdate = imgdate.getString(1);
                }
                java.sql.ResultSet imgtime = DB.search("Select cast(post_time as time) From post where idpost='" + rs.getInt(1) + "' ");
                if (imgtime.next()) {
                    posttime = imgtime.getString(1);
                }
                java.sql.ResultSet likecountrs = DB.search("Select count(likes) From post_rank where post_idpost='" + rs.getInt(1) + "' ");
                if (likecountrs.next()) {
                    likecount = likecountrs.getString(1);
                }
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'><img src='" + imgup + "'>" + fnamepost + " " + lnamepost + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + postdate + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + posttime + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>😍 " + likecount + "</div>");
                out.write("</div>");
                out.write("<div class='col s12 m6'>");
                out.write("<h6>Comments</h6>");
                out.write("<ul id='commentsection' class='" + Acolor + " collection' style='width: 100%;height: 55vh;overflow: scroll; border-color:"+Ccolor+"' >");
            }

            java.sql.ResultSet cmntsrs = DB.search("Select * from `post_comment` where post_idpost='" + pid + "' ORDER BY `post_comment`.`datetime` DESC ");
            if (!cmntsrs.isBeforeFirst()) {
                out.write("<div class='center'><img src='img/no-feeds.png' class='responsiveimg' ></div>");
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
                    out.write("<li class='collection-item avatar  " + Acolor + " " + Dcolor + "' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + cmfn + " " + cmln + "</span>");
                    out.write("<p>" + cmntsrs.getString(5) + "<br>");
                    out.write("" + cmntsrs.getString(2) + " ");
                    out.write("</p>");
                    out.write("</li>");
                }
            }

            out.write("</ul>");
            out.write("<div class='input-field'>");
            out.write("<input type='text' id='commenttextarea' required placeholder='Add a Comment...' class=' " + Dcolor + " ' >");
            out.write("</div><a class='" + Bcolor + " " + Dcolor + " btn' onclick='comment(" + pid + "," + uid + ")'; >Add Comment</a>");
            out.write("</div>");
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
