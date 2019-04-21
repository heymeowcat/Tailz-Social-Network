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
        <title>Tailz-System-Config</title>
        <link href="img/logo.png" rel="icon">
        <link href="css/animate.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
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
                background-color: white;
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

        </style>
    </head>

    <%
        ServletContext context = getServletContext();
        if (request.getParameter("pwd") != null) {
            if (request.getParameter("pwd").equals(context.getInitParameter("adminpassword"))) {
    %>
    <body  class="noselect">
        <header class="StickyHeader" style="position:relative;  z-index: 10;">
            <nav class="white   ">
                <div class=" nav-wrapper LEFT container">
                    <a href="#!" class="brand-logo  "><B class="black-text truncate">TAILZ SYSTEM CONFIG</B></a>
                </div>
                </div>

            </nav>
        </header>

        <main class="StickyContent">
            <div class="animated zoomIn  container">
                <div class="divider"></div>
                <div class="section">
                    <h5>Log</h5>
                    <div class="row">

                    </div>
                </div>
                <div class="divider"></div>
                <div class="section">
                    <h5>Admin Accounts</h5>
                    <div class="row">
                        <% java.sql.ResultSet rsop = DB.search("Select idusers,firstname,lastname,email from `users` WHERE users.user_type_iduser_type ='1' "); %>

                        <table class="striped">
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Action</th>
                            </tr>
                            <%while (rsop.next()) {%>
                            <tr><td><%=rsop.getString(2) + " " + rsop.getString(3)%></td><td><%=rsop.getString(4)%></td><td><button class="white btn black-text waves-effect">Remove</button></td></tr>
                            <%
                                }%>
                            <tr>
                                <td colspan="2"><input id="at1" type="search"  class="" placeholder="Type Email." ></td>
                                <td><button  class="white btn  black-text waves-effect">+Admin</button></td>
                            </tr>
                            <tr colspan="2">
                                <td> 
                                    <table id="table" class="white">
                                    </table>
                                </td>

                            </tr>
                        </table>

                    </div>
                </div>
                <div class="divider"></div>
                <div class="section">
                    <h5>Database</h5>
                    <table>
                        <tr>
                            <td>Server</td>
                            <td><%=context.getInitParameter("Server")%></td>
                        </tr>
                        <tr>
                            <td>Port</td>
                            <td><%=context.getInitParameter("Port")%></td>
                        </tr>
                        <tr>
                            <td>Database</td>
                            <td><%=context.getInitParameter("DB")%></td>
                        </tr>
                        <tr>
                            <td>Username</td>
                            <td><%=context.getInitParameter("Username")%></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><%=context.getInitParameter("Password")%></td>
                        </tr>
                    </table>
                </div>
                <div class="divider"></div>
                <div class="section">
                    <h5>User Options Apply to All</h5>
                    <div class="card-panel">
                        <table>
                            <tr>
                                <td>
                                    <b>Login/Register</b> 
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Email Registration</td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Normal Login</td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Google Login</td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Facebook Login</td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Feed</b> 
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Image Post
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Youtube Post
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Embed Post
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Ad sections
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Profile</b> 
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   Update Profile 
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   Profile Settings
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Messages</b>
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Group Messages
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Notifications</b>
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Search</b>  
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>Dashboard</b> 
                                </td>
                                <td class="right">  
                                    <div class="switch">
                                        <label>
                                            Disable
                                            <input type="checkbox">
                                            <span class="lever"></span>
                                            Enable
                                        </label>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="section">
                    <h5>Individual User Options</h5>
                    <input id="t1" type="search"  class="black-text " placeholder="Type anything..." >
                    <table id="tb1" class="black-text">

                    </table>
                </div>

            </div>
        </main>

        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/materialize.js"></script>
        <script>
            var input = document.getElementById("t1");
            input.addEventListener("keyup", function (event) {
                event.preventDefault();
                if (event.keyCode === 13) {
                    search();
                    savesearchhistory();
                }
            });
            function search() {
                document.getElementById("tb1").innerHTML = "";
                var x = document.getElementById("t1").value;
                if (x == "") {
                    document.getElementById("tb1").innerHTML = "";
                }
                var xhtp = new XMLHttpRequest();
                xhtp.onreadystatechange = function () {
                    if (xhtp.readyState == 4 && xhtp.status == 200) {
                        document.getElementById("tb1").innerHTML += xhtp.responseText;
                    }
                }
                xhtp.open("GET", "profilesearch?name=" + x + "&loggedid=" + 5, true);
                xhtp.send();
            }

        </script>
    </body>


    <%      } else {
                out.write("<h2>Access Denied</h2>");
            }
        } else {
            out.write("<h2>Access Denied</h2>");
        }
    %>


</html>