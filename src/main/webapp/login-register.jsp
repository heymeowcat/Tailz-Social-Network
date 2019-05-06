<%-- 
    Document   : login-register
    Created on : Aug 31, 2018, 10:09:41 AM
    Author     : HEYMEOWCAT
--%>

<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="967347270850-d272m0f4a55431oe60n5052m910vq7sc.apps.googleusercontent.com">
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.css" rel="stylesheet" type="text/css" media="screen,projection"/>
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
            .home {
                height: 100vh;
                display: flex;
                text-align: center;
                align-items: center;
                justify-content: center;
            }

            h1 {
                font-family: "Montserrat", sans-serif;
                color: white;
                font-size: 42px;
                text-align: center;
                line-height: 1.56;
                text-shadow: 0px 1px 2px rgba(62, 57, 107, 0.4);
            }

            .home {
                background-size: 200%;
                background-image: linear-gradient(to bottom left, #fe87c3 0%, #828dfe 50%, #6d58f3 100%);
                transition: 600ms ease;
            }
            .home:hover {
                background-position: 100% 0;
            }
            html{
                height: 100%;
                max-width: 100%;
                overflow-x: hidden;
                background: url(img/bg.jpg)no-repeat center center fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            .tabs .tab a{
                color:#9c27b0;
            }

            .tabs .tab a:hover {
                background-color:#f3e5f5;
                color:#7b1fa2;
            } 

            .tabs .tab a.active {
                background-color:#f3e5f5;
                color:#9c27b0;
            } 
            .tabs .tab a:focus.active {
                background-color: #f3e5f5
            }
            .tabs .indicator {
                background-color:#ab47bc;
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
        </style>
    </head>

    <%
        java.sql.ResultSet configrs = DB.search("Select state from tailzconfig where id= '1' ");
        if (configrs.next()) {
            if (configrs.getInt(1) == 1) {
                java.sql.ResultSet googlers = DB.search("Select state from tailzconfig where id= '2' ");
                if (googlers.next()) {
                    if (googlers.getInt(1) == 1) {
    %>
    <body>
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: transparent; " class="valign-wrapper ">
                <div class="row ">
                    <h3 class="black-text center">TAILZ</h3>
                    <div class="g-signin2" data-onsuccess="onSignIn" data-theme="light"></div>
                </div>
            </div>

            <script src="https://apis.google.com/js/platform.js" async defer></script>
            <script>
                var profile;
                function onSignIn(googleUser) {
                    profile = googleUser.getBasicProfile();
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            window.location = "index.jsp";
                        }
                    };
                    xhttp.open("GET", "google?id=" + profile.getId() + "&name=" + profile.getName() + "&profilepic=" + profile.getImageUrl() + "&email=" + profile.getEmail(), true);
                    xhttp.send();
                }
            </script>
        </main>


    </body>
    <%
    } else {
    %>
    <body class="noselect">
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh;  " class="valign-wrapper transparent">

                <div class="row ">
                    <h3 class="black-text center">TAILZ</h3>
                    <div class="col s12 m12 l12">
                        <ul class=" tabs tabs-fixed-width ">
                            <li class="tab col s6 m6 l6"><a class="active" href="#login">Login</a></li>
                            <li class="tab col s6 m6 l6"><a  href="#signup">Sign up</a></li>
                            <li class="indicator" style="left: 0px; right: 0px;"></li>
                        </ul>
                    </div>
                    <div id="login" class="col s12 ">
                        <div class="card-panel" >
                            <form action="loginprocess" method="post">
                                <div class="input-field col s12 m12">
                                    <input  id="usn" name="usn" type="text" class="validate" required="">
                                    <label for="usn">Username</label>
                                </div>
                                <div class="input-field col s12 m12">
                                    <input  id="psn" name="psn" type="password" class="validate" required="">
                                    <label for="psn">Password</label>
                                    <%
                                        if (request.getParameter("error") != null) {
                                            out.write("<span class='red-text animated bounce'>");
                                            out.write(request.getParameter("error"));
                                            out.write("</span>");
                                        }
                                    %>
                                    <center>or<br>
                                        <div class="g-signin2 " data-onsuccess="onSignIn" data-theme="light" ></div></center>
                                </div>
                                <p class="right">
                                    <label>
                                        <input type="checkbox" name="check" value="remember">
                                        <span>Remember Me</span>
                                    </label>
                                </p>
                                <input type="submit" value="login" class="purple lighten-4 black-text btn center" >
                            </form>
                        </div>
                    </div>
                    <div id="signup" class="col s12">
                        <div class="card-panel">
                            <form action="register" method="post">
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  id="newmail" name="newmail" type="text" class="validate" required="">
                                        <label for="newmail">New Email</label>
                                    </div>
                                    <center>or<br>
                                        <div class="g-signin2 " data-onsuccess="onSignIn" data-theme="light" ></div></center>
                                </div>
                                <input type="submit" value="Next" class="purple lighten-4 black-text btn center" >
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script>
        var profile;
        function onSignIn(googleUser) {
            profile = googleUser.getBasicProfile();
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    window.location = "index.jsp";
                }
            };
            xhttp.open("GET", "google?id=" + profile.getId() + "&name=" + profile.getName() + "&profilepic=" + profile.getImageUrl() + "&email=" + profile.getEmail(), true);
            xhttp.send();
        }
        </script>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.js"></script>
        <script>
        $(document).ready(function () {
            $('.tabs').tabs();
            $("body").on("contextmenu", function (e) {
                return false;
            });
        });

        </script> 

        <%
            if (request.getSession().getAttribute("user") != null) {
        %>
        <div class="fixed-action-btn " >
            <a class="btn-floating btn-large purple lighten-4" href="index.jsp">       
                <i class="large material-icons black-text">skip_next</i>
            </a>
        </div>
        <%
            }%>

    </body>

    <%
            }
        }
    %>

    <%
    } else {
    %>

    <body>
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: transparent; " class="valign-wrapper ">
                <div class="row ">
                    <h5>Login Disabled.</h5>
                </div>
            </div>
            <script>
            </script> 
        </main>
    </body>

    <%
            }
        }
    %>


</html>
