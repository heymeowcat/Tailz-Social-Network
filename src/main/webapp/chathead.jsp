<%-- 
    Document   : chathead
    Created on : Apr 16, 2019, 11:51:33 PM
    Author     : heymeowcat
--%>

<%@page import="com.heymeowcat.tailznet.KEY"%>
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
    <body onload="hideloader(<%=uid%>,<%=muid%>);" class="noselect animated fadeIn  faster">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Bcolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row ">
                        <a href="messege.jsp" onclick="seen();"><i class="material-icons left modal-close  <%=Dcolor%> waves-effect  waves-circle " id="back">arrow_back</i></a>
                        <div  id="muidname" class="<%=Bcolor%> <%=Dcolor%> truncate chip waves-effect waves-light center"></div>
                    </div>
                </div>
            </nav>
        </header>
        <div id="loading" >
            <div style="width: 100%; background-color: transparent; text-align: center;  " class="valign-wrapper">
                <div class="progress <%= Acolor%>" style="margin: 0px">
                    <div class="indeterminate <%= Bcolor%>" ></div>
                </div>

            </div>
        </div>
        <main id="peekmessage" class="StickyContent <%= Acolor%> container" style="padding: 15px;border-radius: 5px">

        </main>


        <footer class="StickyFooter container">
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
            document.getElementById('loading').style.display = "none";
            chatheaderassign();
            chatfirst();
            seen();
        }
        $(document).ready(function () {
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
                        document.getElementById("filet").value = null;
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

        function chatrefresh() {
            var objDiv = document.getElementById("peekmessage");
            timer = setTimeout(function () {
                $('#peekmessage').load("directmessages?uid=" + userid + "&muid=" + outmuid);
                objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                chatrefresh();
            }, 1000);
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
