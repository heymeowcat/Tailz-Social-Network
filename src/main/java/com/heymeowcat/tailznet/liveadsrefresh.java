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
@WebServlet(name = "liveadsrefresh", urlPatterns = {"/liveadsrefresh"})
public class liveadsrefresh extends HttpServlet {

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
             int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
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
            java.sql.ResultSet ss = DB.search("Select Adid from ads where status='4' and users_idusers='" + uid + "' order by (SELECT `adstartedtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC  ");
            while (ss.next()) {
                java.sql.ResultSet adstolivers = DB.search("Select Adid,Text,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ss.getString(1) + "' and status='4'");
                while (adstolivers.next()) {

                    out.write("\n");
                    out.write("                                <div class=\"col s12 m4\">\n");
                    out.write("                                    <b class=\"letter-spacing: ; grey-text\">Ad Id:#");
                    out.print(ss.getString(1));
                    out.write("</b>\n");
                    out.write("                                    <div class=\"card ");
                    out.print(Acolor);
                    out.write(" \">\n");
                    out.write("                                    <div class=\"card-content ");
                    out.print(Dcolor);
                    out.write("\">\n");
                    out.write("                                            <div class=\"card-image resizeimg\" style=\"overflow: hidden\">\n");
                    out.write("                                            <a href=\"");
                    out.print(adstolivers.getString(4));
                    out.write("\">\n");
                    out.write("                                                <img src=\"");
                    out.print(adstolivers.getString(3));
                    out.write("\" >\n");
                    out.write("                                            </a>\n");
                    out.write("                                            </div>\n");
                    out.write("                                        </div>\n");
                    out.write("                                        <div class=\"card-action\">\n");
                    out.write("                                            <div class=\"");
                    out.print(Bcolor);
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" btn-floating center\">\n");
                    out.write("                                                <span><i class=\"material-icons ");
                    out.print(Dcolor);
                    out.write("\">pause</i></span>\n");
                    out.write("                                            </div>\n");
                    out.write("                                            ");

                    String timeremainingfrad = "";
                    java.sql.ResultSet timedifrs = DB.search("SELECT TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP),(SELECT CAST(TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP) AS CHAR) FROM sEXUaFqh92.adtiming WHERE Ads_Adid ='" + ss.getInt(1) + "') FROM adtiming WHERE Ads_Adid ='" + ss.getInt(1) + "' ");
                    if (timedifrs.next()) {
                        if (timedifrs.getString(2).startsWith("-")) {
                            DB.iud("UPDATE `ads` SET `status` = '6'  WHERE `ads`.`Adid` = '" + ss.getInt(1) + "';");
                            timeremainingfrad = "Expired";
                        } else {
                            DB.iud("UPDATE `ads` SET `status` = '4'  WHERE `ads`.`Adid` = '" + ss.getInt(1) + "';");
                            timeremainingfrad = timedifrs.getString(1);
                        }
                    }

                    out.write("\n");
                    out.write("                                            <button class=\" ");
                    out.print(Acolor);
                    out.write(' ');
                    out.print(Dcolor);
                    out.write(" right btn-flat  \">");
                    out.print(timeremainingfrad);
                    out.write("</button>\n");
                    out.write("                                        </div>  \n");
                    out.write("                                    </div>  \n");
                    out.write("                                </div>\n");
                    out.write("                                ");

                }
            }
            java.sql.ResultSet rs = DB.search("Select * from ads where status='4' and users_idusers=" + uid + "");
            if (!rs.isBeforeFirst()) {
                out.write("<div class='center'><img src ='img/nolive.png' class='animated pulse responsiveimg '></div>");
            }
            out.write("\n");
            out.write("                            </div>\n");
            out.write("                        </div>\n");
            out.write("                    </li>\n");
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
