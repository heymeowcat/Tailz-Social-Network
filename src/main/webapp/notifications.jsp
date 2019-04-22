<%-- 
    Document   : notifications
    Created on : Aug 26, 2018, 4:13:32 PM
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
        </style>
    </head>
    <body onload="hideloader(<%=uid%>);" class="noselect">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%= Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
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
            <div class="container animated fadeIn">
                <br>
                <div id="notificationbox">
                    <% java.sql.ResultSet rsop = DB.search("Select * FROM `notification` where notificationfor = '" + uid + "' ORDER BY time DESC");
                        if (!rsop.isBeforeFirst()) {
                    %>
                    <div class='center'><img style="margin-top: 100px" src='img/notifications-silenced.png' class='responsiveimg' ></div>
                    <div class="grey-text center">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
                    <%
                    } else {%>
                    <a class="<%=Bcolor%> <%=Dcolor%> btn" onclick="clearnotifications(<%=uid%>)">Mark All as Read</a>
                    <%}
                    %>
                    <ul class="<%= Acolor%> collection" style="border-color: <%=Ccolor%>" >
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

                        <li class="<%= Acolor%> <%= Dcolor%> collection-item avatar" style="border-color: <%=Ccolor%>">
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

            </div>

        </main>

        <footer class="StickyFooter hide-on-med-and-up ">

            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="notifications.jsp"><i class=" material-icons  <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.js"></script>
        <script>
                        var userid;
                        function hideloader(x) {
                            newassign(x);
                        }
                        $(document).ready(function () {
                            $("body").on("contextmenu", function (e) {
                                return false;
                            });
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
