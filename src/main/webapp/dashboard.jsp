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
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,600,700,900" rel="stylesheet">
        <link href="css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>

        <%
            System.gc();
            if (request.getSession().getAttribute("user") != null) {
               int uid = Integer.parseInt(request.getSession().getAttribute("user").toString());
                String up = "";
                String Acolor = "";
                String Bcolor = "";
                String Ccolor = "";
                String Dcolor = "";
                String Ecolor = "";
                java.sql.ResultSet themers = DB.search("Select themename from app_theme where users_idusers= '" + uid + "' ");
                if (themers.next()) {
                    if (themers.getString(1).equals("pinkdark")) {
                        Acolor = "black";
                        Bcolor = "pink";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                    } else if (themers.getString(1).equals("pinklight")) {
                        Acolor = "white";
                        Bcolor = "pink lighten-4";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "red lighten-5";
                    } else if (themers.getString(1).equals("bluelight")) {
                        Acolor = "white";
                        Bcolor = "light-blue lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-blue lighten-5";
                    } else if (themers.getString(1).equals("bluedark")) {
                        Acolor = "black";
                        Bcolor = "blue";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                    } else if (themers.getString(1).equals("yellowlight")) {
                        Acolor = "white";
                        Bcolor = "yellow lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "yellow lighten-4";
                    } else if (themers.getString(1).equals("yellowdark")) {
                        Acolor = "black";
                        Bcolor = "yellow darken-4";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                    } else if (themers.getString(1).equals("greenlight")) {
                        Acolor = "white";
                        Bcolor = "light-green lighten-2";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "light-green lighten-4";
                    } else if (themers.getString(1).equals("greendark")) {
                        Acolor = "black";
                        Bcolor = "green";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
                    } else if (themers.getString(1).equals("purplelight")) {
                        Acolor = "white";
                        Bcolor = "purple lighten-3";
                        Ccolor = "#f7f4f4";
                        Dcolor = "black-text";
                        Ecolor = "purple lighten-5";
                    } else if (themers.getString(1).equals("purpledark")) {
                        Acolor = "black";
                        Bcolor = "purple";
                        Ccolor = "#1c1c1c";
                        Dcolor = "white-text";
                        Ecolor = "grey darken-4";
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
                background-image: url(img/banner.png);
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
                    margin: 20px;
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
        </style>
    </head>
    <body onload="hideloader(<%=uid%>);" class="noselect">
        <div id="loading">
            <div style="height: 100vh; width: 100%; background-color: <%= Ccolor%>; text-align: center;" class="valign-wrapper">
                <div class="progress <%= Acolor%>">
                    <div class="indeterminate <%= Bcolor%>" ></div>
                </div>

            </div>
        </div>

        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center container">
                    <div class="row hide-on-small-and-down">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%> animated bounce">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up">
                        <div class="col s6">
                            <a href="#!" class="brand-logo"><i class=" material-icons center <%=Dcolor%>">whatshot</i><B class="<%=Dcolor%>">tailz</B></a>
                        </div>
                    </div>
                </div>

            </nav>
        </header>

        <main class="StickyContent">
            <div class="animated zoomIn  container">
                <ul class="<%=Acolor%> collapsible " style="border-color: <%=Ccolor%>">
                    <%
                        int pads = 0;
                        java.sql.ResultSet purchadzrs = DB.search("Select count(status) from purchase_history where status=1 and users_idusers='" + uid + "'");
                        if (purchadzrs.next()) {
                            pads = Integer.parseInt(purchadzrs.getString(1));
                        }
                    %>
                    <li class="active">
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Overview</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-1 resizeimg">
                                        <h6>Live Ads</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-2 resizeimg">
                                        <h6>Expired Ads</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-3 resizeimg">
                                        <h6>Rate Per Hour</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-4 resizeimg">
                                        <h6>Ads to Submit</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-5 resizeimg">
                                        <h6>Ads to go Live</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                                <div class="col s12 m12 l4 ">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-0 card-6 resizeimg">
                                        <h6>Transactions</h6>
                                        <h4>1</h4>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Live Advertisements</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div id="liveads" class="row">

                                <%
                                    java.sql.ResultSet ss = DB.search("Select Adid from ads where status='4' and users_idusers=" + uid + " order by (SELECT `adstartedtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC   ");
                                    while (ss.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,Text,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ss.getString(1) + "' and status='4'");
                                        while (adstolivers.next()) {
                                %>

                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ss.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden"> 
                                                <a href="<%=adstolivers.getString(4)%>">
                                                <img src="<%=adstolivers.getString(3)%>" >
                                            </a>
                                            </div>
                                           
                                        </div>
                                        <div class="card-action">
                                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                <span><i class="material-icons <%=Dcolor%>">pause</i></span>
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
                                    java.sql.ResultSet ssexp = DB.search("Select Adid from ads where status='6' order by (SELECT `adendtime` FROM adtiming WHERE Ads_Adid = ads.Adid) DESC");
                                    while (ssexp.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,Text,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + ssexp.getString(1) + "' and status='6'");
                                        while (adstolivers.next()) {
                                %>
                                <div class="col s12 m12 l4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=ssexp.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <div class="card-image resizeimg" style="overflow: hidden">
                                            <a href="<%=adstolivers.getString(4)%>">
                                                <img src="<%=adstolivers.getString(3)%>" >
                                            </a>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                <span><i class="material-icons <%=Dcolor%>">refresh</i></span>
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
                                <div class="col s12 m6">
                                    <div class="<%=Acolor%> <%=Dcolor%> card-panel">
                                        <form method="post" enctype="multipart/form-data" action="completethead?adid=<%=adstosubmitrs.getString(1)%>">
                                            <b class="grey-text">Ad Id:#<%=adstosubmitrs.getString(1)%></b>
                                            <div class="row">
                                                <div class="input-field col s12 m6">
                                                    <input checked placeholder="Advertisement Text" id="first_name" name="title" type="text" class="validate <%=Dcolor%>" minlength="3" maxlength="60">
                                                </div>
                                                <div class="input-field col s12 m6">
                                                    <input placeholder="Advertisement URL" id="last_name" name="link" type="text" class="validate <%=Dcolor%>" value="" minlength="3">
                                                </div>
                                            </div>
                                            <div class="file-field input-field">
                                                <div class="<%=Bcolor%> <%=Dcolor%> btn-floating center">
                                                    <span ><i class="material-icons <%=Dcolor%>">file_upload</i></span>
                                                    <input name="file" type="file" id="file" >
                                                </div>
                                                <div style="display: none">
                                                    <div class="file-path-wrapper">
                                                        <input class="file-path validate <%=Dcolor%>" type="text" >
                                                    </div>
                                                </div>
                                                <button onclick="setadcomplete(<%=adstosubmitrs.getString(1)%>);;" class="<%=Bcolor%> <%=Dcolor%> btn right">Submit</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>

                                <%
                                        }
                                    }
                                    java.sql.ResultSet rsno = DB.search("Select * from ads where status='2' and users_idusers=" + uid + "");
                                    if (!rsno.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/nolive.png' class='animated pulse responsiveimg '></div>
                                    <%
                                        }
                                    %>
                            </div>

                    </li>
                    <li>
                        <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><b class="<%=Dcolor%>">Advertisements to Go Live</b></div>
                        <div class="<%=Ecolor%> collapsible-body " style="border-color: <%=Ccolor%>">
                            <div class="row">
                                <%
                                    java.sql.ResultSet togolivers = DB.search("Select Adid from ads where status='3' and users_idusers=" + uid + "");
                                    while (togolivers.next()) {
                                        java.sql.ResultSet adstolivers = DB.search("Select Adid,Text,src,link,users_idusers,forhowmanyusers,forhowmanyhours from ads where Adid='" + togolivers.getString(1) + "' and status='3'");
                                        while (adstolivers.next()) {
                                %>
                                <div class="col s12 m4">
                                    <b class="letter-spacing: ; grey-text">Ad Id:#<%=togolivers.getString(1)%></b>
                                    <div class="card <%=Acolor%> ">
                                        <div class="card-content <%=Dcolor%>">
                                            <span class="card-title truncate"><%=adstolivers.getString(2)%></span>
                                            <a href="<%=adstolivers.getString(4)%>">
                                                <img src="<%=adstolivers.getString(3)%>" class="responsive-img ">
                                            </a>
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
                                    java.sql.ResultSet rs = DB.search("Select * from ads where status='3' and users_idusers=" + uid + "");
                                    if (!rs.isBeforeFirst()) {
                                %>
                                <div class='center'><img src ='img/nolive.png' class='animated pulse responsiveimg '></div>
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


                </ul>
            </div>
        </main>

        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light ">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons  <%=Dcolor%>">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%>">message</i></a><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light <%=Bcolor%>  animated bounce">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>

        </footer>
    </body>
    <%        } else {
            response.sendRedirect("login-register.jsp");
        }
    %>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
                                                    var userid;
                                                    $(document).ready(function () {
                                                        $('.collapsible').collapsible();
                                                        $('#peekprofile').modal();
                                                        $('#opncmnts').modal();
                                                        $('#peekdelpost').modal();
                                                        $('select').formSelect();
                                                        $('#commentsection').load('commentlive');
                                                        $('.materialboxed').materialbox();
                                                    });
                                                    function hideloader(x) {
                                                        userid = x
                                                        document.getElementById('loading').style.display = "none";
                                                        numberrefresh();
                                                        chatnumberrefresh();
                                                        liveadsrefresh();
                                                        expiredrefresh();
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
                                                        var xhtp = new XMLHttpRequest();
                                                        xhtp.onreadystatechange = function () {
                                                            if (xhtp.readyState == 4 && xhtp.status == 200) {
                                                                document.getElementById("followsuggestionsid").innerHTML = xhtp.responseText;
                                                            }
                                                        }
                                                        xhtp.open("GET", "newsuggfollow?x=" + x + "&loggedid=" + userid, true);
                                                        xhtp.send();
                                                    }

    </script>
</html>