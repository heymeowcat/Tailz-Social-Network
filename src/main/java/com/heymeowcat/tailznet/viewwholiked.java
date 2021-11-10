package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.PostService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "viewwholiked", urlPatterns = {"/viewwholiked"})
public class viewwholiked extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int postid = 0;
            int loggeduid = 0;
            try {
                postid = Integer.parseInt(request.getParameter("q"));
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

            out.write("<i class='material-icons right waves-effect modal-close " + esc(Dcolor) + "'>close</i>");
            out.write("<ul class='" + esc(Acolor) + " collapsible 'style='border-color: " + esc(Ccolor) + "'>");
            out.write("<li class='active'>");
            out.write("<div class='collapsible-header " + esc(Acolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'><b>People Reacted</b></div>");
            out.write("<div class='collapsible-body " + esc(Ecolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'>");

            out.write("<table class='highlight " + esc(Acolor) + "'>");
            PostService postService = new PostService();
            List<Object[]> likers = postService.getPostLikers(postid);
            boolean hasLikes = false;
            if (likers != null) {
                for (Object[] row : likers) {
                    hasLikes = true;
                    String likerId = String.valueOf(row[2]);
                    String likerFirstName = esc((String) row[0]);
                    String likerLastName = esc((String) row[1]);
                    String likerImage = (String) row[3];
                    String escLikerImage = (likerImage == null) ? "img/Profile_avatar_placeholder_large.png" : esc(likerImage);
                    out.write("\n");
                    out.write("                                <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                    out.print(escLikerImage);
                    out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle\" ><h6 >");
                    out.print(likerFirstName + " " + likerLastName);
                    out.write("</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('");
                    out.print(likerId);
                    out.write("', '");
                    out.print(loggeduid);
                    out.write(");$('#peekprofile').modal('open');\" class=\"");
                    out.print(esc(Dcolor));
                    out.write("\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>\n");
                    out.write("                                        ");
                }
            }
            out.write("</table>");
            if (!hasLikes) {
                out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
            }

            out.write("</div>");
            out.write("</li>");
            out.write("</ul>");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading likes");
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
