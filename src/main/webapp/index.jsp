<%-- 
    Document   : index
    Created on : Aug 21, 2018, 11:36:51 PM
    Author     : HEYMEOWCAT
--%>
<%@page import="com.heymeowcat.tailznet.KEY"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.heymeowcat.tailznet.ENCDEC"%>
<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <script src="js/pace.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.css" rel="stylesheet" type="text/css" media="screen,projection"/>
        <%
            if (request.getSession().getAttribute("user") != null) {
                int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
                String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor = "";
                String Lcolor = "";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
                if (themers.next()) {
                    if (themers.getString(1).equals("pinkdark")) {
                        Acolor = "black";
                        Bcolor = "pink";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Lcolor = "#e91e63";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Lcolor = "#f8bbd0";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Lcolor = "#4fc3f7";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Lcolor = "#2196f3";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Lcolor = "#fff176";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Lcolor = "#f57f17";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Lcolor = "#aed581";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Lcolor = "#4caf50";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Lcolor = "#ce93d8";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Lcolor = "#9c27b0";
                    }
                }
        %> 
        <style>
            .pace {
                -webkit-pointer-events: none;
                pointer-events: none;

                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }
            .pace-inactive {
                display: none;
            }
            .pace .pace-progress {
                background: <%=Lcolor%>;
                position: fixed;
                z-index: 2000;
                top: 0;
                right: 100%;
                width: 100%;
                height: 2px;
            }
            .noselect {
                -webkit-touch-callout: none;
                -webkit-user-select: none;
                -khtml-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none; 
                user-select: none;
            }
            ::-webkit-scrollbar {
                width: 0px;
                background: transparent;
            }
            html, body {
                height:100%;
                min-height:100%;
                overflow: hidden;
                background-color: <%=Ccolor%>;
            }
            body {
                display: flex;
                flex-direction: column;
            }
            .StickyHeader, .StickyFooter {
                flex: 0 0 auto;
            }

            .StickyContent {
                flex: 1 1 auto;
                overflow-y: scroll;
                margin-bottom: 0px;
                padding-bottom: 0px;
            }
            .toggle {
                position: absolute;
                font-size: calc(100px);
                cursor: pointer;
            }

            .toggle-btn::after {
                position: absolute;
                font-size: 0.35em;
                text-align: center;
            }
            .seedling-flower .toggle-btn::after {
                content: '😐';
            }
            .seedling-flower .toggle-checkbox:checked + .toggle-btn::after {
                content: '😍';
            }
            iframe{
                padding:0px;
                width: 100%;
                height: 181px;
            }
        </style>
    </head>



    <body onload="hideloader(<%=uid%>);" class="noselect">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%= Acolor%>">
                <div class="nav-wrapper center container " >
                    <div class="row hide-on-small-and-down ">
                        <div class="col s2  waves-effect waves-light  <%=Bcolor%> animated rubberBand" oncontextmenu="$('#opnmore').modal('open');return false;"><a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons  <%=Dcolor%>">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up ">
                        <div class="col s12 ">
                            <a class="brand-logo"><B class="<%=Dcolor%>">TAILZ</B></a>
                        </div>
                    </div>

            </nav>
        </header>

        <main class="StickyContent noselect" style="border: 0px; margin: 0px; bottom: 0px; padding: 0px;">
            <div class="container ">

                <div class="row">

                    <div class="col s12 m6 l8 animated fadeIn" style='height: 87vh; max-height:100% ; overflow: scroll;'>
                        <div class="row" id="postrow">
                            <%
                                try {
                                    java.sql.ResultSet rs = DB.search("Select * FROM `post` where  users_idusers = ANY (SELECT `receiver` FROM follow WHERE sender ='" + uid + "' and Post_Privacy='1') OR users_idusers = '" + uid + "' and Post_Privacy='1'  ORDER BY `post_time` DESC");
                                    while (rs.next()) {
                                        if (rs.getString(7).equals("1")) {%>
                            <div class="col s12 m12 l6 ">
                                <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable" >
                                    <div class="card-image">
                                        <img class="responsive-img" src="<%=ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)%>">
                                    </div>
                                    <div class="card-content" >
                                        <span class="card-title <%=Dcolor%>"><b class="truncate<%=Dcolor%>"><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%></b><i class="material-icons right activator waves-effect  <%=Dcolor%>">more_vert</i></span>    
                                        <p class="truncate"><%=ENCDEC.decrypt(rs.getString(4), new KEY().secretKey)%></p>
                                    </div>
                                    <div class=" <%=Dcolor%> <%=Acolor%>  card-reveal" >
                                        <span class="card-title <%=Dcolor%> text-darken-4 truncate "><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%><i class="material-icons right waves-effect">close</i></span>
                                        <div class="card-content ">
                                            <a onclick="showprofile('<%=rs.getString(6)%>', '<%=uid%>');$('#peekprofile').modal('open');">
                                                <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                    <%
                                                        String imgup = "";
                                                        String fnamepost = "";
                                                        String lnamepost = "";
                                                        String postdate = "";
                                                        String posttime = "";
                                                        java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(6) + "' ");
                                                        if (imgpostuser.next()) {
                                                            imgup = imgpostuser.getString(1);
                                                        }
                                                        java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (firstimguser.next()) {
                                                            fnamepost = firstimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (lastimguser.next()) {
                                                            lnamepost = lastimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet imgdate = DB.search("Select cast(post_time as date) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgdate.next()) {
                                                            postdate = imgdate.getString(1);
                                                        }
                                                        java.sql.ResultSet imgtime = DB.search("Select cast(post_time as time) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgtime.next()) {
                                                            posttime = imgtime.getString(1);
                                                        }
                                                    %>
                                                    <img src="<%=imgup%>">
                                                    <%= fnamepost + " " + lnamepost%>
                                                </div></a>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Date: <%=postdate%>
                                            </div>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Time:  <%=posttime%>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <%
                                                try {
                                                    java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                                                    if (!likechech.isBeforeFirst()) {
                                            %>
                                            <label class="toggle seedling-flower" oncontextmenu="showwholiked('<%=rs.getString(1)%>', '<%=uid%>');$('#peekwholikesthis').modal('open');return false;">
                                                <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label> 
                                            <%
                                            } else if (likechech.next()) {%>
                                            <label class="toggle seedling-flower" oncontextmenu="showwholiked('<%=rs.getString(1)%>', '<%=uid%>');$('#peekwholikesthis').modal('open');return false;">
                                                <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                            <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
                                            <%
                                                try {
                                                    java.sql.ResultSet bookmarkrs = DB.search("Select post_idpost from user_bookmarks where users_idusers='" + uid + "'  AND `user_bookmarks`.`post_idpost` ='" + rs.getString(1) + "'  ");
                                                    if (!bookmarkrs.isBeforeFirst()) {
                                            %>
                                            <i id="bookmark<%=rs.getString(1)%>" class=" material-icons right waves-effect waves-circle waves-light" onclick="bookmarkthis('<%=uid%>', '<%=rs.getString(1)%>')">bookmark_border</i>
                                            <%
                                            } else if (bookmarkrs.next()) {%>
                                            <i id="bookmark<%=rs.getString(1)%>" class=" material-icons right waves-effect waves-circle waves-light" onclick="bookmarkthis('<%=uid%>', '<%=rs.getString(1)%>')">bookmark</i>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%} else if (rs.getString(7).equals("2")) {%>
                            <div class="col s12 m12 l6 ">
                                <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable ">
                                    <div class="card-image mater">
                                        <%=ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)%>
                                    </div>
                                    <div class="card-content">
                                        <span class="card-title <%=Dcolor%>"><b class="truncate <%=Dcolor%>"><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%></b><i class="material-icons right activator waves-effect <%=Dcolor%>">more_vert</i></span>    
                                        <p class="truncate"><%=ENCDEC.decrypt(rs.getString(4), new KEY().secretKey)%></p>
                                    </div>
                                    <div class="<%=Acolor%> <%=Dcolor%> card-reveal">
                                        <i class="material-icons right waves-effect card-title <%=Dcolor%>">close</i>
                                        <span class="card-title <%=Dcolor%> text-darken-4 truncate"><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%></span>
                                        <div class="card-content ">
                                            <a onclick="showprofile('<%=rs.getString(6)%>', '<%=uid%>');$('#peekprofile').modal('open');">
                                                <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                    <%
                                                        String imgup = "";
                                                        String fnamepost = "";
                                                        String lnamepost = "";
                                                        String postdate = "";
                                                        String posttime = "";
                                                        java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(6) + "' ");
                                                        if (imgpostuser.next()) {
                                                            imgup = imgpostuser.getString(1);
                                                        }
                                                        java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (firstimguser.next()) {
                                                            fnamepost = firstimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (lastimguser.next()) {
                                                            lnamepost = lastimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet imgdate = DB.search("Select cast(post_time as date) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgdate.next()) {
                                                            postdate = imgdate.getString(1);
                                                        }
                                                        java.sql.ResultSet imgtime = DB.search("Select cast(post_time as time) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgtime.next()) {
                                                            posttime = imgtime.getString(1);
                                                        }
                                                    %>
                                                    <img src="<%=imgup%>">
                                                    <%= fnamepost + " " + lnamepost%>
                                                </div></a>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Date: <%=postdate%>
                                            </div>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Time:  <%=posttime%>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <%
                                                try {
                                                    java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                                                    if (!likechech.isBeforeFirst()) {
                                            %>
                                            <label class="toggle seedling-flower" oncontextmenu="showwholiked('<%=rs.getString(1)%>', '<%=uid%>');$('#peekwholikesthis').modal('open');return false;">
                                                <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                            } else if (likechech.next()) {%>
                                            <label class="toggle seedling-flower" oncontextmenu="showwholiked('<%=rs.getString(1)%>', '<%=uid%>');$('#peekwholikesthis').modal('open');return false;">
                                                <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                            <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
                                            <%
                                                try {
                                                    java.sql.ResultSet bookmarkrs = DB.search("Select post_idpost from user_bookmarks where users_idusers='" + uid + "'  AND `user_bookmarks`.`post_idpost` ='" + rs.getString(1) + "'  ");
                                                    if (!bookmarkrs.isBeforeFirst()) {
                                            %>
                                            <i id="bookmark<%=rs.getString(1)%>" class=" material-icons right waves-effect waves-circle waves-light" onclick="bookmarkthis('<%=uid%>', '<%=rs.getString(1)%>')">bookmark_border</i>
                                            <%
                                            } else if (bookmarkrs.next()) {%>
                                            <i id="bookmark<%=rs.getString(1)%>" class=" material-icons right waves-effect waves-circle waves-light" onclick="bookmarkthis('<%=uid%>', '<%=rs.getString(1)%>')">bookmark</i>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            %>
                            <% java.sql.ResultSet noptrs = DB.search("Select * FROM `post` where  users_idusers = ANY (SELECT `receiver` FROM follow WHERE sender ='" + uid + "' and Post_Privacy='1') OR users_idusers = '" + uid + "' and Post_Privacy='1'  ORDER BY `post_time` DESC");
                                if (!noptrs.isBeforeFirst()) {
                            %>
                            <div class='center'><img src='img/no-feeds.png' style="margin-top: 100px" class='responsiveimg ' ></div>
                                <%
                                    }
                                %>
                        </div>
                    </div>

                    <div  class="col s12 m6 l4" style='height: 87vh; min-height:100% ; overflow: scroll'>
                        <div class="row" id="adshow" style="margin-bottom: 0px">


                        </div>
                        <div class="<%=Acolor%> card col s12 m12">
                            <div class='card-content'>
                                <span class='<%=Dcolor%> card-title'>Who to Follow</span>
                                <table id="followsuggestionsid" >
                                    <%
                                        boolean m = false;
                                        java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "') limit 5");
                                        while (rs.next()) {
                                            m = true;
                                    %>
                                    <tr style="cursor: pointer" ><td  valign="middle" class="left" onclick="showprofile('<%=rs.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');"><img src="<%=rs.getString(3)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td onclick="showprofile('<%=rs.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');" valign="middle " ><div class="<%=Dcolor%>"><%=rs.getString(1)%> <%=rs.getString(2)%></div></td><td valign="middle" class="right valign-wrapper"><h6><a class=" btn <%=Bcolor%> <%=Dcolor%> waves-effect" onclick="followthissugg('<%=rs.getString(4)%>')"><i class="material-icons">person_add</i></a></h6></td></tr>
                                            <%
                                                }
                                            %>
                                            <%if (m) {%>
                                    <tr style="border-color: transparent"><td class="<%=Dcolor%>"><a class="<%=Dcolor%>" href="search-trending.jsp">More...</a></td></tr>
                                    <%}%>

                                </table>
                                <% java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "')");
                                    if (!rsop.isBeforeFirst()) {
                                %>
                                <div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <div class="grey-text">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
                        <div class="green-text"><a href="about.jsp">About this App</a></div>
                        <div class="grey-text">Made by <a href="https://www.facebook.com/heymeowcat/">heymeowcat</a></div>
                    </div>
                </div>
            </div>
            <div  class ="fixed-action-btn hide-on-med-and-up" style="bottom:60px; right: 5px;" >
                <a class="btn-floating btn-large <%=Bcolor%>" >       
                    <i class="large material-icons <%=Dcolor%>">mode_edit</i>
                </a>
                <ul>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnimgpost').modal('open');">image</i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="fab fa-youtube <%=Dcolor%>" onclick="$('#opnvidpost').modal('open');"></i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnembedpost').modal('open');">insert_link</i></a></li>
                </ul>
            </div>
            <div class="fixed-action-btn hide-on-small-and-down">
                <a class="btn-floating btn-large <%=Bcolor%>">
                    <i class="large material-icons <%=Dcolor%>">mode_edit</i>
                </a>
                <ul>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnimgpost').modal('open');">image</i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="fab fa-youtube <%=Dcolor%>" onclick="$('#opnvidpost').modal('open');"></i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnembedpost').modal('open');">insert_link</i></a></li>
                </ul>
            </div>

            <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
                <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

                </div>
            </div>

            <div id="peekwholikesthis" class="modal bottom-sheet card" style="background-color: <%=Ccolor%>">

                <div id="wholikesthissec" class="<%=Bcolor%> <%=Dcolor%> container">

                </div>
            </div>


            <div id="opncmnts" class="modal bottom-sheet card" style="min-height:100vh; background-color: <%=Ccolor%>">
                <div id="peekcomments" class="<%=Bcolor%> <%=Dcolor%> container ">

                </div>
            </div>
            <div id="opnmore" class="modal bottom-sheet card" style="background-color: <%=Ccolor%>">
                <div id="peekcommentss" class="<%=Bcolor%> <%=Dcolor%> container">
                    <ul class="<%=Acolor%> <%=Dcolor%> collapsible" style="border-color: <%=Ccolor%>">
                        <li class="active">
                            <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><i class="material-icons">filter_list</i>Search Post</div>
                            <div class="<%=Acolor%> collapsible-body" style="border-color: <%=Ccolor%>">

                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="opnembedpost" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
                <div class="container">
                    <div class="<%=Acolor%> card-panel">
                        <h5 class="<%=Dcolor%>">New Embed Post <i class="material-icons right waves-effect modal-close">close</i></h5>
                        <form action="embedpost" method="POST" >
                            <div class="row">
                                <div class="input-field col s12 ">
                                    <textarea name="link"  class="materialize-textarea validate <%=Dcolor%>" required placeholder="Embed Text"></textarea>
                                </div>
                                <div class="input-field col s12">
                                    <input name="title" id="embedtitletext" placeholder="Title" name="title" type="text" class="validate <%=Dcolor%>" required>
                                </div>
                                <div class="input-field col s12">
                                    <input  id="embeddescriptiontext" placeholder="Description" name="description"  type="text" class="validate <%=Dcolor%>" required>
                                </div>
                            </div>
                            <input type="submit" value="Post" class="<%=Bcolor%> <%=Dcolor%> btn">

                        </form>
                    </div>
                </div>
            </div>
            <div id="opnvidpost" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
                <div class="container">
                    <div class="<%=Acolor%> card-panel">
                        <h5 class="<%=Dcolor%>">New Youtube Video Post <i class="material-icons right waves-effect modal-close">close</i></h5>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input id="videolinktext" placeholder="Youtube Link" name="link"  type="text" class="validate <%=Dcolor%>" required>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="videotitletext" placeholder="Title" name="title" type="text" class="validate <%=Dcolor%>" required>
                            </div>
                            <div class="input-field col s12">
                                <input id="videodescriptiontext" placeholder="Description" name="description"  type="text" class="validate <%=Dcolor%>" required>
                            </div>
                        </div>
                        <button class="<%=Bcolor%> <%=Dcolor%> btn" id="postvidbtn" onclick="vidpost();">Post</button>
                    </div>
                </div>
            </div>
            <div id="opnimgpost" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
                <div class="container">
                    <div class="<%=Acolor%> card-panel">
                        <h5 class="<%=Dcolor%>">New Image Post <i class="material-icons right waves-effect modal-close">close</i></h5>
                        <div class="row">
                            <div class="file-field input-field col s12 m6">
                                <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                    <span>File</span>
                                    <input id="imgfile" type="file" class="imgur" accept="image/*" data-max-size="5000" required=""/>
                                </div>
                                <div class="file-path-wrapper ">
                                    <input id="filepathimg" class="file-path validate <%=Dcolor%>" type="text"  >
                                </div>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="titletext" placeholder="Title" name="title" type="text" class="validate <%=Dcolor%>" required>
                            </div>
                            <div class="input-field col s12">
                                <input id="descriptiontext" placeholder="Description" name="description"  type="text" class="validate <%=Dcolor%>" required>
                            </div>
                        </div>
                        <button class="<%=Bcolor%> <%=Dcolor%> btn" id="imgpostbtn" onclick="imgpost();">Post</button>

                    </div>

                </div>
            </div>

            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/materialize.js"></script>

            <script>
                            var selectedpid;
                            var userid;
                            function hideloader(x) {
                                userid = x
                                numberrefresh();
                                chatnumberrefresh();
                                adsrefresh();
                            }
                            $(document).ready(function () {
                                $('.collapsible').collapsible();
                                $('#opncmnts').modal();
                                $('#opnvidpost').modal();
                                $('#opnimgpost').modal();
                                $('#opnembedpost').modal();
                                $('#peekprofile').modal();
                                $('#peekwholikesthis').modal();
                                $('#opnmore').modal();
                                $('.materialboxed').materialbox();
                                $('.fixed-action-btn').floatingActionButton();
                                $("body").on("contextmenu", function (e) {
                                    return false;
                                });
                            });
                            function showprofile(str, loggedurs) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("profilepeek").innerHTML = this.responseText;
                                        $('.collapsible').collapsible();
                                    }
                                };
                                xhttp.open("GET", "profilefullview?q=" + str + "&loggedusr=" + loggedurs, true);
                                xhttp.send();
                            }
                            function showwholiked(str, loggedurs) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("wholikesthissec").innerHTML = this.responseText;
                                        $('.collapsible').collapsible();
                                    }
                                };
                                xhttp.open("GET", "viewwholiked?q=" + str + "&loggedusr=" + loggedurs, true);
                                xhttp.send();
                            }
                            function follow(x, y) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {

                                    }
                                };
                                xhttp.open("GET", "follow?x=" + x + "&y=" + y, true);
                                xhttp.send();
                            }
                            function unfollow(x, y) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {

                                    }
                                };
                                xhttp.open("GET", "unfollow?x=" + x + "&y=" + y, true);
                                xhttp.send();
                            }
                            function refreshhhh() {
                                window.location.replace("index.jsp");
                            }

                            function getId(url) {
                                var regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
                                var match = url.match(regExp);

                                if (match && match[2].length == 11) {
                                    return match[2];
                                } else {
                                    return 'error';
                                }
                            }

                            var myId;
                            function vidpost() {
                                var myUrl = document.getElementById("videolinktext").value;
                                myId = getId(myUrl);
                                var code = '<iframe src="//www.youtube.com/embed/' + myId + '" frameborder="0" allowfullscreen></iframe>';
                                var y = document.getElementById("videotitletext").value;
                                var z = document.getElementById("videodescriptiontext").value;
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("postrow").innerHTML = this.responseText;
                                        $('.materialboxed').materialbox();
                                        document.getElementById("videolinktext").value = null;
                                        document.getElementById("videotitletext").value = null;
                                        document.getElementById("videodescriptiontext").value = null;
                                        $('#opnvidpost').modal().destroy();
                                    }
                                };
                                xhttp.open("GET", "vidpost?uid=" + userid + "&title=" + y + "&description=" + z + "&link=" + code, true);
                                xhttp.send();
                            }


                            function imgpost() {
                                var $files = $('#imgfile').get(0).files;
                                var fp;
                                if ($files.length) {
                                    // Reject big files
                                    if ($files[0].size > $(this).data("max-size") * 1024) {
                                        console.log("Please select a smaller file");
                                        return false;
                                    }
                                    // Begin file upload
                                    console.log("Uploading file to Imgur..");

                                    // Replace ctrlq with your own API key
                                    var apiUrl = 'https://api.imgur.com/3/image';
                                    var apiKey = 'Bearer c9b33c9056e4378e365513146667e74f40cb9684';

                                    var settings = {
                                        async: false,
                                        crossDomain: true,
                                        processData: false,
                                        contentType: false,
                                        type: 'POST',
                                        url: apiUrl,
                                        headers: {
                                            Authorization: '01dd5cfeb1621b4' + apiKey,
                                            Accept: 'application/json'
                                        },
                                        mimeType: 'multipart/form-data'
                                    };

                                    var formData = new FormData();
                                    formData.append("image", $files[0]);
                                    settings.data = formData;

                                    // Response contains stringified JSON
                                    // Image URL available at response.data.link
                                    $.ajax(settings).done(function (response) {
                                        var obj = JSON.parse(response);
                                        fp = obj.data.link;
                                    });

                                }
                                var x = document.getElementById("titletext").value;
                                var y = document.getElementById("descriptiontext").value;
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("postrow").innerHTML = this.responseText;
                                        $('.materialboxed').materialbox();
                                        document.getElementById("titletext").value = null;
                                        document.getElementById("descriptiontext").value = null;
                                        document.getElementById("imgfile").value = null;
                                        $('#opnimgpost').modal().destroy();
                                    }
                                };
                                xhttp.open("GET", "newpost?uid=" + userid + "&title=" + x + "&description=" + y + "&fp=" + fp, true);
                                xhttp.send();

                            }

                            function like(x, uid) {
                                setTimeout(function () {
                                    var xhttp;
                                    xhttp = new XMLHttpRequest();
                                    xhttp.onreadystatechange = function () {
                                        if (this.readyState == 4 && this.status == 200) {
                                            //document.getElementById("profilepeek").innerHTML = this.responseText;
                                        }
                                    };
                                    xhttp.open("GET", "likeprocess?x=" + x + "&uid=" + uid, true);
                                    xhttp.send();
                                }, 1000);

                            }
                            function bookmarkthis(uid, x) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("bookmark" + x).innerHTML = this.responseText;
                                    }
                                };
                                xhttp.open("GET", "bookmarkprocess?x=" + x + "&uid=" + uid, true);
                                xhttp.send();

                            }

                            function showpostcmnts(uid, pid) {
                                selectedpid = pid;
                                userid = uid;
                                refresh()
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("peekcomments").innerHTML = this.responseText;
                                        $('.materialboxed').materialbox();
                                    }
                                };
                                xhttp.open("GET", "peekcomments?uid=" + uid + "&pid=" + pid, true);
                                xhttp.send();
                            }

                            function comment(x, y) {
                                var z = document.getElementById("commenttextarea").value;
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("commentsection").innerHTML = this.responseText;
                                        document.getElementById("commenttextarea").value = "";
                                    }
                                };
                                xhttp.open("GET", "commentsload?x=" + x + "&y=" + y + "&z=" + z, true);
                                xhttp.send();
                            }

                            function refresh() {
                                setTimeout(function () {
                                    $('#commentsection').load("commentlive?uid=" + userid + "?&pid=" + selectedpid);
                                    refresh();
                                }, 1000);
                            }
                            function numberrefresh() {
                                setTimeout(function () {
                                    $('#notificationnumber').load("notificationnumber?uid=" + userid);
                                    $('#notificationnumber2').load("notificationnumber?uid=" + userid);
                                    numberrefresh();
                                }, 1000);
                            }
                            function chatnumberrefresh() {
                                setTimeout(function () {
                                    $('#chatnumber').load("chatnumber?uid=" + userid);
                                    $('#chatnumber2').load("chatnumber?uid=" + userid);
                                    chatnumberrefresh();
                                }, 1000);
                            }
                            function adsrefresh() {
                                setTimeout(function () {
                                    $('#adshow').load("finaladdsforindexrefresh");
                                    adsrefresh();
                                }, 1000);
                            }

                            function followthissugg(x) {
                                var xhtp = new XMLHttpRequest();
                                xhtp.onreadystatechange = function () {
                                    if (xhtp.readyState == 4 && xhtp.status == 200) {
                                        document.getElementById("followsuggestionsid").innerHTML = xhtp.responseText;
                                    }
                                }
                                xhtp.open("GET", "newsuggfollowindex?x=" + x + "&loggedid=" + userid, true);
                                xhtp.send();
                            }
            </script> 
        </main>

        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light  <%=Bcolor%> animated rubberBand" oncontextmenu="$('#opnmore').modal('open');return false;">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
        <%} else {
                Cookie[] cookies = request.getCookies();
                boolean b = false;
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        Cookie c = cookies[i];
                        if (c.getName().equals("MEOWID")) {
                            HttpSession ses = request.getSession();
                            String decryptedString = ENCDEC.decrypt(c.getValue(), new KEY().secretKey);
                            ses.setAttribute("user", decryptedString);
                            b = true;
                            response.sendRedirect("index.jsp");
                        }
                    }
                }
                if (!b) {
                    response.sendRedirect("login-register.jsp");
                }
            }
        %>
    </body>
</html>
