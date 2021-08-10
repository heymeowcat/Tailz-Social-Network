package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
            String fn = request.getParameter("fn");
            String ln = request.getParameter("ln");
            if (fn == null || ln == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing name parameters");
                return;
            }
            String fp = "img/Profile_avatar_placeholder_large.png";

            PreparedStatement updName = DB.prepare(
                    "UPDATE users SET firstname=?, lastname=? WHERE idusers=?");
            updName.setString(1, fn);
            updName.setString(2, ln);
            updName.setInt(3, uid);
            updName.executeUpdate();

            PreparedStatement insPic = DB.prepare(
                    "INSERT INTO user_profile_pic (image, users_idusers) VALUES (?, ?)");
            insPic.setString(1, fp);
            insPic.setInt(2, uid);
            insPic.executeUpdate();

            PreparedStatement insTheme = DB.prepare(
                    "INSERT INTO app_theme (themename, users_idusers) VALUES ('purplelight', ?)");
            insTheme.setInt(1, uid);
            insTheme.executeUpdate();

            PreparedStatement insLayout = DB.prepare(
                    "INSERT INTO app_layout (users_idusers, layout) VALUES (?, 1)");
            insLayout.setInt(1, uid);
            insLayout.executeUpdate();

            PreparedStatement insUap = DB.prepare(
                    "INSERT INTO uap (Preference, users_idusers) VALUES ('1', ?)");
            insUap.setInt(1, uid);
            insUap.executeUpdate();

            PreparedStatement insPrivacy = DB.prepare(
                    "INSERT INTO user_privacy (privacy_name, users_idusers) VALUES ('public', ?)");
            insPrivacy.setInt(1, uid);
            insPrivacy.executeUpdate();

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
