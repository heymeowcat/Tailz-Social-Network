<%-- 
    Document   : notifications
    Created on : Aug 26, 2018, 4:13:32 PM
    Author     : HEYMEOWCAT
--%>

<%@page import="com.heymeowcat.tailznet.DB"%>
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
        <link href="css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <%
            System.gc();
            if (request.getSession().getAttribute("user") != null) {
                  int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
                String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor ="";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
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
        </style>
    </head>
    <body onload="hideloader(<%=uid%>);" class="noselect">
        <div id="loading">
            <div style="height: 100vh; width: 100%; background-color: <%= Ccolor%>; text-align: center;" class="valign-wrapper">
                <div class="progress <%= Acolor%>">
                    <div class="indeterminate <%= Bcolor%>" ></div>
                </div>

            </div>
        </div>
        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%= Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
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
            <div class="container animated zoomIn">
                <br>
                <% java.sql.ResultSet rsop = DB.search("Select * FROM `notification` where notificationfor = '" + uid + "' ORDER BY time DESC");
                    if (!rsop.isBeforeFirst()) {
                %>

                <div class='center'><img src='img/notifications-silenced.png' class='responsiveimg' style="margin-top: 100px"></div>
                    <%
                    } else {%>
                <a class="<%=Bcolor%> <%=Dcolor%> btn" onclick="clearnotifications(<%=uid%>)">Mark All as Read</a>
                <%}
                %>
                <ul id="notificationbox" class="<%= Acolor%> collection" style="border-color: <%=Ccolor%>" >
                    <%try {
                            String commenttext = "";
                            String notificationtxt = "";
                            java.sql.ResultSet rs = DB.search("Select * FROM `notification` where notificationfor = '" + uid + "' ORDER BY time DESC");
                            while (rs.next()) {
                                if (rs.getString(4).equals("1")) {
                                    commenttext = "started following you";
                                } else if (rs.getString(4).equals("2")) {
                                    commenttext = "commented on your post";
                                }
                                String imgup = "";
                                String fnamepost = "";
                                String lnamepost = "";
                                java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(3) + "' ");
                                if (imgpostuser.next()) {
                                    imgup = imgpostuser.getString(1);
                                }
                                java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(3) + "' ");
                                if (firstimguser.next()) {
                                    fnamepost = firstimguser.getString(1);
                                }
                                java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(3) + "' ");
                                if (lastimguser.next()) {
                                    lnamepost = lastimguser.getString(1);
                                }
                                notificationtxt = fnamepost + " " + lnamepost + " " + commenttext;
                    %>

                    <li class="<%= Acolor%> <%= Dcolor%> collection-item avatar animated fadeInUp" style="border-color: <%=Ccolor%>">
                        <img src="<%=imgup%>" alt="" class="circle">
                        <span class=" title"><%=notificationtxt%></span>
                        <p>
                            <%=rs.getString(5)%>
                        </p>

                    </li>


                    <%}
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>

                </ul>
            </div>
        </main>

        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce">   <a href="notifications.jsp"><i class=" material-icons  <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
                    var userid;
                    function hideloader(x) {
                        document.getElementById('loading').style.display = "none";
                        newassign(x);
                    }
                    $(document).ready(function () {

                    });
                    function newassign(x) {
                        userid = x;
                        numberrefresh();
                        refresh();
                        chatnumberrefresh();
                    }
                    function refresh() {
                        setTimeout(function () {
                            $('#notificationbox').load("notificationslive?uid=" + userid);
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
                    function clearnotifications(uid) {
                        var xhttp;
                        xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {

                            }
                        };
                        xhttp.open("GET", "clearnotifications?uid=" + uid, true);
                        xhttp.send();
                    }
                    function chatnumberrefresh() {
                        setTimeout(function () {
                            $('#chatnumber').load("chatnumber?uid=" + userid);
                            $('#chatnumber2').load("chatnumber?uid=" + userid);
                            chatnumberrefresh();
                        }, 1000);
                    }
        </script>
        <%
            } else {
                response.sendRedirect("login-register.jsp");
            }
        %>
    </body>
</html>