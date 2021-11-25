<%-- 
    Document   : messege
    Created on : Aug 26, 2018, 4:12:12 PM
    Author     : HEYMEOWCAT
--%>

<%@page import="com.heymeowcat.tailznet.KEY"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.heymeowcat.tailznet.ENCDEC"%>
<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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
        <link rel="stylesheet" href="css/balloon.css">
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
                java.sql.ResultSet themers; { PreparedStatement ps = DB.prepare("SELECT themename FROM app_theme WHERE users_idusers=?"); ps.setInt(1, uid); themers = ps.executeQuery(); }
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
            ::-webkit-scrollbar {
                width: 0px;
                background: transparent;
            }
            html, body {
                height:100%;
                min-height:100%;
                overflow: hidden;
                display: flex;
                background-color: <%=Ccolor%>;
                flex-direction: column;
                -webkit-font-smoothing: antialiased;
                -moz-osx-font-smoothing: grayscale;
                text-rendering: optimizeLegibility;
            }


            .StickyHeader, .StickyFooter {
                flex: 0 0 auto;
            }

            .StickyContent {
                flex: 1 1 auto;
                overflow-y: scroll;
            }
            *, *:before, *:after {
                box-sizing: border-box;
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

            .message__list {
                padding: 0;
                margin: 0;
                list-style: none;
                width: 100%;
                position: relative;

                &:after {
                    content: '';
                    display: table;
                    clear: both;
                }
            }

            .message__item {
                max-width: 50%;
                clear: both;
            }

            .message__item--bot {
                float: left;

            }

            .message__item--user {
                float: right;
                text-align: right;                
            }

            .message {
                padding: 8px 15px;
                margin-top: 2px;
                display: inline-block;
                text-align: left;
            }

            .message--bot {
                background-color: <%=Ccolor%>;
                border-radius: 20px 20px 20px 20px;
            }

            .message--user {
                background-color: <%=Fcolor%>;
                border-radius: 20px 20px 20px 20px;
            }

            .bot__image {
                width: 28px;
                height: 28px;
                position: absolute;
                bottom: 3px;
                left: 0;
                border-radius: 20px;
            }
            .ratio {
                background-position: center center;
                background-repeat: no-repeat;
                background-size: cover;
                height: 0;
                padding-bottom: 100%;
                position: relative;
                width: 100%;
            }

            .img-circle {
                border-radius: 50%;
            }

            .img-responsive {
                display: block;
                height: auto;
                max-width: 100%;
            }
        </style>
    </head>
    <body onload="hideloader(<%=uid%>);" class="noselect ">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand" oncontextmenu="setuser(<%=uid%>);return false;"> <a href="messege.jsp"><i class=" material-icons  <%=Dcolor%> mdl-badge mdl-badge--overlap">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up ">
                        <div class="col s6">
                            <a class="brand-logo"><B class="<%=Dcolor%>">TAILZ</B></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <div id="opncreategroup" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
            <div class="container">
                <div class="<%=Acolor%> card-panel">
                    <h5 class="<%=Dcolor%>">New Group<i class="material-icons right waves-effect modal-close">close</i></h5>
                    <div class="row">

                        <div class="input-field col s12 m6">
                            <input id="titletext" placeholder="Group Name" name="title" type="text" class="validate <%=Dcolor%>" required>
                        </div>
                        <div class="file-field input-field col s12 m6">
                            <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                <span>Image</span>
                                <input id="imgfile" type="file" class="imgur" accept="image/*" data-max-size="5000" required=""/>
                            </div>
                            <div class="file-path-wrapper ">
                                <input id="filepathimg" class="file-path validate <%=Dcolor%>" type="text"  >
                            </div>
                        </div>

                    </div>
                    <button class="<%=Bcolor%> <%=Dcolor%> btn" id="imgpostbtn" onclick="imgpost();">Create</button>
                </div>

            </div>
        </div>


        <main class="StickyContent">
            <div class="container animated fadeIn">             
                <div  id="frontmsgui" class="row">
                    <%
                        int muiddd = 0;
                        java.sql.ResultSet rsop; { PreparedStatement ps = DB.prepare("SELECT firstname,lastname,image,idusers FROM users JOIN user_profile_pic ON users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers FROM users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender=?) AND idusers = ANY(SELECT sender FROM follow WHERE receiver=?)) ORDER BY (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='0' AND user_sender=idusers AND users_receiver=?) DESC, (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='0' AND users_receiver=idusers AND user_sender=?) DESC, (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='1' AND users_receiver=idusers AND user_sender=?) DESC"); ps.setInt(1, uid); ps.setInt(2, uid); ps.setInt(3, uid); ps.setInt(4, uid); ps.setInt(5, uid); rsop = ps.executeQuery(); }
                        if (!rsop.isBeforeFirst()) {%>
                    <div class='center'><img src='img/conversation.png' class='responsiveimg' style="margin-top: 100px"></div>
                    <div class="grey-text center">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
                    <%
                        }
                        java.sql.ResultSet rs; { PreparedStatement ps = DB.prepare("SELECT firstname,lastname,image,idusers FROM users JOIN user_profile_pic ON users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers FROM users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender=?) AND idusers = ANY(SELECT sender FROM follow WHERE receiver=?)) ORDER BY (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='0' AND user_sender=idusers AND users_receiver=?) DESC, (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='0' AND users_receiver=idusers AND user_sender=?) DESC, (SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='1' AND users_receiver=idusers AND user_sender=?) DESC"); ps.setInt(1, uid); ps.setInt(2, uid); ps.setInt(3, uid); ps.setInt(4, uid); ps.setInt(5, uid); rs = ps.executeQuery(); }
                        while (rs.next()) {
                            muiddd = Integer.parseInt(rs.getString(4));
                            int unseenno = 0;
                            String outString = "";
                            java.sql.ResultSet unseencount; { PreparedStatement ps = DB.prepare("SELECT count(chatlinestatus) FROM chat WHERE chatlinestatus='0' AND user_sender=? AND users_receiver=?"); ps.setString(1, rs.getString(4)); ps.setInt(2, uid); unseencount = ps.executeQuery(); }
                            if (unseencount.next()) {
                                unseenno = unseencount.getInt(1);
                                if (unseenno > 9) {
                                    outString = "<b>9+</b>";
                                } else if (unseenno == 0) {
                                    outString = "<i class='material-icons " + Dcolor + " '>add</i>";
                                } else {
                                    outString = "<b>" + unseenno + "</b>";
                                }
                            }
                    %>
                    <div class="col s6 m3 l2">
                        <div class="<%=Acolor%> card-panel hoverable ">
                            <img src="<%=rs.getString(3)%>" class="circle responsive-img">
                            <div class="card-content center <%=Dcolor%>">
                                <p class="truncate"><%=rs.getString(1)%> <%=rs.getString(2)%></p>
                                <a onclick="setuser(<%=muiddd%>);"  class="btn-floating <%=Bcolor%> <%=Dcolor%> waves-effect"><%=outString%></a>
                            </div>
                        </div>
                    </div>

                    <%
                        }
                        java.sql.ResultSet rsgroup; { PreparedStatement ps = DB.prepare("SELECT * FROM `groups` WHERE group_id = ANY(SELECT Groups_group_id FROM group_members WHERE members=?) ORDER BY (SELECT count(chatstatus) FROM group_chat WHERE chatstatus='0' AND users_idusers!=?) DESC, (SELECT count(chatstatus) FROM group_chat WHERE chatstatus='0' AND users_idusers=?) DESC, (SELECT count(chatstatus) FROM group_chat WHERE chatstatus='1' AND users_idusers!=?) DESC"); ps.setInt(1, uid); ps.setInt(2, uid); ps.setInt(3, uid); ps.setInt(4, uid); rsgroup = ps.executeQuery(); }
                        String outString = "<i class='material-icons " + Dcolor + " '>add</i>";
                        while (rsgroup.next()) {
                    %>
                    <div class="col s6 m3 l2">
                        <div class="<%=Acolor%> card-panel hoverable ">
                            <img src="<%=rsgroup.getString(3)%>" class="circle responsive-img">
                            <div class="card-content center <%=Dcolor%>">
                                <p class="truncate"><%=rsgroup.getString(2)%></p>
                                <a onclick="setusergroup('<%=rsgroup.getString(1)%>');"  class="btn-floating <%=Bcolor%> <%=Dcolor%> waves-effect"><%=outString%></a>
                            </div>
                        </div>
                    </div>

                    <%}%>
                </div>

            </div>
        </main>
        <%
            java.sql.ResultSet rsgroupfeature; { PreparedStatement ps = DB.prepare("SELECT COUNT(idusers) FROM users WHERE users.idusers = ANY(SELECT idusers FROM users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender=?) AND idusers = ANY(SELECT sender FROM follow WHERE receiver=?))"); ps.setInt(1, uid); ps.setInt(2, uid); rsgroupfeature = ps.executeQuery(); }
            if (rsgroupfeature.next()) {
                if (rsgroupfeature.getInt(1) > 1) {
        %>
        <div  class ="fixed-action-btn hide-on-med-and-up" style="bottom:60px; right: 5px;" >
            <a onclick="$('#opncreategroup').modal('open');" class="btn-floating btn-large <%=Bcolor%>" >       
                <i class="large material-icons <%=Dcolor%>">group_add</i>
            </a>
        </div>
        <div class="fixed-action-btn hide-on-small-and-down">
            <a onclick="$('#opncreategroup').modal('open');" class="btn-floating btn-large <%=Bcolor%>">
                <i class="large material-icons <%=Dcolor%>">group_add</i>
            </a>
        </div>
        <%}
            }
        %>





        <footer class="StickyFooter hide-on-med-and-up">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style="width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%>  animated rubberBand" oncontextmenu="setuser(<%=uid%>);return false;">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
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
                function hideloader(x) {
                    userid = x;
                    refreshfrontmsg();
                }
                $(document).ready(function () {
                    $('#opncreategroup').modal();
                    $("body").on("contextmenu", function (e) {
                        return false;
                    });
                });
                function refreshfrontmsg() {
                    var source = new EventSource("sse/msgoverview?uid=" + userid);
                    source.onmessage = function(event) {
                        $('#frontmsgui').html(event.data);
                    };
                }
                function setuser(x) {
                    var xhttp = new XMLHttpRequest();
                    xhttp.open("GET", "messenginguser?x=" + x, true);
                    xhttp.send();
                    window.location = "chathead.jsp";
                }
                function setusergroup(x) {
                    var xhttp = new XMLHttpRequest();
                    xhttp.open("GET", "messenginggroup?x=" + x, true);
                    xhttp.send();
                    window.location = "groupchathead.jsp";
                }

                function numberrefresh() {
                    $('#notificationnumber').load("notificationnumber?uid=" + userid);
                    $('#notificationnumber2').load("notificationnumber?uid=" + userid);
                }
                function chatnumberrefresh() {
                    $('#chatnumber').load("chatnumber?uid=" + userid);
                }
                function imgpost() {
                    var $files = $('#imgfile').get(0).files;
                    var fp;
                    if ($files.length) {
                        if ($files[0].size > $(this).data("max-size") * 1024) {
                            console.log("Please select a smaller file");
                            return false;
                        }
                        console.log("Uploading file to Imgur..");
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
                    var x = document.getElementById("titletext").value;
                    var xhttp;
                    xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            window.location = "messege.jsp";
                        }
                    };
                    xhttp.open("GET", "createnewgroup?uid=" + userid + "&title=" + x + "&fp=" + fp, true);
                    xhttp.send();

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
