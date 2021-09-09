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
            int uid = 0;
            int pid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                pid = Integer.parseInt(request.getParameter("pid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/pid parameter");
                return;
            }
            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            PreparedStatement rsPs = DB.prepare("Select * FROM `post` where idpost=?");
            rsPs.setInt(1, pid);
            ResultSet rs = rsPs.executeQuery();
            if (rs.next()) {
                out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + " '>close</i>");
                out.write("<div class='" + Acolor + " card-panel' >");
                out.write("<div class='row'>");
                out.write("<div class='col s12 m6' style='max-height:100% ;overflow: scroll' > ");
                if (rs.getString(7).equals("1")) {
                    out.write("<img class='responsive-img materialboxed' src='" + ENCDEC.decrypt(rs.getString(3), "default-key") + "' ");
                } else if (rs.getString(7).equals("2")) {
                    out.write(ENCDEC.decrypt(rs.getString(3), "default-key"));
                }
                out.write("<span><h5>" + ENCDEC.decrypt(rs.getString(2), "default-key") + "</h5></span>");
                out.write("<p>" + ENCDEC.decrypt(rs.getString(4), "default-key") + "</p>");
                out.write("<br>");
                String imgup = "";
                String fnamepost = "";
                String lnamepost = "";
                String postdate = "";
                String posttime = "";
                String likecount = " ";
                PreparedStatement imgpostuserPs = DB.prepare("Select image From user_profile_pic where users_idusers=?");
                imgpostuserPs.setInt(1, rs.getInt(6));
                ResultSet imgpostuser = imgpostuserPs.executeQuery();
                if (imgpostuser.next()) {
                    imgup = imgpostuser.getString(1);
                }
                PreparedStatement firstimguserPs = DB.prepare("Select firstname From users where idusers=?");
                firstimguserPs.setInt(1, rs.getInt(6));
                ResultSet firstimguser = firstimguserPs.executeQuery();
                if (firstimguser.next()) {
                    fnamepost = firstimguser.getString(1);
                }
                PreparedStatement lastimguserPs = DB.prepare("Select lastname From users where idusers=?");
                lastimguserPs.setInt(1, rs.getInt(6));
                ResultSet lastimguser = lastimguserPs.executeQuery();
                if (lastimguser.next()) {
                    lnamepost = lastimguser.getString(1);
                }
                PreparedStatement imgdatePs = DB.prepare("Select cast(post_time as date) From post where idpost=?");
                imgdatePs.setInt(1, rs.getInt(1));
                ResultSet imgdate = imgdatePs.executeQuery();
                if (imgdate.next()) {
                    postdate = imgdate.getString(1);
                }
                PreparedStatement imgtimePs = DB.prepare("Select cast(post_time as time) From post where idpost=?");
                imgtimePs.setInt(1, rs.getInt(1));
                ResultSet imgtime = imgtimePs.executeQuery();
                if (imgtime.next()) {
                    posttime = imgtime.getString(1);
                }
                PreparedStatement likecountrsPs = DB.prepare("Select count(likes) From post_rank where post_idpost=?");
                likecountrsPs.setInt(1, rs.getInt(1));
                ResultSet likecountrs = likecountrsPs.executeQuery();
                if (likecountrs.next()) {
                    likecount = likecountrs.getString(1);
                }
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'><img src='" + imgup + "'>" + fnamepost + " " + lnamepost + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + postdate + "</div>");
                out.write("<div class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>" + posttime + "</div>");
                out.write("<div onclick='$('#peekwholikesthis').modal('open')'; class='" + Bcolor + "  " + Dcolor + " chip waves-effect waves-light'>😍 " + likecount + "</div>");
                out.write("</div>");
                out.write("<div class='col s12 m6'>");
                out.write("<h6>Comments</h6>");
                out.write("<ul id='commentsection' class='" + Acolor + " collection' style='width: 100%;height: 55vh;overflow: scroll; border-color:"+Ccolor+"' >");
            }

            PreparedStatement cmntsrsPs = DB.prepare("Select pc.idpost_comment, pc.post_idpost, pc.users_idusers, u.firstname, u.lastname, u.image, pc.datetime, pc.likes FROM post_comment pc JOIN users u ON pc.users_idusers = u.idusers WHERE pc.post_idpost=? ORDER BY pc.datetime DESC");
            cmntsrsPs.setInt(1, pid);
            ResultSet cmntsrs = cmntsrsPs.executeQuery();
            boolean hasComments = cmntsrs.next();
            if (!hasComments) {
                out.write("<div class='center' style='top:40%; position:relative'><img src ='img/commentlive.png' class='animated pulse responsiveimg '></div>");
            } else {
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
                    out.write("<li class='collection-item avatar  " + Acolor + " " + Dcolor + "' style='border-color:"+Ccolor+"'>");
                    out.write("<img src='" + cmpic + "'  class='circle'>");
                    out.write("<span class='title'>" + cmfn + " " + cmln + "</span>");
                    out.write("<p>" + commentEscape(cmntsrs.getString(1)) + "<br>");
                    out.write("" + commentEscape(cmntsrs.getString(2)) + " ");
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
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading post");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String commentEscape(String s) {
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