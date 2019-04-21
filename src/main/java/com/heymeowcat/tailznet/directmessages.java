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
@WebServlet(name = "directmessages", urlPatterns = {"/directmessages"})
public class directmessages extends HttpServlet {

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
            int muid = Integer.parseInt(request.getParameter("muid"));
            String up = "";
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
            java.sql.ResultSet rs = DB.search("SELECT * FROM `chat` where user_sender=" + muid + " and users_receiver =" + uid + " OR user_sender=" + uid + " AND users_receiver =" + muid + " ORDER BY chat_datetime asc ");
            while (rs.next()) {
                java.sql.ResultSet muidprofiles = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers where idusers =" + muid + " ");
                java.sql.ResultSet uidprofiles = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers where idusers =" + uid + " ");
                if (muidprofiles.next() | uidprofiles.next()) {
                    if (rs.getString(5).equals("" + muid)) {
                        out.write("<div class='message__list'>");
                        out.write("<div class='message__item message__item--bot'>");
                        out.write("<span class='message message--bot "+Dcolor+"'  data-balloon='"+rs.getString(4) +"' data-balloon-pos='right' >");
                        if (rs.getString(2) != null ) {
                            out.write(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                        }
                        if (rs.getString(3) != null) {
                            if(rs.getString(2) == null){
                                out.write("<img class='responsive-img' src='" + ENCDEC.decrypt(rs.getString(3), new KEY().secretKey) + "' width='300px' style='border-radius: 15px'>");
                            }else{
                                out.write("<br><img class='responsive-img' src='" + ENCDEC.decrypt(rs.getString(3), new KEY().secretKey) + "' width='300px' style='border-radius: 15px'>");
                            }
                        }   
                        out.write("</span>");
                        out.write("</div>");
                        out.write("</div>");
                    } else if (rs.getString(5).equals("" + uid)) {
                        out.write("<div class='message__list'>");
                        out.write("<div class='message__item message__item--user'>");
                        out.write("<span class='message message--user "+Dcolor+"' data-balloon='"+rs.getString(4) +"' data-balloon-pos='left' >");
                        if (rs.getString(2) != null) {
                         out.write(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                        }
                        if (rs.getString(3) != null) {
                            if(rs.getString(2) == null){
                                out.write("<img class='responsive-img' src='" + ENCDEC.decrypt(rs.getString(3), new KEY().secretKey) + "' width='300px' style='border-radius: 15px'>");
                            }else{
                                out.write("<br><img class='responsive-img' src='" +ENCDEC.decrypt(rs.getString(3), new KEY().secretKey) + "' width='300px' style='border-radius: 15px'>");
                            }
                        }
                        out.write("</span>");
                        out.write("</div>");
                        out.write("</div>");

                    }

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
