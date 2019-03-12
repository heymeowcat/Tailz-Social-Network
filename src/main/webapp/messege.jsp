<%-- 
    Document   : messege
    Created on : Aug 26, 2018, 4:12:12 PM
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
                        Fcolor = "#e91e63";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "#f8bbd0";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "#4fc3f7";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#2196F3";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "#fff176";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#f57f17";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "#aed581";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#4CAF50";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "#ce93d8";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "#9c27b0";
                    }
                }
        %> 
        <style>
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
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce"><a href="messege.jsp"><i class=" material-icons  <%=Dcolor%> mdl-badge mdl-badge--overlap">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up ">
                        <div class="col s6">
                            <a href="#!" class="brand-logo"><i class=" material-icons center <%=Dcolor%>">whatshot</i><B class="<%=Dcolor%>">tailz</B></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <main class="StickyContent ">
            <div class="container animated zoomIn">             
                <div  id="frontmsgui" class="row">
                    <%
                        int muiddd = 0;
                        java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers from users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender='" + uid + "') AND idusers= ANY(SELECT sender FROM follow WHERE receiver='" + uid + "'))  ORDER BY (Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender=idusers and users_receiver='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='0' and users_receiver=idusers and user_sender='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='1' and users_receiver=idusers and user_sender='" + uid + "' ) DESC");
                        if (!rsop.isBeforeFirst()) {%>
                    <div class='center'><img src='img/conversation.png' class='responsiveimg' style="margin-top: 100px"></div>
                        <%
                            }
                            java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT idusers from users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender='" + uid + "') AND idusers= ANY(SELECT sender FROM follow WHERE receiver='" + uid + "'))  ORDER BY (Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender=idusers and users_receiver='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='0' and users_receiver=idusers and user_sender='" + uid + "' ) DESC,(Select count(chatlinestatus) from chat where chatlinestatus='1' and users_receiver=idusers and user_sender='" + uid + "' ) DESC");
                            while (rs.next()) {
                                muiddd = Integer.parseInt(rs.getString(4));
                                int unseenno = 0;
                                String outString = "";
                                java.sql.ResultSet unseencount = DB.search("Select count(chatlinestatus) from chat where chatlinestatus='0' and user_sender='" + rs.getString(4) + "' and users_receiver='" + uid + "' ");
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
                        <div class="<%=Acolor%> card-panel hoverable">
                            <img src="<%=rs.getString(3)%>" class="circle responsive-img">
                            <div class="card-content center <%=Dcolor%>">
                                <p class="truncate"><%=rs.getString(1)%> <%=rs.getString(2)%></p>
                                <a id="openmsgbox" onclick="$('#omnmsg').modal('open');showmessages('<%=uid%>', '<%=muiddd%>');" class="btn-floating <%=Bcolor%> <%=Dcolor%> waves-effect"><%=outString%></a>
                            </div>
                        </div>
                    </div>

                    <%
                        }

                    %>

                </div>
            </div>
        </main>

        <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
            <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

            </div>
        </div>

        <div id="omnmsg" class="modal bottom-sheet card" style="min-height:100vh; background-color: <%=Ccolor%>">
            <header class="StickyHeader" style="position:relative;  z-index: 10;">
                <nav class="<%=Bcolor%>">
                    <div class=" nav-wrapper center container">
                        <div class="row ">
                            <i class="material-icons left modal-close  <%=Dcolor%> waves-effect  waves-circle " id="back">arrow_back</i>
                            <div id="muidname" class="<%=Bcolor%> <%=Dcolor%> truncate chip waves-effect waves-light center">

                            </div>
                            <a onclick="showprofile();$('#peekprofile').modal('open');"><i class="material-icons right   <%=Dcolor%> waves-effect  waves-circle ">info_outline</i></a>
                        </div>
                    </div>
                </nav>
            </header>

            <div class="<%=Dcolor%> container ">
                <div class='<%=Acolor%> card-panel' style="height: 100%">
                    <div onclick="showmessages('<%=uid%>', '<%=muiddd%>');"  id="peekmessage" class="<%= Acolor%>" style="border-color: <%=Ccolor%>; height: 67vh; overflow: scroll">
                    </div>
                    <form method="POST" enctype="multipart/form-data" id="fileUploadForm">
                        <input class="<%=Dcolor%>" name="msg" required=""  id="msg"  type="text"  placeholder="New message">
                        <div class="file-field input-field">
                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                <span ><i class="material-icons <%=Dcolor%>">file_upload</i></span>
                                <input type="file" name="file" id="filef" required="">
                            </div>
                            <div style="display: none">
                                <div class="file-path-wrapper">
                                    <input class="file-path validate <%=Dcolor%>" type="text" id="filet">
                                </div>
                            </div>
                            <input type="submit" value="Send" id="btnSubmit" class="<%=Dcolor%> <%=Bcolor%> btn right" />
                        </div>
                    </form>

                </div>                 
            </div>

        </div>

        <footer class="StickyFooter hide-on-med-and-up">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style="width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%>  animated bounce">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
        <%
            } else {
                response.sendRedirect("login-register.jsp");
            }
        %>

    </body>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script>

                        var userid;
                        var outmuid;
                        var timer;
                        function hideloader(x) {
                            userid = x;
                            document.getElementById('loading').style.display = "none";
                            numberrefresh();
                            chatnumberrefresh();
                            refreshfrontmsg();
                        }
                        $(document).ready(function () {
                            $('#omnmsg').modal();
                            $('#peekprofile').modal();
                            $('.tooltipped').tooltip();
                            $("#btnSubmit").click(function (event) {
                                event.preventDefault();
                                var form = $('#fileUploadForm')[0];
                                var data = new FormData(form);
                                data.append("uid", userid);
                                data.append("muid", outmuid);
                                $("#btnSubmit").prop("disabled", true);
                                $.ajax({
                                    type: "POST",
                                    enctype: 'multipart/form-data',
                                    url: "newmessage",
                                    data: data,
                                    processData: false,
                                    contentType: false,
                                    cache: false,
                                    timeout: 600000,
                                    success: function (data) {
                                        $("#result").text(data);
                                        console.log("SUCCESS : ", data);
                                        $("#btnSubmit").prop("disabled", false);
                                        document.getElementById("msg").value = null;
                                        document.getElementById("filef").value = null;
                                        document.getElementById("filef").value = null;
                                    },
                                    error: function (e) {
                                        $("#result").text(e.responseText);
                                        console.log("ERROR : ", e);
                                        $("#btnSubmit").prop("disabled", false);

                                    }
                                });
                            });
                            $("#back").click(
                                    function () {
                                        clearTimeout(timer);
                                    });

                        });

                        function showmessages(uid, muid) {
                            outmuid = muid;
                            chatheaderassign();
                            seen();
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function () {
                                if (this.readyState == 4 && this.status == 200) {
                                    chatrefresh();
                                }
                            };
                            xhttp.open("GET", "directmessages?uid=" + uid + "&muid=" + muid, true);
                            xhttp.send();
                        }

                        function refreshfrontmsg() {
                            setTimeout(function () {
                                $('#frontmsgui').load("refreshmsgoverview?uid=" + userid);
                                chatnumberrefresh();
                                refreshfrontmsg();
                            }, 2000);
                        }

                        function numberrefresh() {
                            $('#notificationnumber').load("notificationnumber?uid=" + userid);
                            $('#notificationnumber2').load("notificationnumber?uid=" + userid);
                        }
                        function chatnumberrefresh() {
                            $('#chatnumber').load("chatnumber?uid=" + userid);
                        }


                        function chatrefresh() {
                            var objDiv = document.getElementById("peekmessage");
                            timer = setTimeout(function () {
                                $('#peekmessage').load("directmessages?uid=" + userid + "&muid=" + outmuid);
                                objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                                chatrefresh();
                            }, 1000);
                        }

                        var input = document.getElementById("msg");
                        input.addEventListener("keyup", function (event) {
                            event.preventDefault();
                            if (event.keyCode === 13) {
                                send();
                            }
                        });
                        function chatheaderassign() {
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function () {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("muidname").innerHTML = this.responseText;
                                }
                            };
                            xhttp.open("GET", "chatheader?uid=" + userid + "&muid=" + outmuid, true);
                            xhttp.send();
                        }
                        function seen() {
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.open("GET", "setmessageseen?uid=" + userid + "&muid=" + outmuid, true);
                            xhttp.send();
                        }
                        function showprofile() {
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function () {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("profilepeek").innerHTML = this.responseText;
                                    $('.collapsible').collapsible();
                                }
                            };
                            xhttp.open("GET", "peekprofile?q=" + outmuid + "&loggedusr=" + userid, true);
                            xhttp.send();
                        }
    </script>

</html>