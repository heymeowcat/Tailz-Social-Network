package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "google", urlPatterns = {"/google"})
public class google extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String idParam = request.getParameter("id");
            String fullname = request.getParameter("name");
            String profilepic = request.getParameter("profilepic");
            String newemail = request.getParameter("email");
            if (idParam == null || fullname == null || profilepic == null || newemail == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
                return;
            }

            String id = DigestUtils.md5Hex(idParam);
            String[] nameparts = fullname.split("\\s+");

            UserService userService = new UserService();
            UserProfilePicService picService = new UserProfilePicService();

            if (!userService.isEmailActive(newemail)) {
                int uid = userService.createGoogleUserFull(newemail, id, esc(nameparts[0]),
                        esc(nameparts.length > 1 ? nameparts[1] : ""), profilepic);

                HttpSession ses = request.getSession();
                ses.setAttribute("user", uid);
                out.write("<div class='fixed-action-btn'>");
                out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                out.write("<i class='large material-icons black-text'>skip_next</i>");
                out.write("</a>");
                out.write("</div>");
            } else {
                User user = userService.getUserByEmail(newemail);
                if (user != null) {
                    int uid = user.getId();
                    picService.updateProfilePic(uid, profilepic + "?sz=180");
                    userService.updateUserName(uid, esc(nameparts[0]), esc(nameparts.length > 1 ? nameparts[1] : ""));

                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", uid);
                    out.write("<div class='fixed-action-btn'>");
                    out.write("<a class='btn-floating btn-large purple lighten-4' href='index.jsp'>");
                    out.write("<i class='large material-icons black-text'>skip_next</i>");
                    out.write("</a>");
                    out.write("</div>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing Google login");
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
