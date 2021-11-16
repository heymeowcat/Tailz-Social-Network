package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.FollowService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "newsuggfollow", urlPatterns = {"/newsuggfollow"})
public class newsuggfollow extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int loggeduid = 0;
            int x = 0;
            try {
                loggeduid = Integer.parseInt(request.getParameter("loggedid"));
                x = Integer.parseInt(request.getParameter("x"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(loggeduid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Dcolor = themeColors[3];

            FollowService followService = new FollowService();
            followService.follow(loggeduid, x);

            List<Object[]> suggestions = followService.getSuggestedUsers(loggeduid);
            boolean hasSuggestions = false;
            if (suggestions != null) {
                for (Object[] row : suggestions) {
                    hasSuggestions = true;
                    String firstname = esc((String) row[0]);
                    String lastname = esc((String) row[1]);
                    String image = esc((String) row[2]);
                    String idusers = esc(String.valueOf(row[3]));

                    out.write("<div class='col s6 m3 l2 animated fadeIn'>");
                    out.write("<div class='"+esc(Acolor)+" card-panel hoverable'>");
                    out.write("<img src=" + image + " class='circle responsive-img'>");
                    out.write("<div class='card-content center " + esc(Dcolor) + "'>");
                    out.write("<p class='truncate'>" + firstname + " " + lastname + "</p>");
                    out.write("<a class=' btn "+esc(Bcolor)+" "+esc(Dcolor)+"  waves-effect' onclick='followthissugg('"+idusers+"')'><i class='material-icons'>person_add</i></a>");
                    out.write("</div>");
                    out.write("</div>");
                    out.write("</div>");
                }
            }

            if (!hasSuggestions) {
                out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading suggestions");
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
