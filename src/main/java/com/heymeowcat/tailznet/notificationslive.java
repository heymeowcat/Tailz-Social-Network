package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.NotificationService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "notificationslive", urlPatterns = {"/notificationslive"})
public class notificationslive extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


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

            NotificationService notificationService = new NotificationService();
            List<Object[]> notifications = notificationService.getUserNotificationsWithDetails(uid);

            if (notifications == null || notifications.isEmpty()) {
                out.write("<div class='center'><img style='margin-top: 100px' src='img/notifications-silenced.png' class='responsiveimg'></div>");
                out.write("<div class='grey-text center'>Icons made by <a href='https://www.freepik.com/' title='Freepik'>Freepik</a> from <a href='https://www.flaticon.com/' title='Flaticon'>www.flaticon.com</a> is licensed by <a href='http://creativecommons.org/licenses/by/3.0/' title='Creative Commons BY 3.0' target='_blank'>CC 3.0 BY</a>");
            } else {
                out.write("<a class='" + esc(Bcolor) + " " + esc(Dcolor) + " btn' onclick='clearnotifications(" + uid + ")'>Mark All as Read</a>");
            }

            out.write("<ul class='" + esc(Acolor) + " collection' style='border-color: " + esc(Ccolor) + "'>");

            for (Object[] row : notifications) {
                Integer notificationId = (Integer) row[0];
                String notificationType = (String) row[1];
                String status = (String) row[2];
                String timeStr = (String) row[3];
                Integer target = (Integer) row[4];
                Integer notifFrom = (Integer) row[5];
                String firstname = (String) row[6];
                String lastname = (String) row[7];
                String image = (String) row[8];

                String commenttext = "";
                if (notificationType.equals("1")) {
                    commenttext = "started following you";
                } else if (notificationType.equals("2")) {
                    commenttext = "commented on your post";
                }

                String imgup = esc(image);
                String fnamepost = esc(firstname);
                String lnamepost = esc(lastname);
                String notificationtxt = fnamepost + " " + lnamepost + " " + commenttext;
                String time = esc(timeStr);
                int intStatus = status != null ? Integer.parseInt(status) : 0;
                int intNotificationId = notificationId != null ? notificationId : 0;
                int intTarget = target != null ? target : 0;
                int intNotifFrom = notifFrom != null ? notifFrom : 0;

                String itemClass = (intStatus == 0) ? (esc(Bcolor) + " " + esc(Dcolor)) : (esc(Acolor) + " " + esc(Dcolor));

                if (notificationType.equals("2")) {
                    out.write("<li class='" + itemClass + " collection-item avatar' style='cursor:pointer;border-color: " + esc(Ccolor) + "'>");
                    out.write("<img src='" + imgup + "' class='circle'>");
                    out.write("<span onclick=\"clearnotification(" + intNotificationId + ");$('#opncmnts').modal('open'); showpostcmnts(" + uid + ", " + intTarget + ")\" class='title'>" + notificationtxt + "</span>");
                    out.write("<p>" + time + "</p>");
                    out.write("</li>");
                } else {
                    out.write("<li class='" + itemClass + " collection-item avatar' style='border-color: " + esc(Ccolor) + "'>");
                    out.write("<img onclick=\"clearnotification(" + intNotificationId + ");$('#opncmnts').modal('close'); showprofile(" + intNotifFrom + "," + uid + ");$('#peekprofile').modal('open');\" src='" + imgup + "' class='circle'>");
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
