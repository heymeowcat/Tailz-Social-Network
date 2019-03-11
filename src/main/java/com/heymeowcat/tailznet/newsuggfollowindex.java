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
@WebServlet(name = "newsuggfollowindex", urlPatterns = {"/newsuggfollowindex"})
public class newsuggfollowindex extends HttpServlet {

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
            int loggeduid = Integer.parseInt(request.getParameter("loggedid"));
            int x = Integer.parseInt(request.getParameter("x"));
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
            DB.iud("INSERT INTO `follow` (`sender`, `receiver`) VALUES ('" + loggeduid + "','" + x + "' );");
            boolean m = false;
            java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + loggeduid + "') ) AND NOT idusers='" + loggeduid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + loggeduid + "') limit 5");
            while (rs.next()) {
                m = true;
                out.write("\n");
                out.write("                                    <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                out.print(rs.getString(3));
                out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle \" ><div class=\"");
                out.print(Dcolor);
                out.write('"');
                out.write('>');
                out.print(rs.getString(1));
                out.write(' ');
                out.print(rs.getString(2));
                out.write("</div></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a class=\" btn ");
                out.print(Bcolor);
                out.write(' ');
                out.print(Dcolor);
                out.write(" waves-effect\" onclick=\"followthissugg('");
                out.print(rs.getString(4));
                out.write("')\"><i class=\"material-icons\">person_add</i></a></h6></td></tr>\n");
                out.write("                                            ");

            }
            out.write("\n");
            out.write("                                    ");
            if (m) {
                out.write("\n");
                out.write("                                           <tr style='border-color: transparent'><td class=\"");
                out.print(Dcolor);
                out.write("\"><a class=\"");
                out.print(Dcolor);
                out.write("\" href=\"search-trending.jsp\">More...</a></td></tr>\n");
                out.write("                                    ");
            }
            out.write("\n");
            out.write("                                 \n");
            out.write("                                </table>\n");
            out.write("                                ");
            java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + loggeduid + "') ) AND NOT idusers='" + loggeduid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + loggeduid + "')");
            if (!rsop.isBeforeFirst()) {
                out.write("\n");
                out.write("                                <div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>\n");
                out.write("                            </div>\n");
                out.write("                            ");

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
