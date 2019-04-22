<%-- 
    Document   : groups
    Created on : Apr 21, 2019, 7:51:46 PM
    Author     : heymeowcat
--%>

<%@page import="com.heymeowcat.tailznet.DB"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create a new Group</h1>
    </body>
    <form method="POST" action="testing">
        Group Name<br>
        <input type="text" name="groupname"><br>
        Member<br>
        <input type="number" name="memberid"><br>
        
        <input type="submit" value="create a group">
    </form>
    <h1>Groups</h1>
    <table border="1">
        <tr>
            <th>Group Id</th>
            <th>Group Name</th>
            <th>Group Image</th>
            <th>Group Admin</th>
            <th>Group Members</th>
            <th>Group Chat</th>
        </tr>
        <%
            ResultSet rs = DB.search("SELECT * FROM `groups`");
            while (rs.next()) {
        %>
        <tr>
            <td><%=rs.getString(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><img src="<%=rs.getString(3)%>"</td>
            <td><%=rs.getString(4)%></td>
            <td>
                <table border="1">
                    <%
                        ResultSet rs1 = DB.search("SELECT members FROM `group_members` where Groups_group_id='" + rs.getString(1) + "' ");
                        while (rs1.next()) {
                    %>
                    <tr>
                        <td><%=rs1.getString(1)%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
            <td>
                <table border="1">
                    <%
                        ResultSet rs2 = DB.search("SELECT users_idusers,chat_text FROM `group_chat` where Groups_group_id='" + rs.getString(1) + "' ");
                        while (rs2.next()) {
                    %>
                    <tr>
                        <td><%=rs2.getString(1)%></td>
                        <td><%=rs2.getString(2)%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
        <%
            }
        %>
    </table>

</html>
