<%-- 
    Document   : chathead
    Created on : Apr 16, 2019, 11:51:33 PM
    Author     : heymeowcat
--%>

<%@page import="com.heymeowcat.tailznet.KEY"%>
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
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link rel="stylesheet" href="css/balloon.css">
        <%
            if (request.getSession().getAttribute("user") != null) {
                int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                if (request.getSession().getAttribute("muid") != null) {
                    int muid = Integer.parseInt(request.getSession().getAttribute("muid").toString());
                    String up = "";
                    String Acolor = "";
                    String Bcolor = "";
                    String Ccolor = "";
                    String Dcolor = "";
                    String Ecolor = "";
                    String Fcolor = "";
                    java.sql.ResultSet themers; { PreparedStatement ps = DB.prepare("SELECT themename FROM app_theme WHERE users_idusers=?"); ps.setInt(1, uid); themers = ps.executeQuery(); }
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
    <body onload="hideloader(<%=uid%>,<%=muid%>);" class="noselect animated fadeIn  faster">
        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Bcolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row ">
                        <a href="messege.jsp" onclick="seen();"><i class="material-icons left modal-close  <%=Dcolor%> waves-effect  waves-circle " id="back">arrow_back</i></a>
                        <div onclick="showprofile('<%=muid%>', '<%=uid%>');$('#peekprofile').modal('open');"class="<%=Bcolor%> <%=Dcolor%> truncate chip waves-effect waves-light center">
                            <% String name = "";
                                String url = "";
                                java.sql.ResultSet rs; { PreparedStatement ps = DB.prepare("SELECT image FROM user_profile_pic WHERE users_idusers=?"); ps.setInt(1, muid); rs = ps.executeQuery(); }
                                if (rs.next()) {
                                    url = rs.getString(1);
                                }
                                java.sql.ResultSet rs1; { PreparedStatement ps = DB.prepare("SELECT CONCAT(firstname,' ',lastname) FROM users WHERE idusers=?"); ps.setInt(1, muid); rs1 = ps.executeQuery(); }
                                if (rs1.next()) {
                                    name = rs1.getString(1);
                                }
                                out.write("<img src='" + url + "'>");
                                out.write(name);
                            %>
                        </div>
                    </div>
                </div>
            </nav>
        </header>


        <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
            <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

            </div>
        </div>                  

        <main id="peekmessage" class="StickyContent <%= Acolor%> container" style="padding: 15px;border-radius: 5px;scroll-behavior: smooth;">
        </main>
        <footer class="StickyFooter container">
            <form method="POST" enctype="multipart/form-data" accept-charset="UTF-8" id="fileUploadForm">
                <input class="<%=Dcolor%>" name="msg" required=""  id="msg"  type="text"  placeholder="New message">
                <div class="file-field input-field">
                    <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                        <span ><i class="material-icons <%=Dcolor%>">image</i></span>
                        <input type="file" name="file" id="filef" required="">
                    </div>
                    <div style="display: none">
                        <div class="file-path-wrapper">
                            <input class="file-path validate <%=Dcolor%>" type="text" id="filet">
                        </div>
                    </div>
                    <input style="display: none" type="submit" value="Send" id="btnSubmit" class="<%=Dcolor%> <%=Bcolor%> btn right" />
                    <a id="btnSubmit" class="<%=Dcolor%> <%=Bcolor%> btn-floating right"><i class="material-icons <%=Dcolor%>">send</i></a>
                </div>
            </form>
        </footer>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script>
                            var userid;
                            var outmuid;
                            var timer;
                            function hideloader(x, y) {
                                userid = x;
                                outmuid = y;
                                chatfirst();
                                seen();
                            }
                            $(document).ready(function () {
                                $('#peekprofile').modal();
                                $("body").on("contextmenu", function (e) {
                                    return false;
                                });
                                $("#btnSubmit").click(function (event) {
                                    event.preventDefault();
                                    var $files = $('#filef').get(0).files;
                                    var fp;
                                    var msg = document.getElementById("msg").value;
                                    if ($files.length) {
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
                                            document.getElementById("msg").value = null;
                                            document.getElementById("filef").value = null;
                                            document.getElementById("filet").value = null

                                        }
                                    };
                                    xhttp.open("GET", "newmessage?src=" + fp + "&uid=" + userid + "&muid=" + outmuid + "&msg=" + msg, true);
                                    xhttp.send();
                                });
                                $("#back").click(
                                        function () {
                                            clearTimeout(timer);
                                        });
                                $("#peekmessage").scroll(
                                        function () {
                                            clearTimeout(timer);
                                        });

                            });
                            jQuery(
                                    function ($)
                                    {
                                        $('#peekmessage').bind('scroll', function ()
                                        {
                                            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight)
                                            {
                                                chatrefresh();
                                                seen();
                                            }
                                        })
                                    }
                            );
                            function showprofile(str, loggedurs) {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("profilepeek").innerHTML = this.responseText;
                                        $('.collapsible').collapsible();
                                    }
                                };
                                xhttp.open("GET", "peekprofile?q=" + str + "&loggedusr=" + loggedurs, true);
                                xhttp.send();
                            }
                            function refreshChat() {
                                var objDiv = document.getElementById("peekmessage");
                                $('#peekmessage').load("directmessages?uid=" + userid + "&muid=" + outmuid, function () {
                                    objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                                });
                            }
                            function chatrefresh() {
                                var objDiv = document.getElementById("peekmessage");
                                timer = setTimeout(function () {
                                    $('#peekmessage').load("directmessages?uid=" + userid + "&muid=" + outmuid);
                                    objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                                    chatrefresh();
                                }, 5000);
                            }
                            function chatfirst() {
                                var objDiv = document.getElementById("peekmessage");
                                $('#peekmessage').load("directmessages?uid=" + userid + "&muid=" + outmuid);
                                objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;

                                chatrefresh();
                            }

                            var input = document.getElementById("msg");
                            input.addEventListener("keyup", function (event) {
                                event.preventDefault();
                                if (event.keyCode === 13) {

                                }
                            });
                            function seen() {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.open("GET", "setmessageseen?uid=" + userid + "&muid=" + outmuid, true);
                                xhttp.send();
                            }

                            var ws;
                            function initWebSocket() {
                                if (typeof WebSocket !== "undefined") {
                                    ws = new WebSocket("ws://" + window.location.host + "/Tailz/websocket/chat");
                                    ws.onopen = function () {
                                        ws.send(JSON.stringify({type: "register", uid: userid}));
                                    };
                                    ws.onmessage = function (event) {
                                        var data = JSON.parse(event.data);
                                        if (data.type === "new_message") {
                                            refreshChat();
                                        }
                                    };
                                    ws.onerror = function (err) {
                                        console.log("WebSocket error: ", err);
                                    };
                                    ws.onclose = function () {
                                        setTimeout(initWebSocket, 5000);
                                    };
                                } else {
                                    console.log("WebSocket not supported by this browser.");
                                }
                            }
                            $(document).ready(function () {
                                initWebSocket();
                            });

        </script>
        <%   } else {
                    response.sendRedirect("messege.jsp");
                }
            } else {
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
