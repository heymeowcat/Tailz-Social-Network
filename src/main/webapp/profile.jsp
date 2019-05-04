<%-- 
    Document   : profile
    Created on : Aug 26, 2018, 3:58:44 PM
    Author     : HEYMEOWCAT
--%>
<%@page import="com.heymeowcat.tailznet.KEY"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.heymeowcat.tailznet.ENCDEC"%>
<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <script>
            window.paceOptions = {
                ajax: false,
                restartOnRequestAfter: false,
            };
        </script>
        <script src="js/pace.js"></script>
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="css/normalize.min.css">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <%
            if (request.getSession().getAttribute("user") != null) {
                int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
                String fn = "";
                String ln = "";
                String usrpostcount = "";
                String followercount = "";
                String followingcount = "";
                java.sql.ResultSet ufl = DB.search("Select firstname,lastname FROM `users` where idusers='" + uid + "' ");
                if (ufl.next()) {
                    fn = ufl.getString(1);
                    ln = ufl.getString(2);
                }
                java.sql.ResultSet usp = DB.search("Select image FROM `user_profile_pic` where users_idusers='" + uid + "' ");
                if (!usp.isBeforeFirst()) {
                    up = "img/Profile_avatar_placeholder_large.png";
                } else if (usp.next()) {
                    up = usp.getString(1);
                }
                String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor = "";
                String Fcolor = "";
                String Lcolor = "";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
                if (themers.next()) {
                    if (themers.getString(1).equals("pinkdark")) {
                        Acolor = "black";
                        Bcolor = "pink";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                        Lcolor = "#e91e63";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "black";
                        Lcolor = "#f8bbd0";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "black";
                        Lcolor = "#4fc3f7";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                        Lcolor = "#2196f3";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "black";
                        Lcolor = "#fff176";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                        Lcolor = "#f57f17";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "black";
                        Lcolor = "#aed581";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                        Lcolor = "#4caf50";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "black";
                        Lcolor = "#ce93d8";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
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
                height: 4px;
            }
            .noselect {
                -webkit-touch-callout: none; 
                -webkit-user-select: none; 
                -khtml-user-select: none; 
                -moz-user-select: none; 
                -ms-user-select: none;
                user-select: none; 
            }
            html {
                overflow: scroll;
                overflow-x: hidden;
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

            }

            ul.dropdown-content.select-dropdown li span {
                background-color:  <%=Acolor%>;
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
            ul.dropdown-content.select-dropdown li span {
                background-color:  <%=Acolor%>;
                border-color: <%=Acolor%>;
                color: <%=Fcolor%>;
            }
            input:not([type]), input[type=text]:not(.browser-default), input[type=password]:not(.browser-default), input[type=email]:not(.browser-default), input[type=url]:not(.browser-default), input[type=time]:not(.browser-default), input[type=date]:not(.browser-default), input[type=datetime]:not(.browser-default), input[type=datetime-local]:not(.browser-default), input[type=tel]:not(.browser-default), input[type=number]:not(.browser-default), input[type=search]:not(.browser-default), textarea.materialize-textarea {
                color: <%=Fcolor%>;
            }
            .select-wrapper .caret {
                fill: <%=Fcolor%>;
            }
            .circle-text {
    width:50%;
}
.circle-text:after {
    content: "";
    display: block;
    width: 100%;
    height:0;
    padding-bottom: 100%;
    background: #4679BD; 
    -moz-border-radius: 50%; 
    -webkit-border-radius: 50%; 
    border-radius: 50%;
}
.circle-text div {
    float:left;
    width:100%;
    padding-top:50%;
    line-height:1em;
    margin-top:-0.5em;
    text-align:center;
    color:white;
}
        </style>
    </head>
    <body onload="hideloader(<%=uid%>)" class="noselect">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Acolor%> ">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up">
                        <div class="col s6">
                            <a class="brand-logo"><B class="<%=Dcolor%>">TAILZ</B></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <main class="StickyContent">
            <div class="animated fadeIn container">
                <ul class="<%=Acolor%> collapsible " style="border-color: <%=Ccolor%>">
                    <li class="active" >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Profile</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="center">
                                <img onclick="expandupdateprofile();" id="propiche" style="height: 150px; width: 150px;   cursor: pointer;" src="<%=up%>" alt="" class="circle responsive-img hide-on-small-and-down animated fadeIn">
                                <img onclick="expandupdateprofile();" style="height: 100px; width: 100px;   cursor: pointer;" src="<%=up%>" alt="" class="circle responsive-img hide-on-med-and-up animated fadeIn">
                                <br>
                                <h4 class="hide-on-small-and-down <%=Dcolor%>"><%=fn + " " + ln%></h4>    
                                <h5 class="hide-on-med-and-up <%=Dcolor%>"><%=fn + " " + ln%></h5>    
                            </div>
                            <br>
                            <div class="row center">
                                <%
                                    try {
                                        java.sql.ResultSet postcount = DB.search("SELECT COUNT(`users_idusers`) FROM `post` WHERE `users_idusers`='" + uid + "' ");
                                        if (postcount.next()) {
                                            usrpostcount = postcount.getString(1);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                %>
                                <div  onclick="expandposts();" class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Posts</span><br><b><%=usrpostcount%></b></div>
                                    <%
                                        try {
                                            java.sql.ResultSet postcount = DB.search("SELECT DISTINCT COUNT(`sender`) FROM `follow` WHERE `receiver`='" + uid + "' ");
                                            if (postcount.next()) {
                                                followercount = postcount.getString(1);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                <div onclick="expandfollowers();" class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Followers</span><br><b><%=followercount%></b></div>
                                    <%
                                        try {
                                            java.sql.ResultSet postcount = DB.search("SELECT DISTINCT COUNT(`receiver`) FROM `follow` WHERE `sender`='" + uid + "' ");
                                            if (postcount.next()) {
                                                followingcount = postcount.getString(1);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>
                                <div onclick="expandfollowing();" class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Following</span><br><b><%=followingcount%></b></div>
                            </div>
                        </div>
                    </li>


                    <li>
                        <div class="<%=Acolor%> <%=Dcolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b>Posts</b></div>
                        <div class="<%=Ecolor%> collapsible-body" style="border-color: <%=Ccolor%>;" >
                            <div class="row">
                                <%
                                    try {
                                        java.sql.ResultSet rs = DB.search("Select * FROM `post` where users_idusers = '" + uid + "' ORDER BY `post_time` DESC");
                                        while (rs.next()) {
                                            if (rs.getString(7).equals("1")) {%>
                                <div class="col s12 m6 l4 ">
                                    <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable">
                                        <div class="card-image">
                                            <img class="responsive-img" src="<%=ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)%>">
                                        </div>
                                        <div class="card-content">
                                            <span class="card-title <%=Dcolor%>"><b class="truncate<%=Dcolor%>"><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%></b><i class="material-icons right activator waves-effect  <%=Dcolor%>">more_vert</i></span>    
                                            <p class="truncate"><%=ENCDEC.decrypt(rs.getString(4), new KEY().secretKey)%></p>
                                        </div>
                                        <div class=" <%=Dcolor%> <%=Acolor%>  card-reveal">
                                            <span class="card-title <%=Dcolor%> text-darken-4 truncate "><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%><i class="material-icons right waves-effect">close</i></span>
                                            <div class="card-content ">
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
                                                </div>
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
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>


                                                <%
                                                } else if (likechech.next()) {%>
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>

                                                <%
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                %>
                                                <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#peekdelpost').modal('open'); showdelpost('<%=uid%>', '<%=rs.getString(1)%>')">delete</i>
                                                <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%} else if (rs.getString(7).equals("2")) {%>
                                <div class="col s12 m6 l4 ">
                                    <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable ">
                                        <div class="card-image">
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
                                                </div>
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
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>

                                                <%
                                                } else if (likechech.next()) {%>
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>

                                                <%
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                %>
                                                <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#peekdelpost').modal('open'); showdelpost('<%=uid%>', '<%=rs.getString(1)%>');">delete</i>
                                                <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
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

                                <% java.sql.ResultSet noptrs = DB.search("Select * FROM `post` where users_idusers = '" + uid + "' ORDER BY `post_time` DESC");
                                    if (!noptrs.isBeforeFirst()) {
                                %>

                                <div class='center'><img src='img/no-feeds.png' class='responsiveimg' ></div>
                                    <%
                                        }
                                    %>

                            </div>
                        </div>
                    </li>

                    <li >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Bookmarks</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%
                                    try {
                                        java.sql.ResultSet rs = DB.search("Select * FROM `post` where idpost = ANY (Select post_idpost FROM `user_bookmarks` where users_idusers ='" + uid + "' ORDER BY `user_bookmarks`.`notice_time` DESC)");
                                        while (rs.next()) {
                                            if (rs.getString(7).equals("1")) {%>
                                <div class="col s12 m6 l4 ">
                                    <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable">
                                        <div class="card-image">
                                            <img class="responsive-img" src="<%=ENCDEC.decrypt(rs.getString(3), new KEY().secretKey)%>">
                                        </div>
                                        <div class="card-content">
                                            <span class="card-title <%=Dcolor%>"><b class="truncate<%=Dcolor%>"><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%></b><i class="material-icons right activator waves-effect  <%=Dcolor%>">more_vert</i></span>    
                                            <p class="truncate"><%=ENCDEC.decrypt(rs.getString(4), new KEY().secretKey)%></p>
                                        </div>
                                        <div class=" <%=Dcolor%> <%=Acolor%>  card-reveal">
                                            <span class="card-title <%=Dcolor%> text-darken-4 truncate "><%=ENCDEC.decrypt(rs.getString(2), new KEY().secretKey)%><i class="material-icons right waves-effect">close</i></span>
                                            <div class="card-content ">
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
                                                </div>
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
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>


                                                <%
                                                } else if (likechech.next()) {%>
                                                <label class="toggle seedling-flower" >
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
                                <div class="col s12 m6 l4 ">
                                    <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable ">
                                        <div class="card-image">
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
                                                </div>
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
                                                <label class="toggle seedling-flower" >
                                                    <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                    <div class="toggle-btn"></div>
                                                </label>

                                                <%
                                                } else if (likechech.next()) {%>
                                                <label class="toggle seedling-flower" >
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
                                <% java.sql.ResultSet nobookrs = DB.search("Select * FROM `user_bookmarks` where users_idusers = '" + uid + "' ORDER BY `notice_time` DESC");
                                    if (!nobookrs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src='img/bookmark.png' class='responsiveimg' ></div>
                                    <%
                                        }
                                    %>
                            </div>
                        </div>
                    </li>


                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Followers</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <table class="highlight <%=Acolor%>" >


                                <%
                                    try {
                                        java.sql.ResultSet senderids = DB.search("SELECT DISTINCT `sender` FROM `follow` WHERE `receiver`='" + uid + "' ");
                                        while (senderids.next()) {
                                            java.sql.ResultSet senderss = DB.search("SELECT `firstname`,`lastname`,`idusers` FROM `users` WHERE `idusers`='" + senderids.getString(1) + "'");
                                            while (senderss.next()) {
                                                java.sql.ResultSet senderimg = DB.search("SELECT `image` FROM `user_profile_pic` WHERE `users_idusers`='" + senderss.getString(3) + "'");
                                                while (senderimg.next()) {
                                %>
                                <tr><td  valign="middle" class="left"><img src="<%=senderimg.getString(1)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td valign="middle" ><h6 ><%=senderss.getString(1) + " " + senderss.getString(2)%></h6></td><td valign="middle" class="right valign-wrapper"><h6><a onclick="showprofile('<%=senderids.getString(1)%>', '<%=uid%>');$('#peekprofile').modal('open');" class="<%=Dcolor%>"><i class="material-icons waves-effect">open_in_new</i></a></h6></td></tr>
                                        <%
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        %>

                            </table>
                            <% java.sql.ResultSet meowrs = DB.search("SELECT DISTINCT `sender` FROM `follow` WHERE `receiver`='" + uid + "' ");
                                if (!meowrs.isBeforeFirst()) {
                            %>

                            <div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>
                                <%
                                    }
                                %>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Following</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <table class="highlight <%=Acolor%>" >
                                <%
                                    try {
                                        java.sql.ResultSet senderids = DB.search("SELECT DISTINCT `receiver` FROM `follow` WHERE `sender`='" + uid + "' ");
                                        while (senderids.next()) {
                                            java.sql.ResultSet senderss = DB.search("SELECT `firstname`,`lastname`,`idusers` FROM `users` WHERE `idusers`='" + senderids.getString(1) + "'");
                                            while (senderss.next()) {
                                                java.sql.ResultSet senderimg = DB.search("SELECT `image` FROM `user_profile_pic` WHERE `users_idusers`='" + senderss.getString(3) + "'");
                                                while (senderimg.next()) {
                                %>
                                <tr><td  valign="middle" class="left"><img src="<%=senderimg.getString(1)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td valign="middle" ><h6 ><%=senderss.getString(1) + " " + senderss.getString(2)%></h6></td><td class="right valign-wrapper"><h6><a onclick="showprofile('<%=senderids.getString(1)%>', '<%=uid%>');$('#peekprofile').modal('open');" class="<%=Dcolor%>"><i class="material-icons waves-effect">open_in_new</i></a></h6></td></tr>
                                        <%  }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        %>
                            </table>
                            <% java.sql.ResultSet coolrs = DB.search("SELECT DISTINCT `receiver` FROM `follow` WHERE `sender`='" + uid + "' ");
                                if (!coolrs.isBeforeFirst()) {
                            %>

                            <div class='center'><img src='img/friendship.png' class='responsiveimg ' ></div>
                                <%
                                    }
                                %>
                        </div>
                    </li>

                    <li id="updateprofilesegment">
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Update Profile</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <div class="<%=Acolor%> <%=Dcolor%> card-panel">
                                Change Profile Name
                                <div class="row">
                                    <form method="post"  action="update">
                                        <input name="uid" value="<%=uid%>" type="hidden">
                                        <div class="input-field col s12 m6">
                                            <input checked placeholder="First Name" id="first_name" name="fn" type="text" class="validate <%=Dcolor%>" value="<%=fn%>" minlength="3" required="">
                                        </div>
                                        <div class="input-field col s12 m6">
                                            <input placeholder="Last Name" id="last_name" name="ln" type="text" class="validate <%=Dcolor%>" value="<%=ln%>" minlength="3" required="">
                                        </div>
                                        <input type="submit" value="Update" class="<%=Bcolor%> <%=Dcolor%> btn center">
                                    </form>
                                </div>
                            </div>
                            <div class="<%=Acolor%> <%=Dcolor%> card-panel">
                                Change Profile Picture
                                <div class="file-field input-field">
                                    <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                        <span>Choose Image</span>
                                        <input type="file"  name="file-input" id="imgfile" >
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate <%=Dcolor%>" type="text">
                                    </div>
                                </div>
                                <button class="<%=Bcolor%> <%=Dcolor%> btn" onclick="profilepicupdate();">Update</button><br><br>
                                <a class="<%=Bcolor%> <%=Dcolor%> btn center" href="resetprofilepic?uid=<%=uid%>">Remove</a><br><br>

                            </div>
                        </div>
                    </li>

                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Change Password</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <form action="updatepassword" method="post">
                                <div class="row <%=Acolor%> <%=Dcolor%> card-panel">
                                    <input name="uid" type="hidden" value="<%=uid%>">
                                    <div class="input-field col s12 m6">
                                        <input placeholder="New Password" name="newpsw" type="password" class="validate <%=Dcolor%>" minlength="8">
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Confirm New Name" name="conpsw" type="password" class="validate <%=Dcolor%>" minlength="8">
                                    </div>
                                    <input type="submit" value="Change Password" class="<%=Bcolor%> btn <%=Dcolor%>">
                                </div>

                            </form>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Settings</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <div class="input-field col s12  <%=Acolor%> <%=Dcolor%> card-panel">
                                <B>Change Color Theme</b>
                                    <%
                                        java.sql.ResultSet lightrs = DB.search("SELECT `themename` FROM app_theme WHERE users_idusers='" + uid + "' AND themename LIKE '%light'");
                                        if (lightrs.next()) {
                                    %>
                                <select  id="themeid"  class="<%=Dcolor%>" >
                                    <option value="light" selected class="<%=Dcolor%>">Light</option>
                                    <option value="dark" class="<%=Dcolor%>">Dark</option>
                                </select>
                                <%
                                } else {
                                %>
                                <select  id="themeid"  class="<%=Dcolor%>" >
                                    <option value="light"  class="<%=Dcolor%>">Light</option>
                                    <option value="dark" selected class="<%=Dcolor%>">Dark</option>
                                </select>
                                <%
                                    }
                                %>
                                Change Accent Colors
                                <%
                                    java.sql.ResultSet acccolorrs = DB.search("SELECT `themename` FROM app_theme WHERE users_idusers='" + uid + "'");
                                    if (acccolorrs.next()) {
                                        if (acccolorrs.getString(1).equals("pinklight") || acccolorrs.getString(1).equals("pinkdark")) {
                                %>
                                <select  id="themeaccent"  class="<%=Dcolor%>" >
                                    <option value="pink" selected class="<%=Dcolor%>">Pink</option>
                                    <option value="blue" class="<%=Dcolor%>">Blue</option>
                                    <option value="yellow" class="<%=Dcolor%>">Yellow</option>
                                    <option value="green" class="<%=Dcolor%>">Green</option>
                                    <option value="purple" class="<%=Dcolor%>">Purple</option>
                                </select>
                                <%
                                } else if (acccolorrs.getString(1).equals("bluelight") || acccolorrs.getString(1).equals("bluedark")) {
                                %>
                                <select  id="themeaccent"  class="<%=Dcolor%>" >
                                    <option value="pink"  class="<%=Dcolor%>">Pink</option>
                                    <option value="blue" selected class="<%=Dcolor%>">Blue</option>
                                    <option value="yellow" class="<%=Dcolor%>">Yellow</option>
                                    <option value="green" class="<%=Dcolor%>">Green</option>
                                    <option value="purple" class="<%=Dcolor%>">Purple</option>
                                </select>
                                <%
                                } else if (acccolorrs.getString(1).equals("yellowlight") || acccolorrs.getString(1).equals("yellowdark")) {
                                %>
                                <select  id="themeaccent"  class="<%=Dcolor%>" >
                                    <option value="pink"  class="<%=Dcolor%>">Pink</option>
                                    <option value="blue" class="<%=Dcolor%>">Blue</option>
                                    <option value="yellow" selected class="<%=Dcolor%>">Yellow</option>
                                    <option value="green" class="<%=Dcolor%>">Green</option>
                                    <option value="purple" class="<%=Dcolor%>">Purple</option>
                                </select>
                                <%
                                } else if (acccolorrs.getString(1).equals("greenlight") || acccolorrs.getString(1).equals("greendark")) {
                                %>
                                <select  id="themeaccent"  class="<%=Dcolor%>" >
                                    <option value="pink"  class="<%=Dcolor%>">Pink</option>
                                    <option value="blue" class="<%=Dcolor%>">Blue</option>
                                    <option value="yellow" class="<%=Dcolor%>">Yellow</option>
                                    <option value="green" selected class="<%=Dcolor%>">Green</option>
                                    <option value="purple" class="<%=Dcolor%>">Purple</option>
                                </select>
                                <%
                                } else if (acccolorrs.getString(1).equals("purplelight") || acccolorrs.getString(1).equals("purpledark")) {
                                %>                                
                                <select  id="themeaccent"  class="<%=Dcolor%>" >
                                    <option value="pink"  class="<%=Dcolor%>">Pink</option>
                                    <option value="blue" class="<%=Dcolor%>">Blue</option>
                                    <option value="yellow" class="<%=Dcolor%>">Yellow</option>
                                    <option value="green" class="<%=Dcolor%>">Green</option>
                                    <option value="purple" selected class="<%=Dcolor%>">Purple</option>
                                </select>
                                <%
                                        }
                                    }
                                %>
                                <button onclick="themechange(themeaccent.value + themeid.value, '<%=uid%>');" class="<%=Bcolor%> btn <%=Dcolor%>">CHANGE THEME</button>
                            </div>
                            <div class="input-field col s12 <%=Acolor%> <%=Dcolor%> card-panel">
                                <B>Change Privacy</b>
                                    <%
                                        java.sql.ResultSet privacyrs = DB.search("SELECT `privacy_name` FROM user_privacy WHERE users_idusers='" + uid + "' ");
                                        if (privacyrs.next()) {
                                            if (privacyrs.getString(1).equals("public")) {
                                    %>
                                <select  id="privacyid"  class="<%=Dcolor%>" >
                                    <option value="public" selected class="<%=Dcolor%>">Public</option>
                                    <option value="private" class="<%=Dcolor%>">Private</option>
                                </select>
                                <%
                                } else {
                                %>
                                <select  id="privacyid"  class="<%=Dcolor%>" >
                                    <option value="public"  class="<%=Dcolor%>">Public</option>
                                    <option value="private" selected class="<%=Dcolor%>">Private</option>
                                </select>
                                <%
                                        }
                                    }
                                %>

                                <button onclick="privacychange(privacyid.value, '<%=uid%>');" class="<%=Bcolor%> btn <%=Dcolor%>">CHANGE PRIVACY</button>
                            </div>
                            <div class="input-field col s12 <%=Acolor%> <%=Dcolor%> card-panel">
                                <B>Change Dashboard Page</b>
                                    <%
                                        java.sql.ResultSet customerrs = DB.search("SELECT `user_type_iduser_type` FROM users WHERE idusers='" + uid + "' ");
                                        if (customerrs.next()) {
                                            if (customerrs.getInt(1) == 2) {
                                    %>
                                <select  id="dashid"  class="<%=Dcolor%>" >
                                    <option value="2" selected class="<%=Dcolor%>">Get Started</option>
                                    <option value="3" class="<%=Dcolor%>">Dashboard</option>
                                </select>
                                <%
                                } else if (customerrs.getInt(1) == 3) {
                                %>
                                <select  id="dashid"  class="<%=Dcolor%>" >
                                    <option value="2"  class="<%=Dcolor%>">Get Started</option>
                                    <option value="3" selected class="<%=Dcolor%>">Dashboard</option>
                                </select>
                                <%
                                        }
                                    }
                                %>

                                <button onclick="dashboardchange(dashid.value, '<%=uid%>');" class="<%=Bcolor%> btn <%=Dcolor%>">CHANGE  PAGE</button>

                            </div>
                            <div class="input-field col s12 <%=Acolor%> <%=Dcolor%> card-panel">                                
                                <B>User Ad Preference</b>
                                    <%
                                        java.sql.ResultSet adprers = DB.search("SELECT `Preference` FROM uap WHERE users_idusers='" + uid + "' ");
                                        if (adprers.next()) {
                                            if (adprers.getInt(1) == 1) {
                                    %>
                                <select  id="uapid"  class="<%=Dcolor%>" >
                                    <option value="1" selected class="<%=Dcolor%>">Turned On</option>
                                    <option value="0" class="<%=Dcolor%>">Turned Off</option>
                                </select>
                                <select id="adcate">
                                    <%java.sql.ResultSet adcaters = DB.search("Select idAd_category,Ad_category_name from ad_categories ");
                                        while (adcaters.next()) {%>
                                    <%
                                        String classtext = "";
                                        java.sql.ResultSet adcatersprof = DB.search("SELECT `adcategory` FROM user_followed_ad_catergories WHERE users_idusers='" + uid + "' ");
                                        if (adcatersprof.next()) {
                                            if (adcatersprof.getInt(1) == adcaters.getInt(1)) {
                                                classtext = "selected";
                                            }
                                        }%>
                                    <option <%=classtext%> value="<%=adcaters.getString(1)%>"><%=adcaters.getString(2)%></option>
                                    <%}%>
                                </select>
                                <%
                                } else {
                                %>
                                <select  id="uapid"  class="<%=Dcolor%>" >
                                    <option value="1"  class="<%=Dcolor%>">Turned On</option>
                                    <option value="0" selected class="<%=Dcolor%>">Turned Off</option>
                                </select>
                                <select id="adcate">
                                    <%java.sql.ResultSet adcaters = DB.search("Select idAd_category,Ad_category_name from ad_categories ");
                                        while (adcaters.next()) {%>
                                    <%
                                        String classtext = "";
                                        java.sql.ResultSet adcatersprof = DB.search("SELECT `adcategory` FROM user_followed_ad_catergories WHERE users_idusers='" + uid + "' ");
                                        if (adcatersprof.next()) {
                                            if (adcatersprof.getInt(1) == adcaters.getInt(1)) {
                                                classtext = "selected";
                                            }
                                        }%>
                                    <option <%=classtext%> value="<%=adcaters.getString(1)%>"><%=adcaters.getString(2)%></option>
                                    <%}%>
                                </select>
                                <%
                                        }
                                    }
                                %>
                                <button onclick="uapchange(uapid.value, '<%=uid%>', adcate.value);" class="<%=Bcolor%> btn <%=Dcolor%>">CHANGE UAP</button>

                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Logout</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <form action="Logout">
                                <input onclick="signOut();" type="submit" value="Logout" class="<%=Bcolor%> <%=Dcolor%> btn">
                            </form>
                        </div>

                    </li>
                </ul>
                <div class="grey-text">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

            </div>
            <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
                <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

                </div>
            </div>
            <div id="peekdelpost" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
                <div id="delpostdiv" class="container <%=Bcolor%> <%=Dcolor%> ">

                </div>
            </div>
            <div id="opncmnts" class="modal bottom-sheet card" style="min-height:100vh; background-color: <%=Ccolor%>">
                <div id="peekcomments" class="<%=Bcolor%> <%=Dcolor%> container">

                </div>
            </div>

        </main>

        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%>">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>

        <script>
                                    var userid;
                                    $(document).ready(function () {
                                        $('.collapsible').collapsible();
                                        $('#peekprofile').modal();
                                        $('#opncmnts').modal();
                                        $('#peekdelpost').modal();
                                        $('select').formSelect();
                                        $('#commentsection').load('commentlive');
                                        $('.materialboxed').materialbox();
                                        $("body").on("contextmenu", function (e) {
                                            return false;
                                        });
                                    });
                                    function hideloader(x) {
                                        userid = x;
                                        numberrefresh();
                                        chatnumberrefresh();
                                    }
                                    function expandposts() {
                                        var instance = M.Collapsible.getInstance($('.collapsible'));
                                        instance.open(1)
                                    }
                                    function expandfollowers() {
                                        var instance = M.Collapsible.getInstance($('.collapsible'));
                                        instance.open(3)
                                    }
                                    function expandfollowing() {
                                        var instance = M.Collapsible.getInstance($('.collapsible'));
                                        instance.open(4)
                                    }
                                    function expandupdateprofile() {
                                        var instance = M.Collapsible.getInstance($('.collapsible'));
                                        instance.open(5)
                                    }
                                    function profilepicupdate() {
                                        var $files = $('#imgfile').get(0).files;
                                        var fp;
                                        if ($files.length) {
                                            // Reject big files
                                            if ($files[0].size > $(this).data("max-size") * 1024) {
                                                console.log("Please select a smaller file");
                                                return false;
                                            }
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
                                            $.ajax(settings).done(function (response) {
                                                var obj = JSON.parse(response);
                                                fp = obj.data.link;
                                            });

                                        }
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                window.location = "profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "updateprofilepic?fp=" + fp, true);
                                        xhttp.send();

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
                                    function showdelpost(x, y) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                document.getElementById("delpostdiv").innerHTML = this.responseText;
                                            }
                                        };
                                        xhttp.open("GET", "deletepost?x=" + x + "&y=" + y, true);
                                        xhttp.send();
                                    }
                                    function deletethispost(x, y) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                document.getElementById("delpostdiv").innerHTML = this.responseText;
                                                window.location="profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "deletethispost?x=" + x + "&y=" + y, true);
                                        xhttp.send();
                                    }
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
                                    function follow(x, y) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                window.location = "profile.jsp";
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
                                                window.location = "profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "unfollow?x=" + x + "&y=" + y, true);
                                        xhttp.send();
                                    }
                                    function themechange(x, uid) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                window.location = "profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "themechange?x=" + x + "&uid=" + uid, true);
                                        xhttp.send();
                                    }
                                    function uapchange(x, uid, y) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                window.location = "profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "uapchange?x=" + x + "&uid=" + uid + "&y=" + y, true);
                                        xhttp.send();
                                    }
                                    function dashboardchange(x, uid) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                window.location = "profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "changedashboardpage?x=" + x + "&uid=" + uid, true);
                                        xhttp.send();
                                    }
                                    function privacychange(x, uid) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                  window.location ="profile.jsp";
                                            }
                                        };
                                        xhttp.open("GET", "privacychange?x=" + x + "&uid=" + uid, true);
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
                                    function showpostcmnts(uid, pid) {
                                        selectedpid = pid;
                                        userid = uid;
                                        refresh()
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        $('.materialboxed').materialbox();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                document.getElementById("peekcomments").innerHTML = this.responseText;
                                                $('.materialboxed').materialbox();
                                            }
                                        };
                                        xhttp.open("GET", "peekcomments?uid=" + uid + "&pid=" + pid, true);
                                        $('.materialboxed').materialbox();
                                        xhttp.send();
                                        $('.materialboxed').materialbox();
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

        </script>
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
