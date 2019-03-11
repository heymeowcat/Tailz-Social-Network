<%-- 
    Document   : profile
    Created on : Aug 26, 2018, 3:58:44 PM
    Author     : HEYMEOWCAT
--%>
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
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" href="css/normalize.min.css">
        <link rel='stylesheet' href='css/cropper.min.css'>
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <%
            System.gc();
            if (request.getSession().getAttribute("user") != null) {
                int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
                String fn="";
                String ln="";
                String usrpostcount = "";
                String followercount = "";
                String followingcount = "";
                java.sql.ResultSet ufl = DB.search("Select firstname,lastname FROM `users` where idusers='" + uid + "' ");
                if (ufl.next()) {
                    fn =ufl.getString(1);
                    ln =ufl.getString(2);
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
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
                if (themers.next()) {
                    if (themers.getString(1).equals("pinkdark")) {
                        Acolor = "black";
                        Bcolor = "pink";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    }
                }
        %>
        <style>
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



            .box-2 {
                padding: 0.5em;
                width: calc(100%/2 - 1em);
            }

            .options label,
            .options input{
                width:4em;
                padding:0.5em 1em;
            }


            .hide {
                display: none;
            }

            img {
                max-width: 100%;
            }
            input:not([type]), input[type=text]:not(.browser-default), input[type=password]:not(.browser-default), input[type=email]:not(.browser-default), input[type=url]:not(.browser-default), input[type=time]:not(.browser-default), input[type=date]:not(.browser-default), input[type=datetime]:not(.browser-default), input[type=datetime-local]:not(.browser-default), input[type=tel]:not(.browser-default), input[type=number]:not(.browser-default), input[type=search]:not(.browser-default), textarea.materialize-textarea {
                color: <%=Fcolor%>;
            }
            .select-wrapper .caret {
                fill: <%=Fcolor%>;
            }

        </style>
    </head>
    <body onload="hideloader(<%=uid%>)" class="noselect">
        <div id="fb-root"></div>
        <script async defer src="https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v3.2&appId=2789629401262230&autoLogAppEvents=1"></script>
        <div id="loading">
            <div style="height: 100vh; width: 100%; background-color: <%= Ccolor%>; text-align: center;" class="valign-wrapper">
                <div class="progress <%= Acolor%>">
                    <div class="indeterminate <%= Bcolor%>" ></div>
                </div>
            </div>
        </div>
        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Acolor%> ">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up">
                        <div class="col s6">
                            <a href="#!" class="brand-logo"><i class=" material-icons center <%=Dcolor%>">whatshot</i><B class="<%=Dcolor%>">tailz</B></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>


        <main class="StickyContent">
            <div class="animated zoomIn  container">
                <ul class="<%=Acolor%> collapsible " style="border-color: <%=Ccolor%>">
                    <li class="active" >
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Profile</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="center">
                                <img id="propiche" style="height: 150px; width: 150px" src="<%=up%>" alt="" class="circle responsive-img hide-on-small-and-down animated fadeIn">
                                <img style="height: 100px; width: 100px" src="<%=up%>" alt="" class="circle responsive-img hide-on-med-and-up animated fadeIn">
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
                                <div class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Posts</span><br><b><%=usrpostcount%></b></div>
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
                                <div class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Followers</span><br><b><%=followercount%></b></div>
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
                                <div class="col s4 waves-effect <%=Dcolor%>"> <span class="transparent ">Following</span><br><b><%=followingcount%></b></div>
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
                                            <img class="responsive-img" src="<%=rs.getString(3)%>">
                                        </div>
                                        <div class="card-content">
                                            <span class="card-title <%=Dcolor%>"><b class="truncate<%=Dcolor%>"><%=rs.getString(2)%></b><i class="material-icons right activator waves-effect  <%=Dcolor%>">more_vert</i></span>    
                                            <p class="truncate"><%=rs.getString(4)%></p>
                                        </div>
                                        <div class=" <%=Dcolor%> <%=Acolor%>  card-reveal">
                                            <span class="card-title <%=Dcolor%> text-darken-4 truncate "><%=rs.getString(2)%><i class="material-icons right waves-effect">close</i></span>
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
                                            <video class="responsive-video" controls>
                                                <source src="<%=rs.getString(3)%>" type="video/mp4" >
                                            </video>

                                        </div>
                                        <div class="card-content">
                                            <span class="card-title <%=Dcolor%>"><b class="truncate <%=Dcolor%>"><%=rs.getString(2)%></b><i class="material-icons right activator waves-effect <%=Dcolor%>">more_vert</i></span>    
                                            <p class="truncate"><%=rs.getString(4)%></p>
                                        </div>
                                        <div class="<%=Acolor%> <%=Dcolor%> card-reveal">
                                            <i class="material-icons right waves-effect card-title <%=Dcolor%>">close</i>
                                            <span class="card-title <%=Dcolor%> text-darken-4 truncate"><%=rs.getString(2)%></span>
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

                            <div class='center'><img src='img/business.png' class='responsiveimg ' ></div>
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

                            <div class='center'><img src='img/business.png' class='responsiveimg ' ></div>
                                <%
                                    }
                                %>
                        </div>
                    </li>

                    <li id="updateprofilesegment">
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Update Profile</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <form method="post" enctype="multipart/form-data" action="update?uid=<%=uid%>">
                                Change Profile Name
                                <div class="row">
                                    <div class="input-field col s12 m6">
                                        <input checked placeholder="First Name" id="first_name" name="fn" type="text" class="validate <%=Dcolor%>" value="<%=fn%>" minlength="3">
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Last Name" id="last_name" name="ln" type="text" class="validate <%=Dcolor%>" value="<%=ln%>" minlength="3">
                                    </div>
                                </div>
                                Change Profile Picture
                                <div class="file-field input-field">
                                    <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                        <span>Choose Image</span>
                                        <input type="file"  name="file-input" id="file-input" >
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate <%=Dcolor%>" type="text">
                                    </div>
                                </div>
                                <!-- leftbox -->
                                <div class="row">
                                    <div class=" col s12">
                                        <div class="result"></div>
                                    </div>
                                </div>
                                <!--rightbox-->
                                <div class="box-2 img-result hide">
                                    <!-- result of crop -->
                                    <img class="cropped" src="" alt="" id="crpimg">
                                </div>
                                <!-- input file -->
                                <div class="box ">
                                    <div class="row hiddendiv">
                                        <div class="col s12 m6">
                                            <div class="options hide" >
                                                <input type="number" class="img-w" value="300" min="100" max="1200" />
                                            </div>
                                        </div>
                                    </div>

                                    <!-- save btn -->
                                    <button class="<%=Bcolor%> <%=Dcolor%> btn save hide">Crop</button>
                                    <!-- download btn -->
                                    <a href="" class="<%=Bcolor%> <%=Dcolor%> btn download hide">Download</a>
                                </div>
                                <br>
                                <a class="<%=Bcolor%> <%=Dcolor%> btn center" href="resetprofilepic?uid=<%=uid%>">Remove Profile Picture</a><br><br>
                                <input type="submit" value="Update" class="<%=Bcolor%> <%=Dcolor%> btn center">
                            </form>

                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Update Profile with Facebook</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <div class="fb-login-button" data-size="large" data-button-type="continue_with" data-auto-logout-link="true" data-use-continue-as="true"></div>
                            <br><br>
                            <div id="fbupdateseg">
                                <button class="<%=Bcolor%> <%=Dcolor%> btn" onclick="getInfo()">Update Profile</button>
                            </div>
                        </div>
                    </li>

                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Change Password</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <form action="updatepassword" method="post">
                                <div class="row">
                                    <div class="input-field col s12 m6">
                                        <input placeholder="New Password" name="newpsw" type="password" class="validate <%=Dcolor%>" minlength="8">
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Confirm New Name" name="conpsw" type="password" class="validate <%=Dcolor%>" minlength="8">
                                    </div>
                                </div>
                                <input type="submit" value="Change Password" class="<%=Bcolor%> btn <%=Dcolor%>">
                            </form>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Settings</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <div class="input-field col s12">
                                <B>Change Color Theme</b>
                                    <%
                                        java.sql.ResultSet lightrs = DB.search("SELECT `themename`FROM app_theme WHERE users_idusers='5' AND themename LIKE '%light'");
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
                                    java.sql.ResultSet acccolorrs = DB.search("SELECT `themename` FROM app_theme WHERE users_idusers='5'");
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
                                <button onclick="themechange(themeaccent.value + themeid.value, '<%=uid%>');refreshhhh()" class="<%=Bcolor%> btn <%=Dcolor%>">CHANGE THEME</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="collapsible-header <%=Acolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>"><b>Logout</b></div>
                        <div class="collapsible-body <%=Ecolor%> <%=Dcolor%>" style="border-color: <%=Ccolor%>">
                            <form action="Logout">
                                <input type="submit" value="Logout" class="<%=Bcolor%> <%=Dcolor%> btn">
                            </form>
                        </div>

                    </li>
                </ul>
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
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%>">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
    </body>
    <%
        } else {
            response.sendRedirect("login-register.jsp");
        }
    %>
    <!--  Scripts-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src='js/cropper.min.js'></script>
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
                                    });
                                    function hideloader(x) {
                                        userid = x;
                                        document.getElementById('loading').style.display = "none";
                                        numberrefresh();
                                        chatnumberrefresh();
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
                                        window.location.replace("profile.jsp");
                                    }
                                    function themechange(x, uid) {
                                        var xhttp;
                                        xhttp = new XMLHttpRequest();
                                        xhttp.onreadystatechange = function () {
                                            if (this.readyState == 4 && this.status == 200) {
                                                //document.getElementById("profilepeek").innerHTML = this.responseText;
                                            }
                                        };
                                        xhttp.open("GET", "themechange?x=" + x + "&uid=" + uid, true);
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
                                    // initialize and setup facebook js sdk
                                    window.fbAsyncInit = function () {
                                        FB.init({
                                            appId: '2789629401262230',
                                            xfbml: true,
                                            version: 'v2.5'
                                        });
                                        //FB.getLoginStatus(function (response) {
                                        //      if (response.status === 'connected') {
                                        //      
                                        //     } else {
                                        //          document.getElementById('fbupdateseg').parentNode.removeChild(document.getElementById('fbupdateseg'));
                                        //       }
                                        // });
                                    };
                                    (function (d, s, id) {
                                        var js, fjs = d.getElementsByTagName(s)[0];
                                        if (d.getElementById(id)) {
                                            return;
                                        }
                                        js = d.createElement(s);
                                        js.id = id;
                                        js.src = "//connect.facebook.net/en_US/sdk.js";
                                        fjs.parentNode.insertBefore(js, fjs);
                                    }(document, 'script', 'facebook-jssdk'));

                                    // getting basic user info
                                    function getInfo() {
                                        FB.api('/me', 'GET', {fields: 'first_name,last_name,id,picture.width(250).height(250),email'}, function (response) {
                                            var img = response.picture.data.url;
                                            document.getElementById('propiche').src = img;
                                            var firstn = response.first_name;
                                            var lastn = response.last_name;
                                            var xhttp;
                                            xhttp = new XMLHttpRequest();
                                            xhttp.onreadystatechange = function () {
                                                if (this.readyState == 4 && this.status == 200) {

                                                }
                                            };
                                            xhttp.open("GET", "fbupdate?x=" + img + "&y=" + firstn + "&z=" + lastn + "&uid=" + userid, true);
                                            xhttp.send();
                                        });
                                    }
                                    // vars
                                    var result = document.querySelector('.result'),
                                            img_result = document.querySelector('.img-result'),
                                            img_w = document.querySelector('.img-w'),
                                            img_h = document.querySelector('.img-h'),
                                            options = document.querySelector('.options'),
                                            save = document.querySelector('.save'),
                                            cropped = document.querySelector('.cropped'),
                                            dwn = document.querySelector('.download'),
                                            upload = document.querySelector('#file-input'),
                                            cropper = '';

                                    // on change show image with crop options
                                    upload.addEventListener('change', function (e) {
                                        if (e.target.files.length) {
                                            // start file reader
                                            var reader = new FileReader();
                                            reader.onload = function (e) {
                                                if (e.target.result) {
                                                    // create new image
                                                    var img = document.createElement('img');
                                                    img.id = 'image';
                                                    img.src = e.target.result;
                                                    // clean result before
                                                    result.innerHTML = '';
                                                    // append new image
                                                    result.appendChild(img);
                                                    // show save btn and options
                                                    save.classList.remove('hide');
                                                    options.classList.remove('hide');
                                                    // init cropper
                                                    cropper = new Cropper(img);
                                                }
                                            };
                                            reader.readAsDataURL(e.target.files[0]);
                                        }
                                    });

                                    // save on click
                                    save.addEventListener('click', function (e) {
                                        e.preventDefault();
                                        // get result to data uri
                                        var imgSrc = cropper.getCroppedCanvas({
                                            width: img_w.value // input value
                                        }).toDataURL();
                                        // remove hide class of img
                                        cropped.classList.remove('hide');
                                        img_result.classList.remove('hide');
                                        // show image cropped
                                        cropped.src = imgSrc;
                                        dwn.classList.remove('hide');
                                        dwn.download = 'imagename.png';
                                        dwn.setAttribute('href', imgSrc);
                                        document.getElementById('file-input').value = dwn;
                                    });

    </script>

</html>
