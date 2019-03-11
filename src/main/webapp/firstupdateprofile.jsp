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
            if (uid != null) {
        %>

        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: white; " class="valign-wrapper ">
                <div class="row ">
                    <div class="col s12 m12 l12">
                        <ul class=" tabs">
                            <li class="tab col s12 m12 l12"><a class="active" href="#login">Final Step</a></li>
                        </ul>
                    </div>
                    <div id="login" class="col s12 ">
                        <div class="card-panel">
                            <form method="post" enctype="multipart/form-data" action="firstupdateprofile?uid=<%=uid%>">
                                Change Profile Picture
                                <div class="file-field input-field">
                                    <div class="white black-text btn">
                                        <span>Chose Image</span>
                                        <input type="file"  name="file" onchange="showImage.call(this)" >
                                        <script>
                                            function showImage() {
                                                if (this.files && this.files[0]) {
                                                    var obj = new FileReader();
                                                    obj.onload = function (data) {
                                                        var image = document.getElementById("image");
                                                        var image2 = document.getElementById("image2");
                                                        image.src = data.target.result;
                                                        image2.src = data.target.result;

                                                    }
                                                    obj.readAsDataURL(this.files[0]);
                                                }
                                            }
                                        </script>
                                    </div>
                                    <div class="file-path-wrapper" style="width: 0">
                                        <input class="file-path validate" type="text" >
                                    </div>
                                </div>
                                <div class="img center">
                                    <img id="image" style="height: 100px; width: 100px" src="img/Profile_avatar_placeholder_large.png" alt="" class="circle responsive-img hide-on-small-and-down animated fadeIn">
                                    <img id="image2" style="height: 100px; width: 100px;" src="img/Profile_avatar_placeholder_large.png" alt="" class="circle responsive-img hide-on-med-and-up animated fadeIn">
                                    <br>
                                </div>
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


                                <input type="submit" value="Lets Go" class="pink lighten-4 black-text btn center">
                            </form>
                        </div>

                    </div>

                </div>

            </div>


            <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script>
                                            $(document).ready(function () {
                                                $('#opncmnts').modal();
                                                $('#opnvidpost').modal();
                                                $('#opnimgpost').modal();
                                                $('.tabs').tabs();
                                                $('.fixed-action-btn').floatingActionButton();
                                                $('.materialboxed').materialbox();
                                            });
                                            function hideloader() {
                                                document.getElementById('loading').style.display = "none";
                                            }

            </script> 
        </main>
        <%
            } else {
                response.sendRedirect("registerdetails.jsp");
            }
        %>


    </body>
</html>