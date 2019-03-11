<%-- 
    Document   : verify.jsp
    Created on : Sep 19, 2018, 11:49:03 PM
    Author     : HEYMEOWCAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
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
        <main class="StickyContent animated zoomIn ">
            <div style="height: 100vh; width: 100%; background-color: white; " class="valign-wrapper ">
                <div class="row ">
                    <h5>Verify Your Email Please.</h5>
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
    </body>
</html>
