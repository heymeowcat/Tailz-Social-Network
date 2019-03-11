<%-- 
    Document   : login-register
    Created on : Aug 31, 2018, 10:09:41 AM
    Author     : HEYMEOWCAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css" media="screen,projection"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh;  " class="valign-wrapper transparent">
                <div class="row ">
                    <div class="col s12 m12 l12">
                        <ul class=" tabs">
                            <li class="tab col s6 m6 l6"><a class="active" href="#login">Login</a></li>
                            <li class="tab col s6 m6 l6"><a  href="#signup">Sign up</a></li>
                        </ul>
                    </div>
                    <div id="login" class="col s12 ">
                        <div class="card-panel">
                            <form action="loginprocess" method="post">
                                Username
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  id="usn" name="usn" type="text" class="validate" required="">
                                    </div>
                                </div>
                                Password
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  id="psn" name="psn" type="password" class="validate" required="">
                                    </div>
                                </div>
                                <p class="right">
                                    <label>
                                        <input type="checkbox" name="check" value="remember">
                                        <span>Remember Me</span>
                                    </label>
                                </p>
                                <input type="submit" value="login" class="pink lighten-4 black-text btn center" onclick="getLocation()">

                            </form>

                        </div>



                    </div>
                    <div id="signup" class="col s12">
                        <div class="card-panel">
                            <form action="register" method="post">
                                Email
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  id="newemail" name="newemail" required=""  type="email" class="validate">
                                    </div>
                                </div>
                                <input type="submit" value="Next" class="pink lighten-4 black-text btn center" >
                            </form>
                        </div>
                    </div>
                </div>

            </div>


            <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script>
                                    $(document).ready(function () {

                                    });
                                    function
                                            hideloader() {
                                        document.getElementById('loading').style.display = "none";
                                    }
            </script> 
        </main>
        <%
            if (request.getSession().getAttribute("user") != null) {
        %>
        <div class="fixed-action-btn " >
            <a class="btn-floating btn-large pink lighten-4" href="index.jsp">       
                <i class="large material-icons black-text">skip_next</i>
            </a>
        </div>
        <%
               }%>

    </body>







</html>
