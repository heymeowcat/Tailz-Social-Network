<%-- 
    Document   : index
    Created on : Aug 21, 2018, 11:36:51 PM
    Author     : HEYMEOWCAT
--%>
<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <title>Tailz</title>
        <link href="img/logo.png" rel="icon">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="css/materialize.min.css" rel="stylesheet" type="text/css" media="screen,projection"/>
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
                margin-bottom: 0px;
                padding-bottom: 0px;
            }
            .toggle {
                position: absolute;
                font-size: calc(100px);
                cursor: pointer;
            }

            .toggle-btn::after {
                position: absolute;
                font-size: 0.35em;
                text-align: center;
            }
            .seedling-flower .toggle-btn::after {
                content: '😐';
            }
            .seedling-flower .toggle-checkbox:checked + .toggle-btn::after {
                content: '😍';
            }

        </style>
    </head>



    <body onload="hideloader(<%=uid%>);" class="noselect">
        <div id="loading" >
            <div style="height: 100vh; width: 100%; background-color: <%= Ccolor%>; text-align: center; " class="valign-wrapper">
                <div class="progress <%= Acolor%>">
                    <div class="indeterminate <%= Bcolor%>" ></div>
                </div>

            </div>
        </div>
        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="<%= Acolor%>">
                <div class="nav-wrapper center container " >
                    <div class="row hide-on-small-and-down ">
                        <div class="col s2  waves-effect waves-light  <%=Bcolor%> animated bounce" oncontextmenu="$('#opnmore').modal('open');return false;"><a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light ">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons  <%=Dcolor%>">message</i><div id="chatnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                    <div class="row hide-on-med-and-up ">
                        <div class="col s12 ">
                            <a class="brand-logo"><i class=" material-icons center <%=Dcolor%>">whatshot</i><B class="responsive-img <%=Dcolor%>">tailz</B></a>
                        </div>

                    </div>

            </nav>
        </header>



        <main class="StickyContent noselect" style="border: 0px; margin: 0px; bottom: 0px; padding: 0px;">
            <div class="container ">

                <div class="row">

                    <div class="col s12 m6 l8 animated zoomIn" style='height: 87vh; max-height:100% ; overflow: scroll;'>
                        <div class="row">
                            <%
                                try {
                                    java.sql.ResultSet rs = DB.search("Select * FROM `post` where users_idusers = ANY (SELECT `receiver` FROM follow WHERE sender ='" + uid + "' ) OR users_idusers = '" + uid + "' ORDER BY `post_time` DESC");
                                    while (rs.next()) {
                                        if (rs.getString(7).equals("1")) {%>
                            <div class="col s12 m12 l6 ">
                                <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable" >
                                    <div class="card-image">
                                        <img class="responsive-img" src="<%=rs.getString(3)%>">
                                    </div>
                                    <div class="card-content" >
                                        <span class="card-title <%=Dcolor%>"><b class="truncate<%=Dcolor%>"><%=rs.getString(2)%></b><i class="material-icons right activator waves-effect  <%=Dcolor%>">more_vert</i></span>    
                                        <p class="truncate"><%=rs.getString(4)%></p>
                                    </div>
                                    <div class=" <%=Dcolor%> <%=Acolor%>  card-reveal" >
                                        <span class="card-title <%=Dcolor%> text-darken-4 truncate "><%=rs.getString(2)%><i class="material-icons right waves-effect">close</i></span>
                                        <div class="card-content ">
                                            <a onclick="showprofile('<%=rs.getString(1)%>', '<%=uid%>');$('#peekprofile').modal('open');">
                                                <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                    <%
                                                        String imgup = "";
                                                        String fnamepost = "";
                                                        String lnamepost = "";
                                                        String postdate = "";
                                                        String posttime = "";
                                                        java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(6) + "' ");
                                                        if (imgpostuser.next()) {
                                                            imgup = imgpostuser.getString(1);
                                                        }
                                                        java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (firstimguser.next()) {
                                                            fnamepost = firstimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(6) + "' ");
                                                        if (lastimguser.next()) {
                                                            lnamepost = lastimguser.getString(1);
                                                        }
                                                        java.sql.ResultSet imgdate = DB.search("Select cast(post_time as date) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgdate.next()) {
                                                            postdate = imgdate.getString(1);
                                                        }
                                                        java.sql.ResultSet imgtime = DB.search("Select cast(post_time as time) From post where idpost='" + rs.getInt(1) + "' ");
                                                        if (imgtime.next()) {
                                                            posttime = imgtime.getString(1);
                                                        }
                                                    %>
                                                    <img src="<%=imgup%>">
                                                    <%= fnamepost + " " + lnamepost%>
                                                </div></a>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Date: <%=postdate%>
                                            </div>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Time:  <%=posttime%>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <%
                                                try {
                                                    java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                                                    if (!likechech.isBeforeFirst()) {
                                            %>
                                            <label class="toggle seedling-flower" >
                                                <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                            } else if (likechech.next()) {%>
                                            <label class="toggle seedling-flower" >
                                                <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                            <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%} else if (rs.getString(7).equals("2")) {%>
                            <div class="col s12 m12 l6 ">
                                <div class=" <%=Acolor%> <%=Dcolor%> card small hoverable ">
                                    <div class="card-image">
                                        <video class="responsive-video" controls="">
                                            <source src="<%=rs.getString(3)%>" type="video/mp4" >
                                        </video>

                                    </div>
                                    <div class="card-content">
                                        <span class="card-title <%=Dcolor%>"><b class="truncate <%=Dcolor%>"><%=rs.getString(2)%></b><i class="material-icons right activator waves-effect <%=Dcolor%>">more_vert</i></span>    
                                        <p class="truncate"><%=rs.getString(4)%></p>
                                    </div>
                                    <div class="<%=Acolor%> <%=Dcolor%> card-reveal">
                                        <i class="material-icons right waves-effect card-title <%=Dcolor%>">close</i>
                                        <span class="card-title <%=Dcolor%> text-darken-4 truncate"><%=rs.getString(2)%></span>
                                        <div class="card-content ">
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                <%
                                                    String imgup = "";
                                                    String fnamepost = "";
                                                    String lnamepost = "";
                                                    String postdate = "";
                                                    String posttime = "";
                                                    java.sql.ResultSet imgpostuser = DB.search("Select image From user_profile_pic where users_idusers='" + rs.getInt(6) + "' ");
                                                    if (imgpostuser.next()) {
                                                        imgup = imgpostuser.getString(1);
                                                    }
                                                    java.sql.ResultSet firstimguser = DB.search("Select firstname From users where idusers='" + rs.getInt(6) + "' ");
                                                    if (firstimguser.next()) {
                                                        fnamepost = firstimguser.getString(1);
                                                    }
                                                    java.sql.ResultSet lastimguser = DB.search("Select lastname From users where idusers='" + rs.getInt(6) + "' ");
                                                    if (lastimguser.next()) {
                                                        lnamepost = lastimguser.getString(1);
                                                    }
                                                    java.sql.ResultSet imgdate = DB.search("Select cast(post_time as date) From post where idpost='" + rs.getInt(1) + "' ");
                                                    if (imgdate.next()) {
                                                        postdate = imgdate.getString(1);
                                                    }
                                                    java.sql.ResultSet imgtime = DB.search("Select cast(post_time as time) From post where idpost='" + rs.getInt(1) + "' ");
                                                    if (imgtime.next()) {
                                                        posttime = imgtime.getString(1);
                                                    }
                                                %>
                                                <img src="<%=imgup%>">
                                                <%= fnamepost + " " + lnamepost%>
                                            </div>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Date: <%=postdate%>
                                            </div>
                                            <div class="<%=Bcolor%>  <%=Dcolor%> chip waves-effect waves-light ">
                                                Time:  <%=posttime%>
                                            </div>
                                        </div>
                                        <div class="card-action">
                                            <%
                                                try {
                                                    java.sql.ResultSet likechech = DB.search("Select likes from post_rank where likedby='" + uid + "' AND `post_rank`.`post_idpost` ='" + rs.getString(1) + "' ");
                                                    if (!likechech.isBeforeFirst()) {
                                            %>
                                            <label class="toggle seedling-flower" >
                                                <input type="checkbox" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                            } else if (likechech.next()) {%>
                                            <label class="toggle seedling-flower" >
                                                <input type="checkbox" checked="" class="toggle-checkbox" onchange="like('<%=rs.getString(1)%>', '<%=uid%>')">
                                                <div class="toggle-btn"></div>
                                            </label>
                                            <%
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            %>
                                            <i class=" material-icons right waves-effect waves-circle waves-light" onclick="$('#opncmnts').modal('open'); showpostcmnts('<%=uid%>', '<%=rs.getString(1)%>')">open_in_new</i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            %>
                            <% java.sql.ResultSet noptrs = DB.search("Select * FROM `post` where users_idusers = ANY (SELECT `receiver` FROM follow WHERE sender ='" + uid + "' ) OR users_idusers = '" + uid + "' ORDER BY `post_time` DESC");
                                if (!noptrs.isBeforeFirst()) {
                            %>
                            <div class='center'><img src='img/no-feeds.png' class='responsiveimg ' ></div>
                                <%
                                    }
                                %>
                        </div>
                    </div>

                    <div  class="col s12 m6 l4" style='height: 87vh; min-height:100% ; overflow: scroll'>
                        <div class="row" id="adshow" style="margin-bottom: 0px">


                        </div>
                        <div class="<%=Acolor%> card col s12 m12">
                            <div class='card-content'>
                                <span class='<%=Dcolor%> card-title'>Who to Follow</span>
                                <table id="followsuggestionsid" >
                                    <%
                                        boolean m = false;
                                        java.sql.ResultSet rs = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "') limit 5");
                                        while (rs.next()) {
                                            m=true;
                                    %>
                                    <tr><td  valign="middle" class="left"><img src="<%=rs.getString(3)%>" width="40px" height="40px" style="padding: 0; margin: 0" class="circle responsive-img  animated fadeIn"></td><td valign="middle " ><div class="<%=Dcolor%>"><%=rs.getString(1)%> <%=rs.getString(2)%></div></td><td valign="middle" class="right valign-wrapper"><h6><a class=" btn <%=Bcolor%> <%=Dcolor%> waves-effect" onclick="followthissugg('<%=rs.getString(4)%>')"><i class="material-icons">person_add</i></a></h6></td></tr>
                                            <%
                                                }
                                            %>
                                    <%if(m){%>
                                    <tr style="border-color: transparent"><td class="<%=Dcolor%>"><a class="<%=Dcolor%>" href="search-trending.jsp">More...</a></td></tr>
                                    <%}%>
                                 
                                </table>
                                <% java.sql.ResultSet rsop = DB.search("Select firstname,lastname,image,idusers from users join user_profile_pic on users.idusers = user_profile_pic.users_idusers WHERE users.idusers = ANY(SELECT `receiver`FROM follow WHERE `sender` = ANY(SELECT `receiver` FROM follow WHERE sender ='" + uid + "') ) AND NOT idusers='" + uid + "' AND NOT idusers =ANY(SELECT `receiver` FROM follow WHERE `sender` = '" + uid + "')");
                                    if (!rsop.isBeforeFirst()) {
                                %>
                                <div class='center'><img src='img/friendship.png' class='responsiveimg' ></div>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>


                </div>


            </div>
            <div class="fixed-action-btn hide-on-med-and-up" style="bottom:60px; right: 5px;">
                <a class="btn-floating btn-large <%=Bcolor%>">       
                    <i class="large material-icons <%=Dcolor%>">mode_edit</i>
                </a>
                <ul>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnimgpost').modal('open');">image</i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnvidpost').modal('open');">video_library</i></a></li>
                </ul>
            </div>
            <div class="fixed-action-btn hide-on-small-and-down">
                <a class="btn-floating btn-large <%=Bcolor%>">
                    <i class="large material-icons <%=Dcolor%>">mode_edit</i>
                </a>
                <ul>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnimgpost').modal('open');">image</i></a></li>
                    <li><a class="btn-floating <%=Bcolor%>"><i class="material-icons <%=Dcolor%>" onclick="$('#opnvidpost').modal('open');">video_library</i></a></li>
                </ul>
            </div>
            <div id="opncmnts" class="modal bottom-sheet card" style="min-height:100vh; background-color: <%=Ccolor%>">
                <div id="peekcomments" class="<%=Bcolor%> <%=Dcolor%> container ">

                </div>
            </div>
            <div id="opnmore" class="modal bottom-sheet card" style="background-color: <%=Ccolor%>">
                <div id="peekcomments" class="<%=Bcolor%> <%=Dcolor%> container">
                    <ul class="<%=Acolor%> <%=Dcolor%> collapsible" style="border-color: <%=Ccolor%>">
                        <li>
                            <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><i class="material-icons">filter_list</i>Feed Type</div>
                            <div class="<%=Acolor%> collapsible-body" style="border-color: <%=Ccolor%>"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                        <li>
                            <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><i class="material-icons">filter</i>Filters</div>
                            <div class="<%=Acolor%> collapsible-body" style="border-color: <%=Ccolor%>"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>
                        <li>
                            <div class="<%=Acolor%> collapsible-header" style="border-color: <%=Ccolor%>"><i class="material-icons">group</i>Groups</div>
                            <div class="<%=Acolor%> collapsible-body" style="border-color: <%=Ccolor%>"><span>Lorem ipsum dolor sit amet.</span></div>
                        </li>

                    </ul>
                </div>
            </div>
            <div id="opnvidpost" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
                <div class="container">
                    <div class="<%=Acolor%> card-panel">
                        <form action="vidpost" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
                            <h5 class="<%=Dcolor%>">New Video Post<i class="material-icons right waves-effect modal-close">close</i></h5>
                            <div class="row">
                                <div class="file-field input-field col s12 m6">
                                    <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                        <span>File</span>
                                        <input type="file" name="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate <%=Dcolor%>" type="text" >
                                    </div>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Title" name="title" type="text" class="validate <%=Dcolor%>">
                                </div>
                                <div class="input-field col s12">
                                    <input placeholder="Description" name="description"  type="text" class="validate <%=Dcolor%>" >
                                </div>
                            </div>
                            <input type="submit" value="Post" class="<%=Bcolor%> <%=Dcolor%> btn">
                        </form>
                    </div>
                </div>
            </div>
            <div id="opnimgpost" class=" modal bottom-sheet card" style="max-height:100%; background-color: <%=Ccolor%>">
                <div class="container">
                    <div class="<%=Acolor%> card-panel">
                        <form action="newpost" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
                            <h5 class="<%=Dcolor%>">New Image Post <i class="material-icons right waves-effect modal-close">close</i></h5>
                            <div class="row">
                                <div class="file-field input-field col s12 m6">
                                    <div class="<%=Bcolor%> <%=Dcolor%> btn">
                                        <span>File</span>
                                        <input type="file" name="file" >
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate <%=Dcolor%>" type="text" >
                                    </div>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Title" name="title" type="text" class="validate <%=Dcolor%>" >
                                </div>
                                <div class="input-field col s12">
                                    <input placeholder="Description" name="description"  type="text" class="validate <%=Dcolor%>" >
                                </div>
                            </div>
                            <input type="submit" value="Post" class="<%=Bcolor%> <%=Dcolor%> btn" >
                        </form>
                    </div>
                </div>
            </div>

            <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
            <script type="text/javascript" src="js/materialize.min.js"></script>
            <script>
                        var selectedpid;
                        var userid;
                        function hideloader(x) {
                            userid = x
                            document.getElementById('loading').style.display = "none";
                            numberrefresh();
                            chatnumberrefresh();
                            adsrefresh();
                        }
                        $(document).ready(function () {
                            $('#opncmnts').modal();
                            $('#opnvidpost').modal();
                            $('#opnimgpost').modal();
                            $('#opnmore').modal();
                            $('.fixed-action-btn').floatingActionButton();
                            $('.materialboxed').materialbox();
                            $('.collapsible').collapsible();

                        });
                        function like(x, uid) {
                            setTimeout(function () {
                                var xhttp;
                                xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState == 4 && this.status == 200) {
                                        //document.getElementById("profilepeek").innerHTML = this.responseText;
                                    }
                                };
                                xhttp.open("GET", "likeprocess?x=" + x + "&uid=" + uid, true);
                                xhttp.send();
                            }, 1000);

                        }

                        function showpostcmnts(uid, pid) {
                            selectedpid = pid;
                            userid = uid;
                            refresh()
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function () {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("peekcomments").innerHTML = this.responseText;
                                    $('.materialboxed').materialbox();
                                }
                            };
                            xhttp.open("GET", "peekcomments?uid=" + uid + "&pid=" + pid, true);
                            xhttp.send();
                        }



                        function comment(x, y) {
                            var z = document.getElementById("commenttextarea").value;
                            var xhttp;
                            xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function () {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("commentsection").innerHTML = this.responseText;
                                    document.getElementById("commenttextarea").value = "";
                                }
                            };
                            xhttp.open("GET", "commentsload?x=" + x + "&y=" + y + "&z=" + z, true);
                            xhttp.send();
                        }

                        function refresh() {
                            setTimeout(function () {
                                $('#commentsection').load("commentlive?uid=" + userid + "?&pid=" + selectedpid);
                                refresh();
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
                        function adsrefresh() {
                            setTimeout(function () {
                                $('#adshow').load("finaladdsforindexrefresh");
                                adsrefresh();
                            }, 1000);
                        }
                        el.addEventListener('contextmenu', function (ev) {
                            ev.preventDefault();
                            $('#opnmore').modal('open');
                            return false;
                        }, false);
                                                function followthissugg(x) {
                                                    var xhtp = new XMLHttpRequest();
                                                    xhtp.onreadystatechange = function () {
                                                        if (xhtp.readyState == 4 && xhtp.status == 200) {
                                                            document.getElementById("followsuggestionsid").innerHTML = xhtp.responseText;
                                                        }
                                                    }
                                                    xhtp.open("GET", "newsuggfollowindex?x=" + x + "&loggedid=" + userid, true);
                                                    xhtp.send();
                                                }
            </script> 
        </main>



        <footer class="StickyFooter hide-on-med-and-up ">
            <nav class="<%=Acolor%>">
                <div class=" nav-wrapper center z-depth-5" style=" width: 100%">
                    <div class="row">
                        <div class="col s2  waves-effect waves-light  <%=Bcolor%> animated bounce" oncontextmenu="$('#opnmore').modal('open');return false;">   <a href="index.jsp"><i class="  material-icons <%=Dcolor%>">library_books</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="profile.jsp"><i class=" material-icons <%=Dcolor%> ">account_circle</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="messege.jsp"><i class=" material-icons <%=Dcolor%> ">message</i><div id="chatnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="notifications.jsp"><i class=" material-icons <%=Dcolor%>">notifications_none</i><div id="notificationnumber2" class="badge <%=Dcolor%>" style="position: absolute; top:0px"></div></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="search-trending.jsp"><i class=" material-icons <%=Dcolor%>">search</i></a></div>
                        <div class="col s2 waves-effect waves-light">   <a href="dashboard.jsp"><i class=" material-icons <%=Dcolor%>">dashboard</i></a></div>
                    </div>
                </div>
            </nav>
        </footer>
        <%        } else {
                response.sendRedirect("login-register.jsp");
            }
        %>

    </body>
</html>
