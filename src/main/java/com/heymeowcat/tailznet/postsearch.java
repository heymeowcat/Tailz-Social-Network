package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.UserPrivacy;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.UserPrivacyService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "postsearch", urlPatterns = {"/postsearch"})
public class postsearch extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not logged in");
                return;
            }
            int uid = 0;
            try {
                uid = Integer.parseInt(session.getAttribute("user").toString());
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user session");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            String s = request.getParameter("name");
            if (s == null) s = "";
            String ss = ENCDEC.encrypt(s, new KEY().secretKey);
            if (!s.isEmpty()) {
                s = "%" + s + "%";
            } else {
                s = "%%";
            }

            PostService postService = new PostService();
            List<Object[]> results = postService.searchPostsWithDetails(ss, s);

            if (results != null) {
                for (Object[] row : results) {
                    String postId = String.valueOf(row[0]);
                    String postType = String.valueOf(row[1]);
                    String postHeading = esc(ENCDEC.decrypt((String) row[2], new KEY().secretKey));
                    String postImage = esc(ENCDEC.decrypt((String) row[4], new KEY().secretKey));
                    String postDetail = esc(ENCDEC.decrypt((String) row[3], new KEY().secretKey));
                    String postAuthorId = String.valueOf(row[7]);
                    String authorFirstName = esc((String) row[8]);
                    String authorLastName = esc((String) row[9]);
                    String authorImage = esc((String) row[10]);

                    out.write("<div class='col s12 m6 l4'>");
                    out.write("    <div class='" + esc(Acolor) + " " + esc(Dcolor) + " card small hoverable'>");

                    if (postType.equals("1")) {
                        out.write("        <div class='card-image'>");
                        out.write("            <img class='responsive-img' src='" + postImage + "'>");
                        out.write("        </div>");
                    } else if (postType.equals("2")) {
                        out.write("        <div class='card-image'>");
                        out.write(postImage);
                        out.write("        </div>");
                    }

                    out.write("        <div class='card-content'>");
                    out.write("            <span class='card-title " + esc(Dcolor) + "'><b class='truncate " + esc(Dcolor) + "'>" + postHeading + "</b><i class='material-icons right waves-effect " + esc(Dcolor) + "'>more_vert</i></span>");
                    out.write("            <p class='truncate'>" + postDetail + "</p>");
                    out.write("        </div>");

                    out.write("        <div class='" + esc(Dcolor) + " " + esc(Acolor) + " card-reveal'>");
                    out.write("            <span class='card-title " + esc(Dcolor) + " text-darken-4 truncate'>" + postHeading + "<i class='material-icons right waves-effect'>close</i></span>");
                    out.write("            <div class='card-content'>");
                    out.write("                <a onclick=\"showprofile('" + postAuthorId + "', '" + uid + "');$('#peekprofile').modal('open');\">");
                    out.write("                    <div class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                    out.write("                        <img src='" + authorImage + "'>" + authorFirstName + " " + authorLastName);
                    out.write("                    </div>");
                    out.write("                </a>");
                    out.write("            </div>");
                    out.write("            <div class='card-action'>");

                    boolean liked = postService.hasUserLiked(Integer.parseInt(postId), uid);
                    if (!liked) {
                        out.write("                <label class='toggle seedling-flower'>");
                        out.write("                    <input type='checkbox' class='toggle-checkbox' onchange=\"like('" + postId + "', '" + uid + "')\">");
                        out.write("                    <div class='toggle-btn'></div>");
                        out.write("                </label>");
                    } else {
                        out.write("                <label class='toggle seedling-flower'>");
                        out.write("                    <input type='checkbox' checked='' class='toggle-checkbox' onchange=\"like('" + postId + "', '" + uid + "')\">");
                        out.write("                    <div class='toggle-btn'></div>");
                        out.write("                </label>");
                    }

                    out.write("                <i class='material-icons right waves-effect waves-circle waves-light' onclick=\"$('#opncmnts').modal('open'); showpostcmnts('" + uid + "', '" + postId + "')\">open_in_new</i>");
                    out.write("            </div>");
                    out.write("        </div>");
                    out.write("    </div>");
                    out.write("</div>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error searching posts");
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
