<%-- 
    Document   : registerdetails

    Created on : Sep 21, 2018, 3:21:08 PM
    Author     : heymeowcat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String email = request.getParameter("mail");
    String hash = request.getParameter("hash");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css" media="screen,projection"/>
        <style>
            ::-webkit-scrollbar {
                width: 0px;
                background: transparent;
            }
            html, body {
                height:100%;
                min-height:100%;
                overflow: hidden;
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
            .modal { width: 70% !important ; height: 75% !important ; }
        </style>
    </head>
    <body onload="hideloader()" >
        <div id="loading">
            <div style="height: 100vh; width: 100%; background-color: white; text-align: center" class="valign-wrapper">
                <div class="progress" style="background-color:#fce4ec;">
                    <div class="indeterminate" style="background-color:#f8bbd0;"></div>
                </div>

            </div>
        </div>
        <%
            if (email != null & hash != null) {%>
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: white; " class="valign-wrapper ">
                <div class="row ">
                    <div class="col s12 m12 l12">
                        <ul class=" tabs">
                            <li class="tab col s12 m12 l12"><a class="active" href="#login">Register Step 2</a></li>
                        </ul>
                    </div>
                    <div id="login" class="col s12 ">
                        <div class="card-panel">
                            <form action="laststep?email=<%=email%>&hash=<%=hash%>" method="post">
                                Username
                                <div class="row">
                                    <div class="input-field col s12 m12 ">
                                        <input id="newusernamefield"  name="usn" type="text" class="validate" required="" oninput="newuser()" minlength="5">
                                        <p class="red-text" id="msg"></p>
                                    </div>
                                </div>

                                New Password
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  name="psn" type="password" class="validate" required="" minlength="8">
                                    </div>
                                </div>
                                Confirm Password
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  name="conpsn" type="password" class="validate" required="" minlength="8">
                                    </div>
                                </div>
                                <input type="submit" value="Register" class="pink lighten-4 black-text btn center">
                                <br>
                                <br>
                                <a  href="terms.html">Terms and Conditions</a> &
                                <a  href="privacy.html" >Privacy Policy</a>
                            </form>
                        </div>

                    </div>

                </div>

            </div>

                    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
                    <script type="text/javascript" src="js/materialize.min.js"></script>
                    <script>
                                    $(document).ready(function () {
                                        $('.tabs').tabs();
                                    });
                                    function hideloader() {
                                        document.getElementById('loading').style.display = "none";
                                    }
                                    function newuser() {
                                        var x = document.getElementById("newusernamefield").value;
                                        var xhtp = new XMLHttpRequest();
                                        xhtp.onreadystatechange = function () {
                                            if (xhtp.readyState == 4 && xhtp.status == 200) {
                                                var msg = xhtp.responseText;
                                                if (msg == 'exist') {
                                                    document.getElementById('msg').innerHTML = "Username Exist";
                                                } else if (msg == 'fine') {
                                                    document.getElementById('msg').innerHTML = "";
                                                }
                                            }
                                        }
                                        xhtp.open("GET", "newusernamecheck?x=" + x, true);
                                        xhtp.send();
                                    }

                    </script> 
                    </main>

                    <%} else {
                            response.sendRedirect("verify.jsp");
                        }
                    %>


                    </body>
                    </html>