package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.FollowService;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
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
            UserService userService = new UserService();
            String fn = esc(userService.getUserFirstName(uid));
            String ln = esc(userService.getUserLastName(uid));

            UserProfilePicService picService = new UserProfilePicService();
            String up = picService.getProfilePicPath(uid);

            FollowService followService = new FollowService();
            PostService postService = new PostService();
            String usrpostcount = String.valueOf(postService.getPostCount(uid));
            String followercount = String.valueOf(followService.getFollowerCount(uid));
            String followingcount = String.valueOf(followService.getFollowingCount(uid));

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
