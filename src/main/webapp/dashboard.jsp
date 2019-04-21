<%@page import="com.heymeowcat.tailznet.KEY"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.heymeowcat.tailznet.ENCDEC"%>
<!-- 
    Document   : dashboard
    Created on : Aug 26, 2018, 4:24:27 PM
    Author     : HEYMEOWCAT
-->

<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
                <script src="js/pace.js"></script>
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <%
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
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                        Fcolor = "black";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                        Fcolor = "white";
                    }
                }
                java.sql.ResultSet usp = DB.search("Select image FROM `user_profile_pic` where users_idusers='" + uid + "' ");
                if (!usp.isBeforeFirst()) {
                    up = "img/Profile_avatar_placeholder_large.png";
                } else if (usp.next()) {
                    up = usp.getString(1);
                }
        %> 
        <style>
            .noselect {
                -webkit-touch-callout: none; /* iOS Safari */
                -webkit-user-select: none; /* Safari */
                -khtml-user-select: none; /* Konqueror HTML */
                -moz-user-select: none; /* Firefox */
                -ms-user-select: none; /* Internet Explorer/Edge */
                user-select: none; /* Non-prefixed version, currently
                                      supported by Chrome and Opera */
            }
            ::-webkit-scrollbar {
                width: 0px;
                background: transparent;
            }
            html, body {
                height:100%;
                min-height:100%;
                overflow: hidden;
                background-color: <%=Ccolor%>;
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
            video { 
                position: fixed;
                top: 50%;
                left: 50%;
                min-width: 100%;
                min-height: 100%;
                width: auto;
                height: auto;
                z-index: -100;
                -webkit-transform: translateX(-50%) translateY(-50%);
                transform: translateX(-50%) translateY(-50%);
                background-size: cover;
                transition: 1s opacity;
            }

            .card-0{
                border-radius: 4px;
                box-shadow: 0 6px 10px rgba(0,0,0,.08), 0 0 6px rgba(0,0,0,.05);
                transition: .3s transform cubic-bezier(.155,1.105,.295,1.12),.3s box-shadow,.3s -webkit-transform cubic-bezier(.155,1.105,.295,1.12);
                padding: 14px 80px 14px 36px;
                cursor: pointer;
                margin-top: 18px;
            }

            .card-0:hover{
                transform: scale(1.05);
                box-shadow: 0 10px 20px rgba(0,0,0,.12), 0 4px 8px rgba(0,0,0,.06);
            }


            .card-0 img{
                position: absolute;
                top: 20px;
                right: 15px;
                max-height: 120px;
            }

            .card-1{
                background-image: url(img/livead.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }

            .card-2{
                background-image: url(img/email.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }

            .card-3{
                background-image: url(img/brochure.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }
            .card-4{
                background-image: url(img/adsads.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }
            .card-5{
                background-image: url(img/advertisingww.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }
            .card-6{
                background-image: url(img/letter.png);
                background-repeat: no-repeat;
                background-position: right;
                background-size: 80px;
            }

            @media(max-width: 990px){
                .card-0{
                    margin: 10px;
                    margin-left: 0px;
                    margin-right: 0px;
                }
            } 

            @media (max-width: 990px) {
                .resizeimg {
                    height: auto;
                }
            }

            @media (min-width: 1000px) {
                .resizeimg {
                    width: auto;
                    height: 160px;
                }
            }
            ul.dropdown-content.select-dropdown li span {
                background-color:  <%=Acolor%>;
                color: <%=Fcolor%>
            }
            input:not([type]), input[type=text]:not(.browser-default), input[type=password]:not(.browser-default), input[type=email]:not(.browser-default), input[type=url]:not(.browser-default), input[type=time]:not(.browser-default), input[type=date]:not(.browser-default), input[type=datetime]:not(.browser-default), input[type=datetime-local]:not(.browser-default), input[type=tel]:not(.browser-default), input[type=number]:not(.browser-default), input[type=search]:not(.browser-default), textarea.materialize-textarea {
                color: <%=Fcolor%>;
            }
            .select-wrapper .caret {
                fill: <%=Fcolor%>;
            }
        </style>
    </head>
    <body onload="hideloader(<%=uid%>);" class="noselect">


        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated rubberBand">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up">
                        <div class="col s6">
                            <a class="brand-logo"><B class="<%=Dcolor%>">TAILZ</B></a>
                        </div>
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
        <%
            java.sql.ResultSet usertypers = DB.search("Select user_type_iduser_type from users where status=1 and idusers='" + uid + "'");
            if (usertypers.next()) {
                if (usertypers.getInt(1) == 2) {
        %>
        <main class="StickyContent">
            <div class="animated fadeIn  container">
                <div class="section no-pad-bot" id="index-banner">
                    <div class="container <%=Dcolor%>">

                        <h1 class="header center ">Advertise on Tailz</h1>
                        <div class="row center">
                            <h5 class="header col s12 light">Explore Tailz Advertisement Management Dashboard. </h5>
                        </div>
                        <div class="row center">
                            <a href="converttocustomer?uid=<%=uid%>" id="download-button" class="btn-large waves-effect waves-light <%=Bcolor%> <%=Dcolor%>">Get Started</a>
                        </div>
                        <br><br><br>

                    </div>
                </div>


                <div class="section">

                    <!--   Icon Section   -->
                    <div class="row <%=Dcolor%>">
                        <div class="col s12 m4">
                            <div class="icon-block">
                                <h2 class="center"><img src="img/dash.png" width="100px"></h2>
                                <h5 class="center">All-in-one Dashboard</h5>
                            </div>
                        </div>

                        <div class="col s12 m4">
                            <div class="icon-block">
                                <h2 class="center"><img src="img/uef.png" width="100px"></h2>
                                <h5 class="center">User Experience Focused</h5>
                            </div>
                        </div>

                        <div class="col s12 m4">
                            <div class="icon-block">
                                <h2 class="center"><img src="img/ezpz.png" width="100px"></h2>
                                <h5 class="center">Easy to work with</h5></div>
                        </div>
                    </div>

                </div>

                <div class="center grey-text">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
            </div>
        </main>
        <%} else if (usertypers.getInt(1) == 3) {%>
        <main class="StickyContent">
            <div class="animated fadeIn  container">
                <ul class="<%=Acolor%> collapsible " style="border-color: <%=Ccolor%>">
                    <%
                        int lads = 0;
                        int expads = 0;
                        int rateperhour = 0;
                        int adstosubmit = 0;
                        int adstogolive = 0;
                        int pads = 0;
                        java.sql.ResultSet purchadzrs = DB.search("Select count(status) from purchase_history where status=1 and users_idusers='" + uid + "'");
                        if (purchadzrs.next()) {
                            pads = Integer.parseInt(purchadzrs.getString(1));
                        }
                        java.sql.ResultSet liveadsrs = DB.search("Select count(status) from ads where status=4 and users_idusers='" + uid + "'");
                        if (liveadsrs.next()) {
                            lads = Integer.parseInt(liveadsrs.getString(1));
                        }
                        java.sql.ResultSet expadsrs = DB.search("Select count(status) from ads where status=6 and users_idusers='" + uid + "'");
                        if (expadsrs.next()) {
                            expads = Integer.parseInt(expadsrs.getString(1));
                        }
                        java.sql.ResultSet rateperhourrs = DB.search("Select idAPPHPI from apphpi");
                        if (rateperhourrs.next()) {
                            rateperhour = Integer.parseInt(rateperhourrs.getString(1));
                        }
                        java.sql.ResultSet adstosubrs = DB.search("Select count(status) from ads where status=2 and users_idusers='" + uid + "'");
                        if (adstosubrs.next()) {
                            adstosubmit = Integer.parseInt(adstosubrs.getString(1));
                        }
                        java.sql.ResultSet adstogolivers = DB.search("Select count(status) from ads where status=3 and users_idusers='" + uid + "'");
                        if (adstogolivers.next()) {
                            adstogolive = Integer.parseInt(adstogolivers.getString(1));
                        }
                    %>
                    <li id="overviewli" class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Overview</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <div class="col s12 m12 l4" onclick="expandliveads();">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-1 resizeimg">
                                        <h6>Live Ads</h6>
                                        <h4><%=lads%></h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 " onclick="expandexpads();">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-2 resizeimg">
                                        <h6>Expired Ads</h6>
                                        <h4><%=expads%></h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 " onclick="expandp();">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-3 resizeimg">
                                        <h6>Rate Per Hour</h6>
                                        <h4>Rs.<%=rateperhour%></h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 " onclick="expandats()">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-4 resizeimg">
                                        <h6>Ads to Submit</h6>
                                        <h4><%=adstosubmit%></h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 " onclick="expandatg();">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-5 resizeimg">
                                        <h6>Ads to go Live</h6>
                                        <h4><%=adstogolive%></h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 " onclick="expandtran();">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-6 resizeimg">
                                        <h6>Transactions</h6>
                                        <h4><%=pads%></h4>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </li>
                    <li id="liveli">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Live Advertisements</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div id="liveads" class="row">

                                <%
                                    java.sql.ResultSet ss = DB.search("Select Adid from ads where status='4' and users_idusers='" + uid + "' order by (SELECT `adstartedtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC   ");
                                    while (ss.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ss.getString(1) + "' and status='4'");
                                        while (adstolivers.next()) {
                                %>

                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ss.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden"> 
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>

                                        </div>
                                        <div class="card-action">
                                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                <span><i class="material-icons <%=Dcolor%>">live_tv</i></span>
                                            </div>
                                            <%
                                                String timeremainingfrad = "";
                                                java.sql.ResultSet timedifrs = DB.search("SELECT TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP),(SELECT CAST(TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP) AS CHAR) FROM sEXUaFqh92.adtiming WHERE Ads_Adid ='" + ss.getInt(1) + "') FROM adtiming WHERE Ads_Adid ='" + ss.getInt(1) + "' ");
                                                if (timedifrs.next()) {
                                                    if (timedifrs.getString(2).startsWith("-")) {
                                                        DB.iud("UPDATE `ads` SET `status` = '6'  WHERE `ads`.`Adid` = '" + ss.getInt(1) + "';");
                                                        timeremainingfrad = "Expired";
                                                    } else {
                                                        DB.iud("UPDATE `ads` SET `status` = '4'  WHERE `ads`.`Adid` = '" + ss.getInt(1) + "';");
                                                        timeremainingfrad = timedifrs.getString(1);
                                                    }
                                                }
                                            %>
                                            <button class=" <%=Acolor%> <%=Dcolor%> right btn-flat  "><%=timeremainingfrad%></button>
                                        </div>  
                                    </div>  
                                </div>
                                <%
                                        }
                                    }

                                %>

                            </div>

                        </div>
                    </li>

                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Expired Advertisements</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div id="expiredads" class="row">
                                <%
                                    java.sql.ResultSet ssexp = DB.search("Select Adid from ads where status='6' and users_idusers='" + uid + "' order by (SELECT `adendtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC");
                                    while (ssexp.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ssexp.getString(1) + "' and status='6'");
                                        while (adstolivers.next()) {
                                %>
                                <div class="col s12 m12 l4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ssexp.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden">
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                <span><i class="material-icons <%=Dcolor%>">do_not_disturb_on</i></span>
                                            </div>
                                            <%
                                                String timeremainingfrad = "";
                                                java.sql.ResultSet timedifrs = DB.search("SELECT TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP),(SELECT CAST(TIMEDIFF(`adendtime`,CURRENT_TIMESTAMP) AS CHAR) FROM sEXUaFqh92.adtiming WHERE Ads_Adid ='" + ssexp.getInt(1) + "') FROM adtiming WHERE Ads_Adid ='" + ssexp.getInt(1) + "'  ");
                                                if (timedifrs.next()) {
                                                    if (timedifrs.getString(2).startsWith("-")) {
                                                        timeremainingfrad = "Expired";
                                                    } else {
                                                        timeremainingfrad = timedifrs.getString(1);
                                                        DB.iud("UPDATE `ads` SET `status` = '4'  WHERE `ads`.`Adid` = '" + ssexp.getInt(1) + "';");
                                                    }
                                                }
                                            %>
                                            <button class=" <%=Acolor%> <%=Dcolor%> right btn-flat  "><%=timeremainingfrad%></button>
                                        </div>  
                                    </div>  
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Advertisements to Submit</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div id="purchasedalladsdiv" class="row">
                                <%
                                    java.sql.ResultSet tosubortolivers = DB.search("Select Adid from ads where status='2' and users_idusers=" + uid + " ");
                                    while (tosubortolivers.next()) {
                                        java.sql.ResultSet adstosubmitrs = DB.search("Select Adid,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + tosubortolivers.getString(1) + "' and status='2'");
                                        while (adstosubmitrs.next()) {
                                %>
                                <div class="col s12 m12 l6">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-panel">
                                        <b class="grey-text">Ad Id:#<%=adstosubmitrs.getString(1)%></b>
                                        <input type="hidden" value="<%=adstosubmitrs.getString(1)%>" id="adid<%=adstosubmitrs.getString(1)%>">
                                        <div class="row">
                                            <div class="input-field col s12 m6">
                                                <select id="cate<%=adstosubmitrs.getString(1)%>">
                                                    <%java.sql.ResultSet adcaters = DB.search("Select idAd_category,Ad_category_name from ad_categories WHERE Ad_category_name !='All' ");
                                                        while (adcaters.next()) {%>
                                                    <option value="<%=adcaters.getString(1)%>"><%=adcaters.getString(2)%></option>
                                                    <%}%>
                                                </select>
                                                <label>Select Ad Category</label>
                                            </div>
                                            <div class="input-field col s12 m6">
                                                <input placeholder="Advertisement URL" id="url<%=adstosubmitrs.getString(1)%>" type="text" class="validate <%=Dcolor%>" value="" minlength="3">
                                            </div>
                                        </div>
                                        <div class="file-field input-field">
                                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                <span ><i class="material-icons <%=Dcolor%>">file_upload</i></span>
                                                <input  type="file" id="file<%=adstosubmitrs.getString(1)%>" >
                                            </div>
                                            <div style="display: none">
                                                <div class="file-path-wrapper">
                                                    <input class="file-path validate <%=Dcolor%>" type="text" >
                                                </div>
                                            </div>
                                            <button onclick="setadcomplete(<%=adstosubmitrs.getString(1)%>);refreshhhh();" class="<%=Bcolor%> <%=Dcolor%> btn right" id="btn<%=adstosubmitrs.getString(1)%>">Submit</button>
                                        </div>
                                    </div>
                                </div>

                                <%
                                        }
                                    }
                                    java.sql.ResultSet rsno = DB.search("Select * from ads where status='2' and users_idusers=" + uid + "");
                                    if (!rsno.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/adsads.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>
                            </div>

                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Advertisements to Go Live</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%try {
                                        java.sql.ResultSet togolivers = DB.search("Select Adid from ads where status='3' and users_idusers='" + uid + "' ");
                                        while (togolivers.next()) {
                                            java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + togolivers.getString(1) + "' and status='3'");
                                            while (adstolivers.next()) {
                                %>
                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=adstolivers.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden"> 
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>

                                        </div>
                                        <div class="card-action">
                                            <form action="adtiming" method="post">
                                                <input name="adid" type="hidden" value="<%=togolivers.getString(1)%>">
                                                <a href="profile.jsp" class="<%=Dcolor%>"><input type="submit" value="Go Live" class="<%=Bcolor%> <%=Dcolor%> btn center"></a>
                                            </form>
                                        </div>
                                    </div>  
                                </div>
                                <%
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    java.sql.ResultSet rs = DB.search("Select * from ads where status='3' and users_idusers=" + uid + "");
                                    if (!rs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/advertisingww.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>
                            </div>
                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Purchase New Advertisement Spaces</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="<%=Dcolor%>">
                                <form action="purchase" method="post">
                                    <%
                                        double rate = 0;
                                        java.sql.ResultSet raters = DB.search("Select idAPPHPI from apphpi");
                                        if (raters.next()) {
                                            rate = Double.parseDouble(raters.getString(1));
                                        }
                                    %>
                                    Per Hour Rate
                                    <input class="<%=Dcolor%> type="text" disabled value=" Rs.<%=rate%>"><br>
                                    <input id="rate" name="rate" type="hidden" value="<%=rate%>">
                                    <input  name="uid" type="hidden" value="<%=uid%>">
                                    Add Spaces 
                                    <input id="spaces" oninput="gentotal();" class="<%=Dcolor%>" name="spaces" type="number" min="1" value="1" ><br>
                                    Hours Per Add in this bundle
                                    <input id="hours"  oninput="gentotal();" class="<%=Dcolor%>" name="hours" type="number" min="1" value="1"><br>
                                    Total Price
                                    <input id="totalplace" class="<%=Dcolor%> type="text" disabled value="Rs.<%=rate%>"><br><br>
                                    <input type="submit" value="Purchase" class="<%=Bcolor%> <%=Dcolor%> btn">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Transactions</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="<%=Dcolor%>">
                                <%try {
                                        java.sql.ResultSet purchasehistoryrs = DB.search("Select idpurchase_history from purchase_history where status=1 and users_idusers='" + uid + "'");
                                        if (!purchasehistoryrs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/letter.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                    } else {
                                    %>
                                <table class="<%=Acolor%> <%=Dcolor%>">
                                    <tr>
                                        <th>Id</th>
                                        <th>Date</th>
                                        <th class="hide-on-small-and-down">Rate</th>
                                        <th class="hide-on-small-and-down">Hours</th>
                                        <th class="hide-on-small-and-down">Total</th>
                                        <th>Print</th>
                                    </tr>
                                    <%
                                        java.sql.ResultSet purchasehistoryrs2 = DB.search("Select idpurchase_history from purchase_history where status=1 and users_idusers='" + uid + "'  ORDER BY `purchase_history`.`idpurchase_history` DESC ");
                                        while (purchasehistoryrs2.next()) {
                                            java.sql.ResultSet purchasehistoryrs3 = DB.search("Select idpurchase_history,datetime,rate,hours,total from purchase_history where status=1 and users_idusers='" + uid + "' and idpurchase_history='" + purchasehistoryrs2.getInt(1) + "' ");
                                            if (purchasehistoryrs3.next()) {
                                    %>
                                    <tr>
                                        <td><%=purchasehistoryrs3.getString(1)%></td>
                                    <iframe hidden="" src="printprompt.jsp?printid=<%=purchasehistoryrs3.getInt(1)%>" name="frame<%=purchasehistoryrs3.getInt(1)%>"></iframe>
                                    <td><%=purchasehistoryrs3.getString(2)%></td>
                                    <td class="hide-on-small-and-down">Rs.<%=purchasehistoryrs3.getString(3)%>/=</td>
                                    <td class="hide-on-small-and-down"><%=purchasehistoryrs3.getString(4)%></td>
                                    <td class="hide-on-small-and-down">Rs.<%=purchasehistoryrs3.getString(5)%>/=</td>
                                    <td >
                                        <button class="<%=Bcolor%> <%=Dcolor%> btn" onclick="frames['frame<%=purchasehistoryrs3.getInt(1)%>'].print()" ><i class="material-icons">print</i></button></td>
                                    </tr>
                                    <%}
                                            }
                                        }
                                    %>
                                </table>
                                <%
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                %>

                            </div>
                        </div>
                    </li>


                </ul>
                <div class="grey-text">Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

            </div>
        </main>

        <%} else if (usertypers.getInt(1) == 1) {%>
        <main class="StickyContent">
            <div class="animated zoomIn  container">
                <ul class="<%=Acolor%> collapsible expandable " style="border-color: <%=Ccolor%>">
                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">All Live Advertisements</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">

                                <%
                                    java.sql.ResultSet ss = DB.search("Select Adid from ads where status='4' order by (SELECT `adstartedtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC   ");
                                    while (ss.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ss.getString(1) + "' and status='4'");
                                        while (adstolivers.next()) {
                                %>

                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ss.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden"> 
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>

                                        </div>

                                    </div>  
                                </div>
                                <%
                                        }
                                    }
                                    java.sql.ResultSet rslivss = DB.search("Select Adid from ads where status='4'");
                                    if (!rslivss.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/livead.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>


                            </div>

                        </div>
                    </li>

                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">All Expired Advertisements</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%
                                    java.sql.ResultSet ssexp = DB.search("Select Adid from ads where status='6' order by (SELECT `adendtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC");
                                    while (ssexp.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ssexp.getString(1) + "' and status='6'");
                                        while (adstolivers.next()) {
                                %>
                                <div class="col s12 m12 l4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ssexp.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden">
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <%
                                        }
                                    }

                                    java.sql.ResultSet rs = DB.search("Select Adid from ads where status='6'");
                                    if (!rs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/email.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>
                                %>
                            </div>
                        </div>
                    </li>
                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">All Advertisements to Go Live</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%try {
                                        java.sql.ResultSet togolivers = DB.search("Select Adid from ads where status='3' ");
                                        while (togolivers.next()) {
                                            java.sql.ResultSet adstolivers = DB.search("Select Adid,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + togolivers.getString(1) + "' and status='3'");
                                            while (adstolivers.next()) {
                                %>
                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=adstolivers.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden"> 
                                                <a href="<%=adstolivers.getString(3)%>">
                                                    <img src="<%=adstolivers.getString(2)%>" >
                                                </a>
                                            </div>
                                        </div>
                                    </div>  
                                </div>
                                <%
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    java.sql.ResultSet rsadsmb = DB.search("Select * from ads where status='3'");
                                    if (!rsadsmb.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/advertisingww.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>
                            </div>
                    </li>
                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Change Price Rate Per Hour</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="<%=Dcolor%>">
                                <form action="changerate" method="post">
                                    <%
                                        double rate = 0;
                                        java.sql.ResultSet raters = DB.search("Select idAPPHPI from apphpi");
                                        if (raters.next()) {
                                            rate = Double.parseDouble(raters.getString(1));
                                        }
                                    %>
                                    Per Hour Rate
                                    <input class="<%=Dcolor%>" name="rate" type="number" value="<%=rate%>"><br>
                                    <input type="submit" value="Change" class="<%=Bcolor%> <%=Dcolor%> btn">
                                </form>
                            </div>
                        </div>
                    </li>
                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">All Transactions</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="<%=Dcolor%>">
                                <%try {
                                        java.sql.ResultSet purchasehistoryrs = DB.search("Select idpurchase_history from purchase_history where status=1");
                                        if (!purchasehistoryrs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/letter.png' height="130px" class='animated pulse responsiveimg '></div>
                                    <%
                                    } else {
                                    %>
                                <table class="<%=Acolor%> <%=Dcolor%>">
                                    <tr>
                                        <th>Id</th>
                                        <th>Date</th>
                                        <th>Rate</th>
                                        <th>Hours</th>
                                        <th>Total</th>
                                        <th>Print</th>
                                    </tr>
                                    <%
                                        java.sql.ResultSet purchasehistoryrs2 = DB.search("Select idpurchase_history from purchase_history where status=1   ORDER BY `purchase_history`.`idpurchase_history` DESC ");
                                        while (purchasehistoryrs2.next()) {
                                            java.sql.ResultSet purchasehistoryrs3 = DB.search("Select idpurchase_history,datetime,rate,hours,total from purchase_history where status=1  and idpurchase_history='" + purchasehistoryrs2.getInt(1) + "' ");
                                            if (purchasehistoryrs3.next()) {
                                    %>
                                    <tr>
                                        <td><%=purchasehistoryrs3.getString(1)%></td>
                                    <iframe hidden="" src="printprompt.jsp?printid=<%=purchasehistoryrs3.getInt(1)%>" name="frame<%=purchasehistoryrs3.getInt(1)%>"></iframe>
                                    <td><%=purchasehistoryrs3.getString(2)%></td>
                                    <td>Rs.<%=purchasehistoryrs3.getString(3)%>/=</td>
                                    <td><%=purchasehistoryrs3.getString(4)%></td>
                                    <td>Rs.<%=purchasehistoryrs3.getString(5)%>/=</td>
                                    <td >
                                        <button class="<%=Bcolor%> <%=Dcolor%> btn" onclick="frames['frame<%=purchasehistoryrs3.getInt(1)%>'].print()" ><i class="material-icons">print</i></button></td>
                                    </tr>
                                    <%}
                                            }
                                        }
                                    %>
                                </table>
                                <%
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                %>

                            </div>
                        </div>
                    </li>


                </ul>

            </div>
        </main>
        <%}
            }
        %>


        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%>">message</i></a><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%>  animated rubberBand">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>

        </footer>

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
                                            var userid;
                                            var elem = document.querySelector('.collapsible.expandable');
                                            var instance = M.Collapsible.init(elem, {
                                                accordion: false
                                            });
                                            $(document).ready(function () {
                                                $('.collapsible').collapsible();
                                                $('#peekprofile').modal();
                                                $('#opncmnts').modal();
                                                $('#peekdelpost').modal();
                                                $('select').formSelect();
                                                $('#commentsection').load('commentlive');
                                                $('.materialboxed').materialbox();
                                                $("body").on("contextmenu", function (e) {
                                                    return false;
                                                });
                                            });
                                            function hideloader(x) {
                                                userid = x
                                                document.getElementById('loading').style.display = "none";
                                                numberrefresh();
                                                chatnumberrefresh();
                                                liveadsrefresh();
                                                expiredrefresh();
                                            }
                                            function expandp() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(5)
                                            }
                                            function expandexpads() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(2)
                                            }
                                            function expandliveads() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(1)
                                            }
                                            function expandats() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(3)
                                            }
                                            function expandtran() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(6)
                                            }
                                            function expandatg() {
                                                var instance = M.Collapsible.getInstance($('.collapsible'));
                                                instance.open(4)
                                            }
                                            function liveadsrefresh() {
                                                setTimeout(function () {
                                                    $('#liveads').load("liveadsrefresh");
                                                    liveadsrefresh();
                                                }, 1000);
                                            }
                                            function expiredrefresh() {
                                                setTimeout(function () {
                                                    $('#expiredads').load("expiredadsrefresh");
                                                    $('.tooltipped').tooltip();
                                                    expiredrefresh();
                                                }, 1000);
                                            }
                                            function numberrefresh() {
                                                setTimeout(function () {
                                                    $('#notificationnumber').load("notificationnumber?uid=" + userid);
                                                    $('#notificationnumber2').load("notificationnumber?uid=" + userid);
                                                    numberrefresh();
                                                }, 1000);
                                            }
                                            function chatnumberrefresh() {
                                                setTimeout(function () {
                                                    $('#chatnumber').load("chatnumber?uid=" + userid);
                                                    $('#chatnumber2').load("chatnumber?uid=" + userid);
                                                    chatnumberrefresh();
                                                }, 1000);
                                            }
                                            function gentotal() {
                                                var rate = document.getElementById("rate").value;
                                                var spaces = document.getElementById("spaces").value;
                                                var hours = document.getElementById("hours").value;
                                                var total = rate * spaces * hours;
                                                document.getElementById('totalplace').value = "Rs." + total + ".0";
                                            }
                                            function setadcomplete(x) {
                                                var $files = $('#file' + x).get(0).files;
                                                var fp;
                                                var adid = document.getElementById("adid" + x).value;
                                                var cate = document.getElementById("cate" + x).value;
                                                var urlll = document.getElementById("url" + x).value;
                                                console.log(cate);
                                                if ($files.length) {
                                                    // Reject big files
                                                    if ($files[0].size > $(this).data("max-size") * 1024) {
                                                        console.log("Please select a smaller file");
                                                        return false;
                                                    }
                                                    // Begin file upload
                                                    console.log("Uploading file to Imgur..");

                                                    // Replace ctrlq with your own API key
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

                                                    // Response contains stringified JSON
                                                    // Image URL available at response.data.link
                                                    $.ajax(settings).done(function (response) {
                                                        var obj = JSON.parse(response);
                                                        fp = obj.data.link;
                                                    });

                                                }
                                                var xhttp;
                                                xhttp = new XMLHttpRequest();
                                                xhttp.onreadystatechange = function () {
                                                    if (this.readyState == 4 && this.status == 200) {

                                                    }
                                                };
                                                xhttp.open("GET", "completethead?src=" + fp + "&adid=" + adid + "&cate=" + cate + "&url=" + urlll, true);
                                                xhttp.send();

                                            }
                                            function refreshhhh() {
                                                window.location.replace("dashboard.jsp");
                                            }

        </script>
        <%} else {
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