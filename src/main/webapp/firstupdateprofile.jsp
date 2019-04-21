<%-- 
    Document   : firstupdateprofile
    Created on : Sep 22, 2018, 12:05:38 PM
    Author     : heymeowcat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String uid = request.getParameter("uid");
%>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.css" rel="stylesheet" type="text/css" media="screen,projection"/>
        <style>
            ::-webkit-scrollbar {
                width: 0px;
                background: transparent;
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
        </style>
    </head>
    <body>
        <%
           if (uid != null) {
        %>
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: transparent; " class="valign-wrapper ">
                <div class="row ">
                    <div class="col s12 m12 l12">
                        <ul class=" tabs">
                            <li class="tab col s12 m12 l12"><a class="active" href="#login">Final Step</a></li>
                        </ul>
                    </div>
                    <div id="login" class="col s12 ">
                        <div class="card-panel">
                            <form method="post" action="firstupdateprofile?uid=<%=uid%>">
                                First Name
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input   name="fn" type="text" class="validate" required="">
                                    </div>
                                </div>
                                Last Name
                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input  name="ln" type="text" class="validate" required="">
                                    </div>
                                </div>
                                <input type="submit" value="Lets Go" class="purple lighten-4 black-text btn center">
                            </form>
                        </div>

                    </div>

                </div>

            </div>

                                    
            <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.js"></script>
            <script>
                                            $(document).ready(function () {
                                                $('#opncmnts').modal();
                                                $('#opnvidpost').modal();
                                                $('#opnimgpost').modal();
                                                $('.tabs').tabs();
                                                $('.fixed-action-btn').floatingActionButton();
                                                $('.materialboxed').materialbox();
                                            });
            </script> 
        </main>
        <%
           } else {
                response.sendRedirect("registerdetails.jsp");
           }
        %>


    </body>
</html>