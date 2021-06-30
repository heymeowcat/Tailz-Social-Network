/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "profilefullview", urlPatterns = {"/profilefullview"})
public class profilefullview extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = Integer.parseInt(request.getParameter("q"));
            int loggeduid = Integer.parseInt(request.getParameter("loggedusr"));
            String Acolor = "";
            String Bcolor = "";
            String Ccolor = "";
            String Dcolor = "";
            String Ecolor = "";
            PreparedStatement themersPs = DB.prepare("Select themename from app_theme where users_idusers= ?");
            themersPs.setInt(1, loggeduid);
            ResultSet themers = themersPs.executeQuery();
            if (themers.next()) {
                if (themers.getString(1).equals("pinkdark")) {
                    Acolor = "black";
                    Bcolor = "pink";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("pinklight")) {
                    Acolor = "white";
                    Bcolor = "pink lighten-4";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "red lighten-5";
                } else if (themers.getString(1).equals("bluelight")) {
                    Acolor = "white";
                    Bcolor = "light-blue lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "light-blue lighten-5";
                } else if (themers.getString(1).equals("bluedark")) {
                    Acolor = "black";
                    Bcolor = "blue";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("yellowlight")) {
                    Acolor = "white";
                    Bcolor = "yellow lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "yellow lighten-4";
                } else if (themers.getString(1).equals("yellowdark")) {
                    Acolor = "black";
                    Bcolor = "yellow darken-4";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("greenlight")) {
                    Acolor = "white";
                    Bcolor = "light-green lighten-2";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "light-green lighten-4";
                } else if (themers.getString(1).equals("greendark")) {
                    Acolor = "black";
                    Bcolor = "green";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                } else if (themers.getString(1).equals("purplelight")) {
                    Acolor = "white";
                    Bcolor = "purple lighten-3";
                    Ccolor = "#f7f4f4";
                    Dcolor = "black-text";
                    Ecolor = "purple lighten-5";
                } else if (themers.getString(1).equals("purpledark")) {
                    Acolor = "black";
                    Bcolor = "purple";
                    Ccolor = "#1c1c1c";
                    Dcolor = "white-text";
                    Ecolor = "grey darken-4";
                }
            }
            String up = "";
            String fn = "";
            String ln = "";
            PreparedStatement uflPs = DB.prepare("Select firstname,lastname FROM `users` where idusers=?");
            uflPs.setInt(1, uid);
            ResultSet ufl = uflPs.executeQuery();
            if (ufl.next()) {
                fn = ufl.getString(1);
                ln = ufl.getString(2);
            }

            String usrpostcount = "";
            String followercount = "";
            String followingcount = "";
            PreparedStatement uspPs = DB.prepare("Select image FROM `user_profile_pic` where users_idusers=?");
            uspPs.setInt(1, uid);
            ResultSet usp = uspPs.executeQuery();
            if (!usp.isBeforeFirst()) {
                up = "img/Profile_avatar_placeholder_large.png";
            } else if (usp.next()) {
                up = usp.getString(1);
            }
            out.write("<i class='material-icons right waves-effect modal-close " + Dcolor + "'>close</i>");
            out.write("<ul class='" + Acolor + " collapsible 'style='border-color: " + Ccolor + "'>");
            out.write("<li class='active'>");
            out.write("<div class='" + Acolor + " collapsible-header' style='border-color: " + Ccolor + "'><b class='" + Dcolor + "'>Profile</b></div>");
            out.write("<div class='" + Ecolor + " collapsible-body' style='border-color: " + Ccolor + "'>");
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
                    PreparedStatement iffillowPs = DB.prepare("SELECT receiver FROM `follow` WHERE `sender`=? AND receiver=?");
                    iffillowPs.setInt(1, loggeduid);
                    iffillowPs.setInt(2, uid);
                    ResultSet iffillow = iffillowPs.executeQuery();
                    if (iffillow.next()) {
                        out.write("<a onclick='unfollow(" + loggeduid + "," + uid + "); refreshhhh()' class='" + Dcolor + " " + Bcolor + " btn-small' >Unfollow</a>");
                    } else {
                        out.write("<a onclick='follow(" + loggeduid + "," + uid + "); refreshhhh()' class='" + Dcolor + " " + Bcolor + " btn-small' >Follow</a>");
                    }
                }
                out.write("</div>");
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("</div>");
            out.write("<br>");
            out.write("<br>");
            out.write("<div class='row center'>");
            try {
                PreparedStatement postcountPs = DB.prepare("SELECT COUNT(`users_idusers`) FROM `post` WHERE `users_idusers`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    usrpostcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Posts</span><br><b>" + usrpostcount + "</b></div>");
            try {
                PreparedStatement postcountPs = DB.prepare("SELECT DISTINCT COUNT(`sender`) FROM `follow` WHERE `receiver`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    followercount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Followers</span><br><b>" + followercount + "</b></div>");
            try {
                PreparedStatement postcountPs = DB.prepare("SELECT DISTINCT COUNT(`receiver`) FROM `follow` WHERE `sender`=?");
                postcountPs.setInt(1, uid);
                ResultSet postcount = postcountPs.executeQuery();
                if (postcount.next()) {
                    followingcount = postcount.getString(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.write("<div class='col s4 waves-effect'> <span class='transparent '>Following</span><br><b>" + followingcount + "</b></div>");
            out.write("</div>");
            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='" + Acolor + " " + Dcolor + " collapsible-header' style='border-color: " + Ccolor + "'><b>Posts</b></div>");
            out.write("<div class='" + Ecolor + " collapsible-body' style='border-color: " + Ccolor + "' >");

            PreparedStatement privacyrsPs = DB.prepare("SELECT privacy_name FROM `user_privacy` WHERE `users_idusers`=?");
            privacyrsPs.setInt(1, uid);
            ResultSet privacyrs = privacyrsPs.executeQuery();
            if (privacyrs.next()) {
                if (privacyrs.getString(1).equals("private")) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<div class='row'>");
                    PreparedStatement rsPs = DB.prepare("Select * FROM `post` where users_idusers = ? ORDER BY `post_time` DESC");
                    rsPs.setInt(1, uid);
                    ResultSet rs = rsPs.executeQuery();
                    while (rs.next()) {
                        if (rs.getString(7).equals("1")) {
                            out.write("<div class='col s12 m6 l4'>");
                            out.write("<div class='" + Acolor + " " + Dcolor + " card small hoverable'>");
                            out.write("<div class='card-image'>");
                            out.write("<img class='responsive-img' src='" + ENCDEC.decrypt(rs.getString(3), new KEY().secretKey) + "'>");
                            out.write("</div>");
                            out.write("<div class='card-content'>");
                            out.write("<span class='card-title " + Dcolor + "'><b class='truncate " + Dcolor + "'>" + ENCDEC.decrypt(rs.getString(2), new KEY().secretKey) + "</b><i class='material-icons right activator waves-effect  " + Dcolor + "'>more_vert</i></span>");
                            out.write("<p class='truncate'>" + ENCDEC.decrypt(rs.getString(4), new KEY().secretKey) + "</p>");
                            out.write("</div>");
                            out.write("<div class='" + Acolor + " " + Dcolor + "  card-reveal'>");
                            out.write("<span class='card-title " + Dcolor + " text-darken-4 truncate'>" + ENCDEC.decrypt(rs.getString(2), new KEY().secretKey) + "<i class='material-icons right waves-effect'>close</i></span>");
                            out.write("<div class='card-content'>");
                            out.write("<a>");
                            out.write("<div class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");
                            String imgup = "";
                            String fnamepost = "";
                            String lnamepost = "";
                            String postdate = "";
                            String posttime = "";
                            PreparedStatement imgpostuserPs = DB.prepare("Select image From user_profile_pic where users_idusers=?");
                            imgpostuserPs.setInt(1, rs.getInt(6));
                            ResultSet imgpostuser = imgpostuserPs.executeQuery();
                            if (imgpostuser.next()) {
                                imgup = imgpostuser.getString(1);
                            }
                            PreparedStatement firstimguserPs = DB.prepare("Select firstname From users where idusers=?");
                            firstimguserPs.setInt(1, rs.getInt(6));
                            ResultSet firstimguser = firstimguserPs.executeQuery();
                            if (firstimguser.next()) {
                                fnamepost = firstimguser.getString(1);
                            }
                            PreparedStatement lastimguserPs = DB.prepare("Select lastname From users where idusers=?");
                            lastimguserPs.setInt(1, rs.getInt(6));
                            ResultSet lastimguser = lastimguserPs.executeQuery();
                            if (lastimguser.next()) {
                                lnamepost = lastimguser.getString(1);
                            }
                            PreparedStatement imgdatePs = DB.prepare("Select cast(post_time as date) From post where idpost=?");
                            imgdatePs.setInt(1, rs.getInt(1));
                            ResultSet imgdate = imgdatePs.executeQuery();
                            if (imgdate.next()) {
                                postdate = imgdate.getString(1);
                            }
                            PreparedStatement imgtimePs = DB.prepare("Select cast(post_time as time) From post where idpost=?");
                            imgtimePs.setInt(1, rs.getInt(1));
                            ResultSet imgtime = imgtimePs.executeQuery();
                            if (imgtime.next()) {
                                posttime = imgtime.getString(1);
                            }
                            out.write("<img src='" + imgup + "'>");
                            out.write(" " + fnamepost + " " + lnamepost + " ");
                            out.write("</div></a>");
                            out.write("<div class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");
                            out.write("Date: " + postdate + "");
                            out.write("</div>");
                            out.write("<div class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");
                            out.write("Time:  " + posttime + " ");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("<div class='card-action'>");

                            PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                            likechechPs.setInt(1, uid);
                            likechechPs.setString(2, rs.getString(1));
                            ResultSet likechech = likechechPs.executeQuery();
                            if (!likechech.isBeforeFirst()) {
                                out.write("<label class='toggle seedling-flower'>");
                                out.write("<input type='checkbox' class='toggle-checkbox' onchange='like('" + rs.getString(1) + "', '" + uid + "')'>");
                                out.write("<div class='toggle-btn'></div>");
                                out.write("</label>");
                            } else if (likechech.next()) {
                                out.write("<label class='toggle seedling-flower'>");
                                out.write("<input type='checkbox' checked='' class='toggle-checkbox' onchange='like('" + rs.getString(1) + "', '" + uid + "')'>");
                                out.write("<div class='toggle-btn'></div>");
                                out.write("</label>");
                            }

                            out.write("<i class='material-icons right waves-effect waves-circle waves-light' onclick='$('#opncmnts').modal('open'); showpostcmnts('" + uid + "', '" + rs.getString(1) + "')'>open_in_new</i>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                        } else if (rs.getString(7).equals("2")) {

                            out.write("<div class='col s12 m6 l4'>");
                            out.write("<div class='" + Acolor + " " + Dcolor + " card small hoverable'>");
                            out.write("                                    <div class=\"card-image\">\n");
                            out.write("                                       ");
                            out.print(ENCDEC.decrypt(rs.getString(3), new KEY().secretKey));
                            out.write("\n");
                            out.write("                                    </div>\n");
                            out.write("<div class='card-content'>");
                            out.write("<span class='card-title " + Dcolor + "'><b class='truncate " + Dcolor + "'>" + ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)+ "</b><i class='material-icons right activator waves-effect " + Dcolor + "'>more_vert</i></span>");
                            out.write("<p class='truncate'>" +ENCDEC.decrypt(rs.getString(4), new KEY().secretKey) + "</p>");
                            out.write("</div>");
                            out.write("<div class='" + Acolor + " " + Dcolor + " card-reveal'>");
                            out.write("<i class='material-icons right waves-effect card-title " + Dcolor + "'>close</i>");
                            out.write("<span class='card-title " + Dcolor + " text-darken-4 truncate'>" + ENCDEC.decrypt(rs.getString(2), new KEY().secretKey) + "</span>");
                            out.write("<div class='card-content'>");
                            out.write("<div  class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");

                            String imgup = "";
                            String fnamepost = "";
                            String lnamepost = "";
                            String postdate = "";
                            String posttime = "";
                            PreparedStatement imgpostuserPs = DB.prepare("Select image From user_profile_pic where users_idusers=?");
                            imgpostuserPs.setInt(1, rs.getInt(6));
                            ResultSet imgpostuser = imgpostuserPs.executeQuery();
                            if (imgpostuser.next()) {
                                imgup = imgpostuser.getString(1);
                            }
                            PreparedStatement firstimguserPs = DB.prepare("Select firstname From users where idusers=?");
                            firstimguserPs.setInt(1, rs.getInt(6));
                            ResultSet firstimguser = firstimguserPs.executeQuery();
                            if (firstimguser.next()) {
                                fnamepost = firstimguser.getString(1);
                            }
                            PreparedStatement lastimguserPs = DB.prepare("Select lastname From users where idusers=?");
                            lastimguserPs.setInt(1, rs.getInt(6));
                            ResultSet lastimguser = lastimguserPs.executeQuery();
                            if (lastimguser.next()) {
                                lnamepost = lastimguser.getString(1);
                            }
                            PreparedStatement imgdatePs = DB.prepare("Select cast(post_time as date) From post where idpost=?");
                            imgdatePs.setInt(1, rs.getInt(1));
                            ResultSet imgdate = imgdatePs.executeQuery();
                            if (imgdate.next()) {
                                postdate = imgdate.getString(1);
                            }
                            PreparedStatement imgtimePs = DB.prepare("Select cast(post_time as time) From post where idpost=?");
                            imgtimePs.setInt(1, rs.getInt(1));
                            ResultSet imgtime = imgtimePs.executeQuery();
                            if (imgtime.next()) {
                                posttime = imgtime.getString(1);
                            }
                            out.write("<img src='" + imgup + "'>");
                            out.write(" " + fnamepost + " " + lnamepost + " ");
                            out.write("</div>");
                            out.write("<div  class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");
                            out.write("Date: " + postdate + "");
                            out.write("</div>");
                            out.write("<div class='" + Bcolor + " " + Dcolor + " chip waves-effect waves-light'>");
                            out.write("Time:  " + posttime + "");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("<div class='card-action'>");

                            PreparedStatement likechechPs = DB.prepare("Select likes from post_rank where likedby=? AND `post_rank`.`post_idpost` =?");
                            likechechPs.setInt(1, uid);
                            likechechPs.setString(2, rs.getString(1));
                            ResultSet likechech = likechechPs.executeQuery();
                            if (!likechech.isBeforeFirst()) {
                                out.write("<label class='toggle seedling-flower'>");
                                out.write("<input type='checkbox' class='toggle-checkbox' onchange='like('" + rs.getString(1) + "', '" + uid + "')'>");
                                out.write("<div class='toggle-btn'></div>");
                                out.write("</label>");

                            } else if (likechech.next()) {
                                out.write("<label class='toggle seedling-flower'>");
                                out.write("<input type='checkbox' checked='' class='toggle-checkbox' onchange='like('" + rs.getString(1) + "', '" + uid + "')'>");
                                out.write("<div class='toggle-btn'></div>");
                                out.write("</label>");
                            }

                            out.write("<i class='material-icons right waves-effect waves-circle waves-light' onclick='$('#opncmnts').modal('open'); showpostcmnts('" + uid + "', '" + rs.getString(1) + "')'>open_in_new</i>");
                            out.write(" </div>");
                            out.write("</div>");
                            out.write("</div>");
                            out.write("</div>");
                        }
                    }

                    PreparedStatement noptrsPs = DB.prepare("Select * FROM `post` where users_idusers = ? ORDER BY `post_time` DESC");
                    noptrsPs.setInt(1, uid);
                    ResultSet noptrs = noptrsPs.executeQuery();
                    if (!noptrs.isBeforeFirst()) {
                        out.write("<div class='center'><img src='img/no-feeds.png' class='responsiveimg' ></div>");
                    }
                    out.write("</div>");
                }
            }

            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='collapsible-header " + Acolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'><b>Followers</b></div>");
            out.write("<div class='collapsible-body " + Ecolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'>");

            PreparedStatement privacyrs2Ps = DB.prepare("SELECT privacy_name FROM `user_privacy` WHERE `users_idusers`=?");
            privacyrs2Ps.setInt(1, uid);
            ResultSet privacyrs2 = privacyrs2Ps.executeQuery();
            if (privacyrs2.next()) {
                if (privacyrs2.getString(1).equals("private")) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<table class='highlight " + Acolor + "'>");
                    PreparedStatement senderidsPs = DB.prepare("SELECT DISTINCT `sender` FROM `follow` WHERE `receiver`=?");
                    senderidsPs.setInt(1, uid);
                    ResultSet senderids = senderidsPs.executeQuery();
                    while (senderids.next()) {
                        PreparedStatement senderssPs = DB.prepare("SELECT `firstname`,`lastname`,`idusers` FROM `users` WHERE `idusers`=?");
                        senderssPs.setString(1, senderids.getString(1));
                        ResultSet senderss = senderssPs.executeQuery();
                        while (senderss.next()) {
                            PreparedStatement senderimgPs = DB.prepare("SELECT `image` FROM `user_profile_pic` WHERE `users_idusers`=?");
                            senderimgPs.setString(1, senderss.getString(3));
                            ResultSet senderimg = senderimgPs.executeQuery();
                            while (senderimg.next()) {
                                out.write("\n");
                                out.write("                                <tr><td  valign=\"middle\" class=\"left\"><img src=\"");
                                out.print(senderimg.getString(1));
                                out.write("\" width=\"40px\" height=\"40px\" style=\"padding: 0; margin: 0\" class=\"circle responsive-img  animated fadeIn\"></td><td valign=\"middle\" ><h6 >");
                                out.print(senderss.getString(1) + " " + senderss.getString(2));
                                out.write("</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('");
                                out.print(senderids.getString(1));
                                out.write("', '");
                                out.print(loggeduid);
                                out.write("');$('#peekprofile').modal('open');\" class=\"");
                                out.print(Dcolor);
                                out.write("\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>\n");
                                out.write("                                        ");
                            }
                        }
                    }

                    out.write("</table>");
                    PreparedStatement meowrsPs = DB.prepare("SELECT DISTINCT `sender` FROM `follow` WHERE `receiver`=?");
                    meowrsPs.setInt(1, uid);
                    ResultSet meowrs = meowrsPs.executeQuery();
                    if (!meowrs.isBeforeFirst()) {
                        out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
                    }
                }
            }

            out.write("</div>");
            out.write("</li>");
            out.write("<li>");
            out.write("<div class='collapsible-header " + Acolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'><b>Following</b></div>");
            out.write("<div class='collapsible-body " + Ecolor + " " + Dcolor + "' style='border-color: " + Ccolor + "'>");
            PreparedStatement privacyrs3Ps = DB.prepare("SELECT privacy_name FROM `user_privacy` WHERE `users_idusers`=?");
            privacyrs3Ps.setInt(1, uid);
            ResultSet privacyrs3 = privacyrs3Ps.executeQuery();
            if (privacyrs3.next()) {
                if (privacyrs3.getString(1).equals("private")) {
                    out.write("<div class='row'>");
                    out.write("<div class='center'><img src='img/private.png' class='responsiveimg' ></div>");
                    out.write("</div>");
                } else {
                    out.write("<table class='highlight " + Acolor + "'>");

                    PreparedStatement senderidssPs = DB.prepare("SELECT DISTINCT `receiver` FROM `follow` WHERE `sender`=?");
                    senderidssPs.setInt(1, uid);
                    ResultSet senderidss = senderidssPs.executeQuery();
                    while (senderidss.next()) {
                        PreparedStatement senderssPs = DB.prepare("SELECT `firstname`,`lastname`,`idusers` FROM `users` WHERE `idusers`=?");
                        senderssPs.setString(1, senderidss.getString(1));
                        ResultSet senderss = senderssPs.executeQuery();
                        while (senderss.next()) {
                            PreparedStatement senderimgPs = DB.prepare("SELECT `image` FROM `user_profile_pic` WHERE `users_idusers`=?");
                            senderimgPs.setString(1, senderss.getString(3));
                            ResultSet senderimg = senderimgPs.executeQuery();
                            while (senderimg.next()) {
                                out.write("<tr><td  valign='middle' class='left'><img src='" + senderimg.getString(1) + "' width='40px' height='40px' style='padding: 0; margin: 0' class='circle responsive-img  animated fadeIn'></td><td valign='middle' ><h6 >" + senderss.getString(1) + " " + senderss.getString(2) + "</h6></td><td valign=\"middle\" class=\"right valign-wrapper\"><h6><a onclick=\"showprofile('" + senderidss.getString(1) + "', '" + loggeduid + "');$('#peekprofile').modal('open');\" class=\"" + Dcolor + "\"><i class=\"material-icons waves-effect\">open_in_new</i></a></h6></td></tr>");
                            }
                        }
                    }

                    out.write("</table>");
                    PreparedStatement coolrsPs = DB.prepare("SELECT DISTINCT `receiver` FROM `follow` WHERE `sender`=?");
                    coolrsPs.setInt(1, uid);
                    ResultSet coolrs = coolrsPs.executeQuery();
                    if (!coolrs.isBeforeFirst()) {
                        out.write("<div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>");
                    }
                }
            }

            out.write("</div>");
            out.write("</li>");
            out.write("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
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
