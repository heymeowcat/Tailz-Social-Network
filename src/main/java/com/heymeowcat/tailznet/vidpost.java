package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.Post;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.UserPrivacyService;
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

@WebServlet(name = "vidpost", urlPatterns = {"/vidpost"})
public class vidpost extends HttpServlet {

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
                uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
            } catch (NumberFormatException | NullPointerException e) {
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

            UserPrivacyService privacyService = new UserPrivacyService();
            PostService postService = new PostService();
            UserService userService = new UserService();
            UserProfilePicService profilePicService = new UserProfilePicService();

            String privacyName = privacyService.getPrivacyName(uid);
            int privacy = privacyName.equals("private") ? 2 : 1;

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String fp = request.getParameter("link");
            if (fp != null && !fp.equals("<iframe class=\"ifr\" src=\"//www.youtube.com/embed/error\" frameborder=\"0\" allowfullscreen></iframe>")) {
                Post post = new Post();
                post.setHeading(ENCDEC.encrypt(title, new KEY().secretKey));
                post.setImage(ENCDEC.encrypt(fp, new KEY().secretKey));
                post.setDetail(ENCDEC.encrypt(description, new KEY().secretKey));
                post.setUserId(uid);
                post.setPostTypeId(2);
                post.setPostPrivacy(privacy);
                postService.savePost(post);
            }

            List<Object[]> feed = postService.getFeedForUser(uid);
            boolean hasPosts = false;
            if (feed != null) {
                for (Object[] row : feed) {
                    hasPosts = true;
                    String postType = String.valueOf(row[6]);
                    String postId = String.valueOf(row[0]);
                    String postHeading = esc(ENCDEC.decrypt((String) row[1], new KEY().secretKey));
                    String postImageRaw = ENCDEC.decrypt((String) row[2], new KEY().secretKey);
                    String postImage = esc(postImageRaw);
                    String postDetail = esc(ENCDEC.decrypt((String) row[3], new KEY().secretKey));
                    String authorFirstName = esc((String) row[8]);
                    String authorLastName = esc((String) row[9]);
                    String authorImage = esc((String) row[10]);
                    String postDate = esc((String) row[11]);
                    String postTime = esc((String) row[12]);

                    if (postType.equals("1")) {
                        out.write("\n");
                        out.write("                            <div class=\"col s12 m12 l6 \">\n");
                        out.write("                                <div class=\" ");
                        out.print(esc(Acolor));
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" card small hoverable\" >\n");
                        out.write("                                    <div class=\"card-image\">\n");
                        out.write("                                        <img class=\"responsive-img\" src=\"");
                        out.print(postImage);
                        out.write("\">\n");
                        out.write("                                    </div>\n");
                        out.write("                                    <div class=\"card-content\" >\n");
                        out.write("                                        <span class=\"card-title ");
                        out.print(esc(Dcolor));
                        out.write("\"><b class=\"truncate");
                        out.print(esc(Dcolor));
                        out.write('"');
                        out.write('>');
                        out.print(postHeading);
                        out.write("</b><i class=\"material-icons right activator waves-effect  ");
                        out.print(esc(Dcolor));
                        out.write("\">more_vert</i></span>    \n");
                        out.write("                                        <p class=\"truncate\">");
                        out.print(postDetail);
                        out.write("</p>\n");
                        out.write("                                    </div>\n");
                        out.write("                                    <div class=\" ");
                        out.print(esc(Dcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Acolor));
                        out.write("  card-reveal\" >\n");
                        out.write("                                        <span class=\"card-title ");
                        out.print(esc(Dcolor));
                        out.write(" text-darken-4 truncate \">");
                        out.print(postHeading);
                        out.write("<i class=\"material-icons right waves-effect\">close</i></span>\n");
                        out.write("                                        <div class=\"card-content \">\n");
                        out.write("                                            <a onclick=\"showprofile('");
                        out.print(postId);
                        out.write("', '");
                        out.print(uid);
                        out.write("');$('#peekprofile').modal('open');\">\n");
                        out.write("                                                <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                    ");

                        out.write("\n");
                        out.write("                                                    <img src=\"");
                        out.print(authorImage);
                        out.write("\">\n");
                        out.write("                                                    ");
                        out.print(authorFirstName + " " + authorLastName);
                        out.write("\n");
                        out.write("                                                </div></a>\n");
                        out.write("                                            <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                Date: ");
                        out.print(postDate);
                        out.write("\n");
                        out.write("                                            </div>\n");
                        out.write("                                            <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                Time:  ");
                        out.print(postTime);
                        out.write("\n");
                        out.write("                                            </div>\n");
                        out.write("                                        </div>\n");
                        out.write("                                        <div class=\"card-action\">\n");
                        out.write("                                            ");

                        if (!postService.hasUserLiked(Integer.parseInt(postId), uid)) {

                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(postId);
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        } else {
                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" checked=\"\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(postId);
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        }

                        out.write("\n");
                        out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnds('");
                        out.print(uid);
                        out.write("', '");
                        out.print(postId);
                        out.write("')\">open_in_new</i>\n");
                        out.write("                                        </div>\n");
                        out.write("                                    </div>\n");
                        out.write("                                </div>\n");
                        out.write("                            </div>\n");
                        out.write("                            ");
                    } else if (postType.equals("2")) {
                        out.write("\n");
                        out.write("                            <div class=\"col s12 m12 l6 \">\n");
                        out.write("                                <div class=\" ");
                        out.print(esc(Acolor));
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" card small hoverable\">\n");
                        out.write("                                    <div class=\"card-image\">\n");
                        out.write("                                       ");
                        out.print(postImageRaw);
                        out.write("\n");
                        out.write("                                    </div>\n");
                        out.write("                                    <div class=\"card-content\">\n");
                        out.write("                                        <span class=\"card-title ");
                        out.print(esc(Dcolor));
                        out.write("\"><b class=\"truncate ");
                        out.print(esc(Dcolor));
                        out.write('"');
                        out.write('>');
                        out.print(postHeading);
                        out.write("</b><i class=\"material-icons right activator waves-effect ");
                        out.print(esc(Dcolor));
                        out.write("\">more_vert</i></span>    \n");
                        out.write("                                        <p class=\"truncate\">");
                        out.print(postDetail);
                        out.write("</p>\n");
                        out.write("                                    </div>\n");
                        out.write("                                    <div class=\"");
                        out.print(esc(Acolor));
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" card-reveal\">\n");
                        out.write("                                        <i class=\"material-icons right waves-effect card-title ");
                        out.print(esc(Dcolor));
                        out.write("\">close</i>\n");
                        out.write("                                        <span class=\"card-title ");
                        out.print(esc(Dcolor));
                        out.write(" text-darken-4 truncate\">");
                        out.print(postHeading);
                        out.write("</span>\n");
                        out.write("                                        <div class=\"card-content \">\n");
                        out.write("                                            <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                ");

                        out.write("\n");
                        out.write("                                                <img src=\"");
                        out.print(authorImage);
                        out.write("\">\n");
                        out.write("                                                ");
                        out.print(authorFirstName + " " + authorLastName);
                        out.write("\n");
                        out.write("                                            </div>\n");
                        out.write("                                            <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                Date: ");
                        out.print(postDate);
                        out.write("\n");
                        out.write("                                            </div>\n");
                        out.write("                                            <div class=\"");
                        out.print(esc(Bcolor));
                        out.write(' ');
                        out.write(' ');
                        out.print(esc(Dcolor));
                        out.write(" chip waves-effect waves-light \">\n");
                        out.write("                                                Time:  ");
                        out.print(postTime);
                        out.write("\n");
                        out.write("                                            </div>\n");
                        out.write("                                        </div>\n");
                        out.write("                                        <div class=\"card-action\">\n");
                        out.write("                                            ");

                        if (!postService.hasUserLiked(Integer.parseInt(postId), uid)) {
                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(postId);
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        } else {
                            out.write("\n");
                            out.write("                                            <label class=\"toggle seedling-flower\" >\n");
                            out.write("                                                <input type=\"checkbox\" checked=\"\" class=\"toggle-checkbox\" onchange=\"like('");
                            out.print(postId);
                            out.write("', '");
                            out.print(uid);
                            out.write("')\">\n");
                            out.write("                                                <div class=\"toggle-btn\"></div>\n");
                            out.write("                                            </label>\n");
                            out.write("                                            ");

                        }

                        out.write("\n");
                        out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnds('");
                        out.print(uid);
                        out.write("', '");
                        out.print(postId);
                        out.write("')\">open_in_new</i>\n");
                        out.write("                                        </div>\n");
                        out.write("                                    </div>\n");
                        out.write("                                </div>\n");
                        out.write("                            </div>\n");
                        out.write("                            ");
                    }
                }
            }

            out.write("\n");
            out.write("                            ");
            if (!hasPosts) {
                out.write("\n");
                out.write("                            <div class='center'><img src='img/no-feeds.png' style='top: 200px; position:  relative;'  class='responsiveimg ' ></div>\n");
            }
            out.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading feed");
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
