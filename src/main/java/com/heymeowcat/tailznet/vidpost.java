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

            int privacy = 1;
            PreparedStatement privacyrsPs = DB.prepare("SELECT `privacy_name` FROM user_privacy WHERE users_idusers=?");
            privacyrsPs.setInt(1, uid);
            ResultSet privacyrs = privacyrsPs.executeQuery();
            if (privacyrs.next()) {
                if (privacyrs.getString(1).equals("private")) {
                    privacy = 2;
                }
            }

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String fp = request.getParameter("link");
            if (!fp.equals("<iframe class=\"ifr\" src=\"//www.youtube.com/embed/error\" frameborder=\"0\" allowfullscreen></iframe>")) {
                PreparedStatement postIns = DB.prepare("INSERT INTO `post` ( `post_heading`, `post_img_area`, `post_detial`, `post_time`, `users_idusers`, `post_type_idpost_type`,Post_Privacy) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?, '2', ?)");
                postIns.setString(1, ENCDEC.encrypt(title, new KEY().secretKey));
                postIns.setString(2, ENCDEC.encrypt(fp, new KEY().secretKey));
                postIns.setString(3, ENCDEC.encrypt(description, new KEY().secretKey));
                postIns.setInt(4, uid);
                postIns.setInt(5, privacy);
                postIns.executeUpdate();
            }

            String feedSql = "SELECT p.*, u.firstname, u.lastname, upp.image, " +
                    "DATE(p.post_time) as post_date, TIME(p.post_time) as post_time_val " +
                    "FROM `post` p " +
                    "JOIN `users` u ON p.users_idusers = u.idusers " +
                    "JOIN `user_profile_pic` upp ON p.users_idusers = upp.users_idusers " +
                    "WHERE p.Post_Privacy='1' and p.users_idusers = ANY (SELECT `receiver` FROM follow WHERE sender =? and Post_Privacy='1') OR p.users_idusers = ? and p.Post_Privacy='1' " +
                    "ORDER BY p.post_time DESC";
            PreparedStatement rsPs = DB.prepare(feedSql);
            rsPs.setInt(1, uid);
            rsPs.setInt(2, uid);
            ResultSet rs = rsPs.executeQuery();
            boolean hasPosts = false;
            while (rs.next()) {
                hasPosts = true;
                String postType = rs.getString(7);
                String postId = rs.getString(1);
                String postHeading = esc(ENCDEC.decrypt(rs.getString(2), new KEY().secretKey));
                String postImageRaw = ENCDEC.decrypt(rs.getString(3), new KEY().secretKey);
                String postImage = esc(postImageRaw);
                String postDetail = esc(ENCDEC.decrypt(rs.getString(4), new KEY().secretKey));
                String authorFirstName = esc(rs.getString("firstname"));
                String authorLastName = esc(rs.getString("lastname"));
                String authorImage = esc(rs.getString("image"));
                String postDate = esc(rs.getString("post_date"));
                String postTime = esc(rs.getString("post_time_val"));

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

                    try {
                        PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                        likechechPs.setInt(1, uid);
                        likechechPs.setString(2, postId);
                        ResultSet likechech = likechechPs.executeQuery();
                        if (!likechech.isBeforeFirst()) {

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

                        } else if (likechech.next()) {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    out.write("\n");
                    out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnts('");
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

                    try {
                        PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                        likechechPs.setInt(1, uid);
                        likechechPs.setString(2, postId);
                        ResultSet likechech = likechechPs.executeQuery();
                        if (!likechech.isBeforeFirst()) {

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

                        } else if (likechech.next()) {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    out.write("\n");
                    out.write("                                            <i class=\" material-icons right waves-effect waves-circle waves-light\" onclick=\"$('#opncmnts').modal('open'); showpostcmnts('");
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
