package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "profilesearch", urlPatterns = {"/profilesearch"})
public class profilesearch extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String s = request.getParameter("name");
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not logged in");
                return;
            }
            int loggeduid = 0;
            try {
                loggeduid = Integer.parseInt(session.getAttribute("user").toString());
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user session");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(loggeduid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];
            if (s != null && !s.isEmpty()) {
                s = "%" + s + "%";
            } else {
                s = "%%";
            }

            UserService userService = new UserService();
            UserProfilePicService picService = new UserProfilePicService();
            List<User> results = userService.searchUsers(s.replace("%", ""));

            StringBuilder text = new StringBuilder();
            if (results != null) {
                for (User user : results) {
                    String pic = picService.getProfilePicPath(user.getId());
                    text.append("<tr class='animated fadeIn'>");
                    text.append("<td valign='middle' class='left'><img src='").append(esc(pic)).append("' width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img'></td>");
                    text.append("<td valign='middle'><h6 class='").append(esc(Dcolor)).append("'>").append(esc(user.getFirstName())).append("  ").append(esc(user.getLastName())).append("</h6></td>");

                    text.append("<td><a onclick='fullviewprofile(").append(user.getId()).append(")'><i class='material-icons right ").append(esc(Dcolor)).append(" waves-effect '>open_in_new</i></a></td>");
                    text.append("</tr>");
                }
            }
            out.write(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error searching profiles");
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
