package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "peekprofile", urlPatterns = {"/peekprofile"})
public class peekprofile extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int loggeduid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("q"));
                loggeduid = Integer.parseInt(request.getParameter("loggedusr"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid q/loggedusr parameter");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(loggeduid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];
            String up = "";
            String fn = "";
            String ln = "";
            PreparedStatement uflPs = DB.prepare("Select firstname,lastname FROM `users` where idusers=?");
            uflPs.setInt(1, uid);
            ResultSet ufl = uflPs.executeQuery();
            if (ufl.next()) {
                fn = esc(ufl.getString(1));
                ln = esc(ufl.getString(2));
            }

            String usrpostcount = "0";
            String followercount = "0";
            String followingcount = "0";
            PreparedStatement uspPs = DB.prepare("Select image FROM `user_profile_pic` where users_idusers=?");
            uspPs.setInt(1, uid);
            ResultSet usp = uspPs.executeQuery();
            if (!usp.isBeforeFirst()) {
                up = "img/Profile_avatar_placeholder_large.png";
            } else if (usp.next()) {
                up = esc(usp.getString(1));
            }

            try {
                PreparedStatement postcountPs = DB.prepare("SELECT COUNT(`users_idusers`) FROM `post` WHERE `users_idusers`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    usrpostcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                PreparedStatement postcountPs = DB.prepare("SELECT DISTINCT COUNT(`sender`) FROM `follow` WHERE `receiver`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    followercount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                PreparedStatement postcountPs = DB.prepare("SELECT DISTINCT COUNT(`receiver`) FROM `follow` WHERE `sender`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    followingcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.write("<i class='material-icons right waves-effect modal-close " + esc(Dcolor) + "'>close</i>");
            out.write("<div class='center'>");
            out.write("<div class='" + esc(Ecolor) + " card-panel'>");

            out.write("<img style='height: 150px; width: 150px' src='" + up + "' class='circle responsive-img hide-on-small-and-down animated fadeIn'>");
            out.write("<img style='height: 100px; width: 100px' src='" + up + "' class='circle responsive-img hide-on-med-and-up animated fadeIn'>");
            out.write("<br>");
            out.write("<h4 class='hide-on-small-and-down'>" + fn + " " + ln + "</h4>");
            out.write("<h5 class='hide-on-med-and-up'>" + fn + " " + ln + "</h5>");

            out.write("<br>");
            out.write("<br>");
            out.write("<div class='row center'>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Posts</span><br><b>" + usrpostcount + "</b></div>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Followers</span><br><b>" + followercount + "</b></div>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Following</span><br><b>" + followingcount + "</b></div>");
            out.write("</div>");
            out.write("</div>");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading profile");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
