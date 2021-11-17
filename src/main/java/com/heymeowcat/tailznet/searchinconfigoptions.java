package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.service.UserService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "searchinconfigoptions", urlPatterns = {"/searchinconfigoptions"})
public class searchinconfigoptions extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String s = request.getParameter("name");
            int loggeduid = 0;
            try {
                loggeduid = Integer.parseInt(request.getParameter("loggedid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(loggeduid);
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];

            UserService userService = new UserService();
            UserProfilePicService picService = new UserProfilePicService();
            List<User> results = userService.searchUsers(s);

            String text = "";
            if (results != null) {
                for (User user : results) {
                    String image = esc(picService.getProfilePicPath(user.getId()));
                    String firstname = esc(user.getFirstName());
                    String lastname = esc(user.getLastName());
                    String idusers = esc(String.valueOf(user.getId()));
                    text += "<tr style='background-color:"+esc(Ccolor)+"' class=animated fadeIn>";
                    text += "<td valign='middle' class='left'><img src=" + image + " width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img '></td><td valign='middle'><h6 class='"+esc(Dcolor)+"'>" + firstname + "  " + lastname + "</h6></td><td><a onclick='fullviewprofile("+idusers+")'><i class='material-icons right "+esc(Dcolor)+" waves-effect '>open_in_new</i></a></td>";
                    text += "</tr>";
                }
            }
            out.write(text);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error searching users");
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
