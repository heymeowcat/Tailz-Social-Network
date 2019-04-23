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
                if (request.getSession().getAttribute("groupid") != null) {
                    String groupid = request.getSession().getAttribute("groupid").toString();
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
    <body onload="hideloader(<%=uid%>, '<%=groupid%>');" class="noselect animated fadeIn  faster">

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Bcolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row ">
                        <a href="messege.jsp" onclick="seen();"><i class="material-icons left modal-close  <%=Dcolor%> waves-effect  waves-circle " id="back">arrow_back</i></a>
                        <div onclick="$('#opncreategroup').modal('open');" class="<%=Bcolor%> <%=Dcolor%> truncate chip waves-effect waves-light center">
                            <% java.sql.ResultSet groupheaderrs = DB.search("Select groupimg,groupname from groups where `group_id`='" + groupid + "' ");
                                if (groupheaderrs.next()) {
                                    out.write("<img src='" + groupheaderrs.getString(1) + "'>");
                                    out.write(groupheaderrs.getString(2));
                                }
                            %>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <div id="opncreategroup" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
            <div class="container">
                <i class="material-icons right waves-effect modal-close <%=Dcolor%>">close</i>
                <div class="<%=Acolor%> center card-panel">
                    <div class="row">
                        <% java.sql.ResultSet openedgroup = DB.search("Select groupimg,groupname,groupadmin from groups where `group_id`='" + groupid + "' ");
                            if (openedgroup.next()) {%>
                        <img style='height: 150px; width: 150px' src='<%=openedgroup.getString(1)%>'  class='circle responsive-img hide-on-small-and-down animated fadeIn'>
                        <img style='height: 100px; width: 100px' src='<%=openedgroup.getString(1)%>'  class='circle responsive-img hide-on-med-and-up animated fadeIn'>
                        <h4 class='hide-on-small-and-down <%=Dcolor%>'><%=openedgroup.getString(2)%></h4>
                        <h5 class='hide-on-med-and-up <%=Dcolor%>'><%=openedgroup.getString(2)%></h5>

                        <table id="followsuggestionsid" class="<%=Acolor%> highlight" style="margin: 0px;padding: 0px" >
                            <%
                                java.sql.ResultSet rs2 = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers ='" + openedgroup.getString(3) + "' ");
                                if (rs2.next()) {
                            %>
                            <tr style="cursor: pointer" ><td  valign="middle" class="left" onclick="showprofile('<%=rs2.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');"><img src="<%=rs2.getString(3)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td onclick="showprofile('<%=rs2.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');" valign="middle " ><div class="<%=Dcolor%>"><%=rs2.getString(1)%> <%=rs2.getString(2)%> (Admin)</div></td><td valign="middle" class="right valign-wrapper"></td></tr>                                    
                                    <%
                                        java.sql.ResultSet rs22 = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers !='" + rs2.getString(4) + "' and users.idusers = ANY(SELECT members from group_members where Groups_group_id ='" + groupid + "')");
                                        while (rs22.next()) {
                                    %>
                            <tr style="cursor: pointer" ><td  valign="middle" class="left" onclick="showprofile('<%=rs22.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');"><img src="<%=rs22.getString(3)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td onclick="showprofile('<%=rs22.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');" valign="middle " ><div class="<%=Dcolor%>"><%=rs22.getString(1)%> <%=rs22.getString(2)%></div></td><td valign="middle" class="right valign-wrapper"></td></tr>
                                    <%
                                        }
                                        if (uid == rs2.getInt(4)) {%>
                            <button onclick="deletethisgroup('<%=groupid%>');" class="red btn-small white-text">DELETE GROUP</button><br> <br>       
                            <%
                                java.sql.ResultSet rs222 = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers NOT IN (SELECT `members` FROM `group_members` WHERE `Groups_group_id`='" + groupid + "') and users.idusers = ANY(SELECT idusers from users WHERE idusers = ANY(SELECT receiver FROM follow WHERE sender='" + uid + "') AND idusers= ANY(SELECT sender FROM follow WHERE receiver='" + uid + "')) ");
                                while (rs222.next()) {
                            %>
                            <tr style="cursor: pointer" ><td  valign="middle" class="left" onclick="showprofile('<%=rs222.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');"><img src="<%=rs222.getString(3)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td onclick="showprofile('<%=rs222.getString(4)%>', '<%=uid%>');$('#peekprofile').modal('open');" valign="middle " ><div class="<%=Dcolor%>"><%=rs222.getString(1)%> <%=rs222.getString(2)%></div></td><td valign="middle" class="right valign-wrapper"><h6><a class=" btn <%=Bcolor%> <%=Dcolor%> waves-effect" onclick="addthistogroup('<%=groupid%>', '<%=rs222.getString(4)%>')"><i class="material-icons">group_add</i></a></h6></td></tr>
                                    <%
                                        }
                                    } else {
                                    %>
                              <button onclick="leavethisgroup('<%=groupid%>','<%=uid%>');" class="<%=Bcolor%> btn-small <%=Dcolor%>">LEAVE GROUP</button><br> <br>       
                                    <%
                                                }
                                            }
                                        }
                                    %>
                        </table>
                    </div>

                </div>

            </div>
        </div>

        <div id="peekprofile" class=" modal bottom-sheet card" style="max-height:100%;background-color: <%=Ccolor%>"">
            <div id="profilepeek" class="container <%=Bcolor%> <%=Dcolor%>">

            </div>
        </div>  

        <main id="peekmessage" class="StickyContent <%= Acolor%> container" style="padding: 15px;border-radius: 5px;scroll-behavior: smooth;">
        </main>


        <footer class="StickyFooter container">
            <form method="POST" enctype="multipart/form-data" accept-charset="UTF-8" id="fileUploadForm" >
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
                    <input style="display: none" type="submit" value="Send" id="btnSubmit" class="<%=Dcolor%> <%=Bcolor%> btn-floating right" />
                    <a id="btnSubmit" class="<%=Dcolor%> <%=Bcolor%> btn-floating right"><i class="material-icons <%=Dcolor%>">send</i></a>
                </div>
            </form>
        </footer>
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script>
                                var userid;
                                var groupid;
                                var timer;
                                var objDiv = document.getElementById("peekmessage");
                                function hideloader(x, y) {
                                    userid = x;
                                    groupid = y;
                                    chatfirst();
                                }
                                $(document).ready(function () {
                                    $('#opncreategroup').modal();
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
                                                document.getElementById("filet").value = null;
                                            }
                                        };
                                        xhttp.open("GET", "newgroupmessage?src=" + fp + "&uid=" + userid + "&muid=" + groupid + "&msg=" + msg, true);
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

                                                }
                                            })
                                        }
                                );
                                function chatrefresh() {
                                    timer = setTimeout(function () {
                                        $('#peekmessage').load("groupmessages?uid=" + userid + "&muid=" + groupid);
                                        objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                                        chatrefresh();
                                    }, 1000);
                                }
                                function chatfirst() {
                                    $('#peekmessage').load("groupmessages?uid=" + userid + "&muid=" + groupid);
                                    objDiv.scrollTop = objDiv.scrollHeight * objDiv.scrollHeight;
                                    chatrefresh();
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
                                    xhttp.open("GET", "peekprofile?q=" + str + "&loggedusr=" + loggedurs, true);
                                    xhttp.send();
                                }
                                function addthistogroup(x, y) {
                                    var xhtp = new XMLHttpRequest();
                                    xhtp.onreadystatechange = function () {
                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                            window.location = "groupchathead.jsp";
                                        }
                                    }
                                    xhtp.open("GET", "addtothisgroup?x=" + x + "&y=" + y, true);
                                    xhtp.send();
                                }
                                function deletethisgroup(x) {
                                    var xhtp = new XMLHttpRequest();
                                    xhtp.onreadystatechange = function () {
                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                            window.location = "messege.jsp";
                                        }
                                    }
                                    xhtp.open("GET", "deletethisgroup?x=" + x, true);
                                    xhtp.send();
                                }
                                function leavethisgroup(x,y) {
                                    var xhtp = new XMLHttpRequest();
                                    xhtp.onreadystatechange = function () {
                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                            window.location = "messege.jsp";
                                        }
                                    }
                                    xhtp.open("GET", "leavethisgroup?x=" + x+"&y="+y, true);
                                    xhtp.send();
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
