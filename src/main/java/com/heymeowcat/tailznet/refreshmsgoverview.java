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
@WebServlet(name = "refreshmsgoverview", urlPatterns = {"/refreshmsgoverview"})
public class refreshmsgoverview extends HttpServlet {

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
            int muiddd = 0;
            java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers from users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender='" + uid + "') AND idusers= ANY(SELECT sender FROM follow WHERE receiver='" + uid + "'))  ORDER BY (Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender=idusers and users_receiver='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='0' and users_receiver=idusers and user_sender='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='1' and users_receiver=idusers and user_sender='" + uid + "' ) DESC");
            if (!rsop.isBeforeFirst()) {
                out.write("\n");
                out.write("                    <div class='center'><img src='img/conversation.png' class='responsiveimg' style=\"margin-top: 100px\"></div>\n");
                out.write("                    <div class=\"grey-text center\">Icons made by <a href=\"https://www.freepik.com/\" title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>\n");
                out.write("                    ");

            }
            java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers from users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender='" + uid + "') AND idusers= ANY(SELECT sender FROM follow WHERE receiver='" + uid + "'))  ORDER BY (Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender=idusers and users_receiver='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='0' and users_receiver=idusers and user_sender='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='1' and users_receiver=idusers and user_sender='" + uid + "' ) DESC");
            while (rs.next()) {
                muiddd = Integer.parseInt(rs.getString(4));
                int unseenno = 0;
                String outString = "";
                java.sql.ResultSet unseencount = DB.search("Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender='" + rs.getString(4) + "' and users_receiver='" + uid + "' ");
                if (unseencount.next()) {
                    unseenno = unseencount.getInt(1);
                    if (unseenno > 9) {
                        outString = "<b>9+</b>";
                    } else if (unseenno == 0) {
                        outString = "<i class='material-icons " + Dcolor + " '>add</i>";
                    } else {
                        outString = "<b>" + unseenno + "</b>";
                    }
                }

                out.write("\n");
                out.write("                    <div class=\"col s6 m3 l2\">\n");
                out.write("                        <div class=\"");
                out.print(Acolor);
                out.write(" card-panel hoverable \">\n");
                out.write("                            <img src=\"");
                out.print(rs.getString(3));
                out.write("\" class=\"circle responsive-img\">\n");
                out.write("                            <div class=\"card-content center ");
                out.print(Dcolor);
                out.write("\">\n");
                out.write("                                <p class=\"truncate\">");
                out.print(rs.getString(1));
                out.write(' ');
                out.print(rs.getString(2));
                out.write("</p>\n");
                out.write("                                <a onclick=\"setuser(");
                out.print(muiddd);
                out.write(");\"  class=\"btn-floating ");
                out.print(Bcolor);
                out.write(' ');
                out.print(Dcolor);
                out.write(" waves-effect\">");
                out.print(outString);
                out.write("</a>\n");
                out.write("                            </div>\n");
                out.write("                        </div>\n");
                out.write("                    </div>\n");
                out.write("\n");
                out.write("                    ");

            }
            java.sql.ResultSet rsgroup = DB.search("SELECT * FROM `groups` where group_id= ANY(SELECT Groups_group_id from group_members where members='" + uid + "') ORDER BY (Select count(chatstatus) from group_chat where chatstatus='0' and users_idusers!='" + uid + "' ) DESC,(Select count(chatstatus) from group_chat where chatstatus='0' and  users_idusers='" + uid + "' ) DESC,(Select count(chatstatus) from group_chat where chatstatus='1' and  users_idusers!='" + uid + "' ) DESC");
            String outString = "<i class='material-icons " + Dcolor + " '>add</i>";
            while (rsgroup.next()) {

                out.write("\n");
                out.write("                    <div class=\"col s6 m3 l2\">\n");
                out.write("                        <div class=\"");
                out.print(Acolor);
                out.write(" card-panel hoverable \">\n");
                out.write("                            <img src=\"");
                out.print(rsgroup.getString(3));
                out.write("\" class=\"circle responsive-img\">\n");
                out.write("                            <div class=\"card-content center ");
                out.print(Dcolor);
                out.write("\">\n");
                out.write("                                <p class=\"truncate\">");
                out.print(rsgroup.getString(2));
                out.write("</p>\n");
                out.write("                                <a onclick=\"setusergroup('");
                out.print(rsgroup.getString(1));
                out.write("');\"  class=\"btn-floating ");
                out.print(Bcolor);
                out.write(' ');
                out.print(Dcolor);
                out.write(" waves-effect\">");
                out.print(outString);
                out.write("</a>\n");
                out.write("                            </div>\n");
                out.write("                        </div>\n");
                out.write("                    </div>\n");
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
