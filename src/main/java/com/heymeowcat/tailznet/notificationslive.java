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

@WebServlet(name = "notificationslive", urlPatterns = {"/notificationslive"})
public class notificationslive extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid parameter");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            String esc(String s) {
                if (s == null) return "";
                return s.replace("&", "&").replace("<", "<").replace(">", ">").replace("\"", """);
            }

            // Optimized query with JOIN to avoid N+1
            String sql = "SELECT n.*, u.firstname, u.lastname, upp.image " +
                    "FROM notification n " +
                    "JOIN users u ON n.notificationfrom = u.idusers " +
                    "JOIN user_profile_pic upp ON u.idusers = upp.users_idusers " +
                    "WHERE n.notificationfor = ? " +
                    "ORDER BY n.time DESC";

            PreparedStatement rsPs = DB.prepare(sql);
            rsPs.setInt(1, uid);
            ResultSet rs = rsPs.executeQuery();

            if (!rs.isBeforeFirst()) {
                out.write("<div class='center'><img style='margin-top: 100px' src='img/notifications-silenced.png' class='responsiveimg'></div>");
                out.write("<div class='grey-text center'>Icons made by <a href='https://www.freepik.com/' title='Freepik'>Freepik</a> from <a href='https://www.flaticon.com/' title='Flaticon'>www.flaticon.com</a> is licensed by <a href='http://creativecommons.org/licenses/by/3.0/' title='Creative Commons BY 3.0' target='_blank'>CC 3.0 BY</a>");
            } else {
                out.write("<a class='" + esc(Bcolor) + " " + esc(Dcolor) + " btn' onclick='clearnotifications(" + uid + ")'>Mark All as Read</a>");
            }

            out.write("<ul class='" + esc(Acolor) + " collection' style='border-color: " + esc(Ccolor) + "'>");

            while (rs.next()) {
                String notificationType = rs.getString(4);
                String commenttext = "";
                if (notificationType.equals("1")) {
                    commenttext = "started following you";
                } else if (notificationType.equals("2")) {
                    commenttext = "commented on your post";
                }

                String imgup = esc(rs.getString("image"));
                String fnamepost = esc(rs.getString("firstname"));
                String lnamepost = esc(rs.getString("lastname"));
                String notificationtxt = fnamepost + " " + lnamepost + " " + commenttext;
                String time = esc(rs.getString(5));
                int status = rs.getInt(6);
                int notificationId = rs.getInt(1);
                int target = rs.getInt(7);

                String itemClass = (status == 0) ? (esc(Bcolor) + " " + esc(Dcolor)) : (esc(Acolor) + " " + esc(Dcolor));

                if (notificationType.equals("2")) {
                    out.write("<li class='" + itemClass + " collection-item avatar' style='cursor:pointer;border-color: " + esc(Ccolor) + "'>");
                    out.write("<img src='" + imgup + "' class='circle'>");
                    out.write("<span onclick=\"clearnotification(" + notificationId + ");$('#opncmnts').modal('open'); showpostcmnts(" + uid + ", " + target + ")\" class='title'>" + notificationtxt + "</span>");
                    out.write("<p>" + time + "</p>");
                    out.write("</li>");
                } else {
                    out.write("<li class='" + itemClass + " collection-item avatar' style='border-color: " + esc(Ccolor) + "'>");
                    out.write("<img onclick=\"clearnotification(" + notificationId + ");$('#opncmnts').modal('close'); showprofile(" + rs.getInt(3) + "," + uid + ");$('#peekprofile').modal('open');\" src='" + imgup + "' class='circle'>");
                    out.write("<span class='title'>" + notificationtxt + "</span>");
                    out.write("<p>" + time + "</p>");
                    out.write("</li>");
                }
            }

            out.write("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading notifications");
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
