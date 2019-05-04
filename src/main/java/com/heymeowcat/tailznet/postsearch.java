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
@WebServlet(name = "postsearch", urlPatterns = {"/postsearch"})
public class postsearch extends HttpServlet {

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
            uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
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

            int privacy = 1;
            java.sql.ResultSet privacyrs = DB.search("SELECT `privacy_name` FROM user_privacy WHERE users_idusers='" + uid + "' ");
            if (privacyrs.next()) {
                if (privacyrs.getString(1).equals("private")) {
                    privacy = 2;
                }
            }

            String s = request.getParameter("name");
            String ss = ENCDEC.encrypt(s, new KEY().secretKey);
            if (!s.isEmpty()) {
                s = "%" + s + "%";
            }
            
           
            java.sql.ResultSet rs = DB.search("SELECT * FROM post where idpost =ANY(Select DISTINCT `idpost` from post where post_heading = '"+ss+"' OR post_detial ='"+ss+"' OR users_idusers= ANY(SELECT `idusers` FROM users WHERE firstname LIKE '" + s + "' OR lastname LIKE '" + s + "' OR  concat(firstname,' ',lastname) LIKE '" + s + "' OR  concat(firstname,lastname) LIKE '" + s + "' ))");
            while (rs.next()) {
                if (rs.getString(7).equals("1")) {
                    out.write("\n");
                    out.write("                            <div class=\"col s12 m6 l4 \">\n");
                    out.write("                                <div class=\" ");
                    out.print(Acolor);
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" card small hoverable\" >\n");
                    out.write("                                    <div class=\"card-image\">\n");
                    out.write("                                        <img class=\"responsive-img\" src=\"");
                    out.print(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey));
                    out.write("\">\n");
                    out.write("                                    </div>\n");
                    out.write("                                    <div class=\"card-content\" >\n");
                    out.write("                                        <span class=\"card-title ");
                    out.print(Dcolor);
                    out.write("\"><b class=\"truncate");
                    out.print(Dcolor);
                    out.write('"');
                    out.write('>');
                    out.print(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                    out.write("</b><i class=\"material-icons right activator waves-effect  ");
                    out.print(Dcolor);
                    out.write("\">more_vert</i></span>    \n");
                    out.write("                                        <p class=\"truncate\">");
                    out.print(ENCDEC.decrypt(rs.getString(4), new KEY().secretKey));
                    out.write("</p>\n");
                    out.write("                                    </div>\n");
                    out.write("                                    <div class=\" ");
                    out.print(Dcolor);
                    out.write(' ');
                    out.print(Acolor);
                    out.write("  card-reveal\" >\n");
                    out.write("                                        <span class=\"card-title ");
                    out.print(Dcolor);
                    out.write(" text-darken-4 truncate \">");
                    out.print(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                    out.write("<i class=\"material-icons right waves-effect\">close</i></span>\n");
                    out.write("                                        <div class=\"card-content \">\n");
                    out.write("                                            <a onclick=\"showprofile('");
                    out.print(rs.getString(1));
                    out.write("', '");
                    out.print(uid);
                    out.write("');$('#peekprofile').modal('open');\">\n");
                    out.write("                                                <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                    ");

                    String imgup = "";
                    String fnamepost = "";
                    String lnamepost = "";
                    String postdate = "";
                    String posttime = "";
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

                    out.write("\n");
                    out.write("                                                    <img src=\"");
                    out.print(imgup);
                    out.write("\">\n");
                    out.write("                                                    ");
                    out.print(fnamepost + " " + lnamepost);
                    out.write("\n");
                    out.write("                                                </div></a>\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                Date: ");
                    out.print(postdate);
                    out.write("\n");
                    out.write("                                            </div>\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                Time:  ");
                    out.print(posttime);
                    out.write("\n");
                    out.write("                                            </div>\n");
                    out.write("                                        </div>\n");
                    out.write("                                        <div class=\"card-action\">\n");
                    out.write("                                            ");

                    try {
                        java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                        if (!likechech.isBeforeFirst()) {

                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(rs.getString(1));
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        } else if (likechech.next()) {
                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" checked=\"\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(rs.getString(1));
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    out.write("\n");
                    out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnts('");
                    out.print(uid);
                    out.write("', '");
                    out.print(rs.getString(1));
                    out.write("')\">open_in_new</i>\n");
                    out.write("                                        </div>\n");
                    out.write("                                    </div>\n");
                    out.write("                                </div>\n");
                    out.write("                            </div>\n");
                    out.write("                            ");
                } else if (rs.getString(7).equals("2")) {
                    out.write("\n");
                    out.write("                            <div class=\"col s12 m6 l4 \">\n");
                    out.write("                                <div class=\" ");
                    out.print(Acolor);
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" card small hoverable \">\n");
                    out.write("                                    <div class=\"card-image\">\n");
                    out.write("                                       ");
                    out.print(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey));
                    out.write("\n");
                    out.write("                                    </div>\n");
                    out.write("                                    <div class=\"card-content\">\n");
                    out.write("                                        <span class=\"card-title ");
                    out.print(Dcolor);
                    out.write("\"><b class=\"truncate ");
                    out.print(Dcolor);
                    out.write('"');
                    out.write('>');
                    out.print(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                    out.write("</b><i class=\"material-icons right activator waves-effect ");
                    out.print(Dcolor);
                    out.write("\">more_vert</i></span>    \n");
                    out.write("                                        <p class=\"truncate\">");
                    out.print(ENCDEC.decrypt(rs.getString(4), new KEY().secretKey));
                    out.write("</p>\n");
                    out.write("                                    </div>\n");
                    out.write("                                    <div class=\"");
                    out.print(Acolor);
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" card-reveal\">\n");
                    out.write("                                        <i class=\"material-icons right waves-effect card-title ");
                    out.print(Dcolor);
                    out.write("\">close</i>\n");
                    out.write("                                        <span class=\"card-title ");
                    out.print(Dcolor);
                    out.write(" text-darken-4 truncate\">");
                    out.print(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                    out.write("</span>\n");
                    out.write("                                        <div class=\"card-content \">\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                ");

                    String imgup = "";
                    String fnamepost = "";
                    String lnamepost = "";
                    String postdate = "";
                    String posttime = "";
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

                    out.write("\n");
                    out.write("                                                <img src=\"");
                    out.print(imgup);
                    out.write("\">\n");
                    out.write("                                                ");
                    out.print(fnamepost + " " + lnamepost);
                    out.write("\n");
                    out.write("                                            </div>\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                Date: ");
                    out.print(postdate);
                    out.write("\n");
                    out.write("                                            </div>\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" chip waves-effect waves-light \">\n");
                    out.write("                                                Time:  ");
                    out.print(posttime);
                    out.write("\n");
                    out.write("                                            </div>\n");
                    out.write("                                        </div>\n");
                    out.write("                                        <div class=\"card-action\">\n");
                    out.write("                                            ");

                    java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                    if (!likechech.isBeforeFirst()) {

                        out.write("\n");
                        out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                        out.write("                                                <input type=\"checkbox\" class=\"toggle-checkbox\" onchange=\"like('");
                        out.print(rs.getString(1));
                        out.write("', '");
                        out.print(uid);
                        out.write("')\">\n");
                        out.write("                                                <div class=\"toggle-btn\"></div>\n");
                        out.write("                                            </label>\n");
                        out.write("                                            ");

                    } else if (likechech.next()) {
                        out.write("\n");
                        out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                        out.write("                                                <input type=\"checkbox\" checked=\"\" class=\"toggle-checkbox\" onchange=\"like('");
                        out.print(rs.getString(1));
                        out.write("', '");
                        out.print(uid);
                        out.write("')\">\n");
                        out.write("                                                <div class=\"toggle-btn\"></div>\n");
                        out.write("                                            </label>\n");
                        out.write("                                            ");

                    }

                    out.write("\n");
                    out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnts('");
                    out.print(uid);
                    out.write("', '");
                    out.print(rs.getString(1));
                    out.write("')\">open_in_new</i>\n");
                    out.write("                                        </div>\n");
                    out.write("                                    </div>\n");
                    out.write("                                </div>\n");
                    out.write("                            </div>\n");
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
