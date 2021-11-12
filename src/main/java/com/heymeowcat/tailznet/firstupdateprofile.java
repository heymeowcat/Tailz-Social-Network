package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.entities.UserProfilePic;
import com.heymeowcat.tailznet.entities.UserPrivacy;
import com.heymeowcat.tailznet.entities.AppTheme;
import com.heymeowcat.tailznet.service.AppLayoutService;
import com.heymeowcat.tailznet.service.AppThemeService;
import com.heymeowcat.tailznet.service.UserPrivacyService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "firstupdateprofile", urlPatterns = {"/firstupdateprofile"})
public class firstupdateprofile extends HttpServlet {

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
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID");
                return;
            }
            String fn = esc(request.getParameter("fn"));
            String ln = esc(request.getParameter("ln"));
            if (fn == null || ln == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing name parameters");
                return;
            }
            String fp = "img/Profile_avatar_placeholder_large.png";

            UserService userService = new UserService();
            User user = userService.getUserById(uid);
            if (user != null) {
                user.setFirstName(fn);
                user.setLastName(ln);
                userService.updateUser(user);
            }

            UserProfilePicService picService = new UserProfilePicService();
            picService.updateProfilePic(uid, fp);

            AppThemeService themeService = new AppThemeService();
            themeService.upsertTheme(uid, "purplelight");

            AppLayoutService layoutService = new AppLayoutService();
            layoutService.saveLayout(uid);

            UserPrivacyService privacyService = new UserPrivacyService();
            privacyService.upsertPrivacy(uid, "public");

            response.sendRedirect("login-register.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating profile");
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
