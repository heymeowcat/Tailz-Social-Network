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

@WebServlet(name = "searchinconfig", urlPatterns = {"/searchinconfig"})
public class searchinconfig extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String s = request.getParameter("name");
            s = "%"+s+"%";
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

            PreparedStatement rsPs = DB.prepare("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `idusers` FROM users WHERE firstname LIKE ? OR lastname LIKE ? OR  concat(firstname,' ',lastname) LIKE ? OR  concat(firstname,lastname) LIKE ? )");
            rsPs.setString(1, s);
            rsPs.setString(2, s);
            rsPs.setString(3, s);
            rsPs.setString(4, s);
            ResultSet rs = rsPs.executeQuery();
            String text = "";
            while (rs.next()) {
                String image = esc(rs.getString(3));
                String firstname = esc(rs.getString(1));
                String lastname = esc(rs.getString(2));
                String idusers = esc(rs.getString(4));
                text += "<tr style='background-color:"+esc(Ccolor)+"' class=animated fadeIn>";
                text += "<td valign='middle' class='left'><img src=" + image + " width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img '></td><td valign='middle'><h6 class='"+esc(Dcolor)+"'>" + firstname + "  " + lastname + "</h6></td><td><a onclick='fullviewprofile("+idusers+")'><i class='material-icons right "+esc(Dcolor)+" waves-effect '>open_in_new</i></a></td>";
                text += "</tr>";
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
