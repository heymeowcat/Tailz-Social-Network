package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.FollowService;
import com.heymeowcat.tailznet.service.PostService;
import com.heymeowcat.tailznet.service.UserPrivacyService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import com.heymeowcat.tailznet.entities.User;
import com.heymeowcat.tailznet.entities.UserPrivacy;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "profilefullview", urlPatterns = {"/profilefullview"})
public class profilefullview extends HttpServlet {

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int loggeduid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("q"));
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

            UserService userService = new UserService();
            String fn = esc(userService.getUserFirstName(uid));
            String ln = esc(userService.getUserLastName(uid));

            UserProfilePicService picService = new UserProfilePicService();
            String up = picService.getProfilePicPath(uid);

            FollowService followService = new FollowService();
            PostService postService = new PostService();
            out.write("<i class='material-icons right waves-effect modal-close " + esc(Dcolor) + "'>close</i>");
            out.write("<ul class='" + esc(Acolor) + " collapsible 'style='border-color: " + esc(Ccolor) + "'>");
            out.write("<li class='active'>");
            out.write("<div class='" + esc(Acolor) + " collapsible-header' style='border-color: " + esc(Ccolor) + "'><b class='" + esc(Dcolor) + "'>Profile</b></div>");
            out.write("<div class='" + esc(Ecolor) + " collapsible-body' style='border-color: " + esc(Ccolor) + "'>");
            out.write("<div class='center'>");
            out.write("<img style='height: 150px; width: 150px' src=' " + up + " '  class='circle responsive-img hide-on-small-and-down animated fadeIn'>");
            out.write("<img style='height: 100px; width: 100px' src='" + up + "'  class='circle responsive-img hide-on-med-and-up animated fadeIn'>");
            out.write("<br>");
            out.write("<h4 class='hide-on-small-and-down'>" + fn + " " + ln + "</h4>");
            out.write("<h5 class='hide-on-med-and-up'>" + fn + " " + ln + "</h5>");
            out.write("<div id ='followun'>");
            try {
                if (loggeduid == uid) {

                } else {
                    boolean isFollowing = followService.isFollowing(loggeduid, uid);
                    if (isFollowing) {
                        out.write("<a onclick='unfollow(" + loggeduid + "," + uid + "); refreshhhh()' class='" + esc(Dcolor) + " " + esc(Bcolor) + " btn-small' >Unfollow</a>");
                    } else {
                        out.write("<a onclick='follow(" + loggeduid + "," + uid + "); refreshhhh()' class='" + esc(Dcolor) + " " + esc(Bcolor) + " btn-small' >Follow</a>");
                    }
                }
                out.write("</div>");
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("</div>");
            out.write("<br>");
            out.write("<br>");
            String usrpostcount = String.valueOf(postService.getPostCount(uid));
            String followercount = String.valueOf(followService.getFollowerCount(uid));
            String followingcount = String.valueOf(followService.getFollowingCount(uid));
            out.write("<div class='row center'>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Posts</span><br><b>" + usrpostcount + "</b></div>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Followers</span><br><b>" + followercount + "</b></div>");
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Following</span><br><b>" + followingcount + "</b></div>");
            out.write("</div>");
            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='" + esc(Acolor) + " " + esc(Dcolor) + " collapsible-header' style='border-color: " + esc(Ccolor) + "'><b>Posts</b></div>");
            out.write("<div class='" + esc(Ecolor) + " collapsible-body' style='border-color: " + esc(Ccolor) + "' >");

            UserPrivacyService privacyService = new UserPrivacyService();
            UserPrivacy privacy = privacyService.getUserPrivacy(uid);
            boolean isPrivate = privacy != null && "private".equals(privacy.getPrivacyName());
            if (isPrivate) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<div class='row'>");
                    String postsSql = "SELECT p.*, u.firstname, u.lastname, upp.image, " +
                            "DATE(p.post_time) as post_date, TIME(p.post_time) as post_time_val " +
                            "FROM `post` p " +
                            "JOIN `users` u ON p.users_idusers = u.idusers " +
                            "JOIN `user_profile_pic` upp ON p.users_idusers = upp.users_idusers " +
                            "WHERE p.users_idusers = ? ORDER BY p.post_time DESC";
                    PreparedStatement rsPs = DB.prepare(postsSql);
                    rsPs.setInt(1, uid);
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
                            out.write("<div class='col s12 m6 l4'>");
                            out.write("<div class='" + esc(Acolor) + " " + esc(Dcolor) + " card small hoverable'>");
                            out.write("<div class='card-image'>");
                            out.write("<img class='responsive-img' src='" + postImage + "'>");
                            out.write("</div>");
                            out.write("<div class='card-content'>");
                            out.write("<span class='card-title " + esc(Dcolor) + "'><b class='truncate " + esc(Dcolor) + "'>" + postHeading + "</b><i class='material-icons right activator waves-effect  " + esc(Dcolor) + "'>more_vert</i></span>");
                            out.write("<p class='truncate'>" + postDetail + "</p>");
                            out.write("</div>");
                            out.write("<div class='" + esc(Acolor) + " " + esc(Dcolor) + "  card-reveal'>");
                            out.write("<span class='card-title " + esc(Dcolor) + " text-darken-4 truncate'>" + postHeading + "<i class='material-icons right waves-effect'>close</i></span>");
                            out.write("<div class='card-content'>");
                            out.write("<a>");
                            out.write("<div class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                            out.write("<img src='" + authorImage + "'>");
                            out.write(" " + authorFirstName + " " + authorLastName + " ");
                            out.write("</div></a>");
                            out.write("<div class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                            out.write("Date: " + postDate + "");
                            out.write("</div>");
                            out.write("<div class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                            out.write("Time:  " + postTime + " ");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("<div class='card-action'>");

                            try {
                                PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                                likechechPs.setInt(1, uid);
                                likechechPs.setString(2, postId);
                                ResultSet likechech = likechechPs.executeQuery();
                                if (!likechech.isBeforeFirst()) {
                                    out.write("<label class='toggle seedling-flower'>");
                                    out.write("<input type='checkbox' class='toggle-checkbox' onchange='like('" + postId + "', '" + uid + "')'>");
                                    out.write("<div class='toggle-btn'></div>");
                                    out.write("</label>");
                                } else if (likechech.next()) {
                                    out.write("<label class='toggle seedling-flower'>");
                                    out.write("<input type='checkbox' checked='' class='toggle-checkbox' onchange='like('" + postId + "', '" + uid + "')'>");
                                    out.write("<div class='toggle-btn'></div>");
                                    out.write("</label>");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            out.write("<i class='material-icons right waves-effect waves-circle waves-light' onclick='$('#opncmnts').modal('open'); showpostcmnts('" + uid + "', '" + postId + "')'>open_in_new</i>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                        } else if (postType.equals("2")) {

                            out.write("<div class='col s12 m6 l4'>");
                            out.write("<div class='" + esc(Acolor) + " " + esc(Dcolor) + " card small hoverable'>");
                            out.write("                                    <div class=\"card-image\">\n");
                            out.write("                                       ");
                            out.print(postImageRaw);
                            out.write("\n");
                            out.write("                                    </div>\n");
                            out.write("<div class='card-content'>");
                            out.write("<span class='card-title " + esc(Dcolor) + "'><b class='truncate " + esc(Dcolor) + "'>" + postHeading + "</b><i class='material-icons right activator waves-effect " + esc(Dcolor) + "'>more_vert</i></span>");
                            out.write("<p class='truncate'>" + postDetail + "</p>");
                            out.write("</div>");
                            out.write("<div class='" + esc(Acolor) + " " + esc(Dcolor) + " card-reveal'>");
                            out.write("<i class='material-icons right waves-effect card-title " + esc(Dcolor) + "'>close</i>");
                            out.write("<span class='card-title " + esc(Dcolor) + " text-darken-4 truncate'>" + postHeading + "</span>");
                            out.write("<div class='card-content'>");
                            out.write("<div  class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");

                            out.write("<img src='" + authorImage + "'>");
                            out.write(" " + authorFirstName + " " + authorLastName + " ");
                            out.write("</div>");
                            out.write("<div  class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                            out.write("Date: " + postDate + "");
                            out.write("</div>");
                            out.write("<div class='" + esc(Bcolor) + " " + esc(Dcolor) + " chip waves-effect waves-light'>");
                            out.write("Time:  " + postTime + "");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("<div class='card-action'>");

                            try {
                                PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                                likechechPs.setInt(1, uid);
                                likechechPs.setString(2, postId);
                                ResultSet likechech = likechechPs.executeQuery();
                                if (!likechech.isBeforeFirst()) {
                                    out.write("<label class='toggle seedling-flower'>");
                                    out.write("<input type='checkbox' class='toggle-checkbox' onchange='like('" + postId + "', '" + uid + "')'>");
                                    out.write("<div class='toggle-btn'></div>");
                                    out.write("</label>");

                                } else if (likechech.next()) {
                                    out.write("<label class='toggle seedling-flower'>");
                                    out.write("<input type='checkbox' checked='' class='toggle-checkbox' onchange='like('" + postId + "', '" + uid + "')'>");
                                    out.write("<div class='toggle-btn'></div>");
                                    out.write("</label>");
                                }

                                out.write("<i class='material-icons right waves-effect waves-circle waves-light' onclick='$('#opncmnts').modal('open'); showpostcmnts('" + uid + "', '" + postId + "')'>open_in_new</i>");
                                out.write(" </div>");
                                out.write("</div>");
                                out.write("</div>");
                                out.write("</div>");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if (!hasPosts) {
                        out.write("<div class='center'><img src='img/no-feeds.png' class='responsiveimg' ></div>");
                    }
                    out.write("</div>");
                }

            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='collapsible-header " + esc(Acolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'><b>Followers</b></div>");
            out.write("<div class='collapsible-body " + esc(Ecolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'>");

            if (isPrivate) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<table class='highlight " + esc(Acolor) + "'>");
                    String followersSql = "SELECT DISTINCT u.firstname, u.lastname, u.idusers, upp.image " +
                            "FROM `follow` f " +
                            "JOIN `users` u ON f.sender = u.idusers " +
                            "LEFT JOIN `user_profile_pic` upp ON u.idusers = upp.users_idusers " +
                            "WHERE f.receiver = ?";
                    PreparedStatement senderidsPs = DB.prepare(followersSql);
                    senderidsPs.setInt(1, uid);
                    ResultSet senderids = senderidsPs.executeQuery();
                    boolean hasFollowers = false;
                    while (senderids.next()) {
                        hasFollowers = true;
                        String followerId = senderids.getString("idusers");
                        String followerFirstName = esc(senderids.getString("firstname"));
                        String followerLastName = esc(senderids.getString("lastname"));
                        String followerImage = senderids.getString("image");
                        String escFollowerImage = (followerImage == null) ? "img/Profile_avatar_placeholder_large.png" : esc(followerImage);
                        out.write("\n");
                        out.write("                                <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                        out.print(escFollowerImage);
                        out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle\" ><h6 >");
                        out.print(followerFirstName + " " + followerLastName);
                        out.write("</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('");
                        out.print(followerId);
                        out.write("', '");
                        out.print(loggeduid);
                        out.write("');$('#peekprofile').modal('open');\" class=\"");
                        out.print(esc(Dcolor));
                        out.write("\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>\n");
                        out.write("                                        ");
                    }
                    out.write("</table>");
                    if (!hasFollowers) {
                        out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
                    }
                }

            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='collapsible-header " + esc(Acolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'><b>Following</b></div>");
            out.write("<div class='collapsible-body " + esc(Ecolor) + " " + esc(Dcolor) + "' style='border-color: " + esc(Ccolor) + "'>");
            if (isPrivate) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<table class='highlight " + esc(Acolor) + "'>");

                    String followingSql = "SELECT DISTINCT u.firstname, u.lastname, u.idusers, upp.image " +
                            "FROM `follow` f " +
                            "JOIN `users` u ON f.receiver = u.idusers " +
                            "LEFT JOIN `user_profile_pic` upp ON u.idusers = upp.users_idusers " +
                            "WHERE f.sender = ?";
                    PreparedStatement senderidssPs = DB.prepare(followingSql);
                    senderidssPs.setInt(1, uid);
                    ResultSet senderidss = senderidssPs.executeQuery();
                    boolean hasFollowing = false;
                    while (senderidss.next()) {
                        hasFollowing = true;
                        String followId = senderidss.getString("idusers");
                        String followFirstName = esc(senderidss.getString("firstname"));
                        String followLastName = esc(senderidss.getString("lastname"));
                        String followImage = senderidss.getString("image");
                        String escFollowImage = (followImage == null) ? "img/Profile_avatar_placeholder_large.png" : esc(followImage);
                        out.write("<tr><td  valign='middle' class='left'><img src='" + escFollowImage + "' width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img  animated fadeIn'></td><td valign='middle' ><h6 >" + followFirstName + " " + followLastName + "</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('" + followId + "', '" + loggeduid + "');$('#peekprofile').modal('open');\" class=\"" + esc(Dcolor) + "\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>");
                    }
                    out.write("</table>");
                    if (!hasFollowing) {
                        out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
                    }
                }

            out.write("</div>");
            out.write("</li>");
            out.write("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading profile");
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
