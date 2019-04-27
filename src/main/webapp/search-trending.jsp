<%-- 
    Document   : search-trending
    Created on : Aug 26, 2018, 4:17:13 PM
    Author     : HEYMEOWCAT
--%>

<%@page import="com.heymeowcat.tailznet.KEY"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.heymeowcat.tailznet.ENCDEC"%>
<%@page import="com.heymeowcat.tailznet.DB"%>
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
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <%
            if (request.getSession().getAttribute("user") != null) {
                int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
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
                -webkit-touch-callout: none; /* iOS Safari */
                -webkit-user-select: none; /* Safari */
                -khtml-user-select: none; /* Konqueror HTML */
                -moz-user-select: none; /* Firefox */
                -ms-user-select: none; /* Internet Explorer/Edge */
                user-select: none; /* Non-prefixed version, currently
                                      supported by Chrome and Opera */
            }
            html {
                overflow: scroll;
                overflow-x: hidden;
            }
            ::-webkit-scrollbar {
                width: 0px;  /* remove scrollbar space */
                background: transparent;  /* optional: just make scrollbar invisible */
            }
            /* optional: show position indicator in red */
            ::-webkit-scrollbar-thumb {
                background: #FF0000;
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

            ul.ks-cboxtags {
                list-style: none;
                padding: 20px;
            }
            ul.ks-cboxtags li{
                display: inline;
            }
            ul.ks-cboxtags li label{
                display: inline-block;
                background-color: rgba(255, 255, 255, .9);
                border: 2px solid rgba(139, 139, 139, .3);
                color: #adadad;
                border-radius: 25px;
                white-space: nowrap;
                margin: 3px 0px;
                -webkit-touch-callout: none;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
                -webkit-tap-highlight-color: transparent;
                transition: all .2s;
            }

            ul.ks-cboxtags li label {
                padding: 8px 12px;
                cursor: pointer;
            }

            ul.ks-cboxtags li label::before {
                display: inline-block;
                font-style: normal;
                font-variant: normal;
                text-rendering: auto;
                -webkit-font-smoothing: antialiased;
                font-family: "Font Awesome 5 Free";
                font-weight: 900;
                font-size: 12px;
                padding: 2px 6px 2px 2px;
                content: "\f067";
                transition: transform .3s ease-in-out;
            }

            ul.ks-cboxtags li input[type="checkbox"]:checked + label::before {
                content: "\f00c";
                transform: rotate(-360deg);
                transition: transform .3s ease-in-out;
            }

            ul.ks-cboxtags li input[type="checkbox"]:checked + label {
                border: 2px solid #e4007d;
                background-color: #e4007d;
                color: #fff;
                transition: all .2s;
            }

            ul.ks-cboxtags li input[type="checkbox"] {
                display: absolute;
            }
            ul.ks-cboxtags li input[type="checkbox"] {
                position: absolute;
                opacity: 0;
            }
            ul.ks-cboxtags li input[type="checkbox"]:focus + label {
                border: 2px solid #e9a1ff;
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
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%>  animated rubberBand">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
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
        <main class="StickyContent noselect">
            <div class="container animated fadeIn">
                <ul class="<%=Acolor%> collapsible expandable" style="border-color: <%=Ccolor%>">
                    <li class="active" >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Search</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <input id="t1" type="search"  class="<%=Dcolor%> " placeholder="Type anything..." >
                            <table id="tb1" class="<%=Dcolor%>">

                            </table>
                            <!--<ul class="ks-cboxtags">
                                <li><input type="checkbox" id="checkboxOne" value="profile" checked><label for="checkboxOne">Profile</label></li>
                                <li><input type="checkbox" id="checkboxTwo" value="post" ><label for="checkboxTwo">Post</label></li>
                            </ul>-->
                        </div>
                    </li>

                    <li class="active" >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Trending Posts</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%
                                    try {
                                        java.sql.ResultSet rs = DB.search("SELECT * from post where Post_Privacy='1' AND  idpost = ANY(SELECT `post_idpost` FROM post_rank GROUP BY `post_idpost` ORDER BY COUNT(`likes`) DESC)");
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
                                                    </div>
                                                </a>
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

                                <% java.sql.ResultSet noptrs = DB.search("SELECT * from post where Post_Privacy='1' AND  idpost = ANY(SELECT `post_idpost` FROM post_rank GROUP BY `post_idpost` ORDER BY COUNT(`likes`) DESC)");
                                    if (!noptrs.isBeforeFirst()) {
                                %>

                                <div class='center'><img src='img/no-feeds.png' class='responsiveimg ' ></div>
                                    <%
                                        }
                                    %>

                            </div>
                        </div>
                    </li>
                    <li class="active" >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Follow Suggestions</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div id="followsuggestionsid" class="row">
                                <%
                                    java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "')");
                                    while (rs.next()) {
                                %>
                                <div class="col s6 m3 l2 animated fadeIn">
                                    <div class="<%=Acolor%> card-panel hoverable">
                                        <img src="<%=rs.getString(3)%>" class="circle responsive-img">
                                        <div class="card-content center <%=Dcolor%>">
                                            <p class="truncate"><%=rs.getString(1)%> <%=rs.getString(2)%></p>
                                            <a class=" btn <%=Bcolor%> <%=Dcolor%> waves-effect" onclick="followthissugg('<%=rs.getString(4)%>')"><i class="material-icons">person_add</i></a>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                                <% java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "')");
                                    if (!rsop.isBeforeFirst()) {
                                %>

                                <div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>
                                    <%
                                        }
                                    %>

                            </div>
                        </div>
                    </li>
                </ul>
                <div class="grey-text">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

            </div>
            <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
                <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

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
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>

        </footer>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script>
                                                var elem = document.querySelector('.collapsible.expandable');
                                                var instance = M.Collapsible.init(elem, {
                                                    accordion: false
                                                });
                                                var input = document.getElementById("t1");
                                                input.addEventListener("keyup", function (event) {
                                                    event.preventDefault();
                                                    if (event.keyCode === 13) {
                                                        search();
                                                        savesearchhistory();
                                                    }
                                                });
                                                function hideloader(x) {
                                                    userid = x
                                                    numberrefresh();
                                                    chatnumberrefresh();
                                                }
                                                $(document).ready(function () {
                                                    $('#peekprofile').modal();
                                                    $('#opncmnts').modal();
                                                    $('.materialboxed').materialbox();
                                                    $("body").on("contextmenu", function (e) {
                                                        return false;
                                                    });
                                                });
                                                function search() {
                                                    document.getElementById("tb1").innerHTML = "";
                                                    var x = document.getElementById("t1").value;
                                                    if (x == "") {
                                                        document.getElementById("tb1").innerHTML = "";
                                                    }
                                                    var xhtp = new XMLHttpRequest();
                                                    xhtp.onreadystatechange = function () {
                                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                                            document.getElementById("tb1").innerHTML += xhtp.responseText;
                                                        }
                                                    }
                                                    xhtp.open("GET", "profilesearch?name=" + x + "&loggedid=" + userid, true);
                                                    xhtp.send();
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
                                                function follow(x, y) {
                                                    var xhttp;
                                                    xhttp = new XMLHttpRequest();
                                                    xhttp.onreadystatechange = function () {
                                                        if (this.readyState == 4 && this.status == 200) {
                                                            window.location = "search-trending.jsp";
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
                                                            window.location = "search-trending.jsp";
                                                        }
                                                    };
                                                    xhttp.open("GET", "unfollow?x=" + x + "&y=" + y, true);
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
                                                function followthissugg(x) {
                                                    var xhtp = new XMLHttpRequest();
                                                    xhtp.onreadystatechange = function () {
                                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                                            document.getElementById("followsuggestionsid").innerHTML = xhtp.responseText;
                                                        }
                                                    }
                                                    xhtp.open("GET", "newsuggfollow?x=" + x + "&loggedid=" + userid, true);
                                                    xhtp.send();
                                                }
                                                function fullviewprofile(x) {
                                                    showprofile(x, userid);
                                                    $('#peekprofile').modal('open');

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
