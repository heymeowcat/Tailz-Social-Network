package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "refreshmsgoverview", urlPatterns = {"/refreshmsgoverview"})
public class refreshmsgoverview extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid parameter");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];

            String esc(String s) {
                if (s == null) return "";
                return s.replace("&", "&").replace("<", "<").replace(">", ">").replace("\"", """);
            }

            // Optimized query with JOIN to avoid N+1
            String sql = "SELECT u.firstname, u.lastname, u.image, u.idusers, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='0' AND user_sender=u.idusers AND users_receiver=?) AS unseen_sent, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='0' AND users_receiver=u.idusers AND user_sender=?) AS unseen_recv, " +
                    "(SELECT COUNT(*) FROM chat WHERE chatlinestatus='1' AND users_receiver=u.idusers AND user_sender=?) AS seen_recv " +
                    "FROM users u JOIN user_profile_pic upp ON u.idusers = upp.users_idusers " +
                    "WHERE u.idusers IN (SELECT receiver FROM follow WHERE sender=?) " +
                    "AND u.idusers IN (SELECT sender FROM follow WHERE receiver=?) " +
                    "ORDER BY unseen_sent DESC, unseen_recv DESC, seen_recv DESC";

            PreparedStatement rsPs = DB.prepare(sql);
            rsPs.setInt(1, uid);
            rsPs.setInt(2, uid);
            rsPs.setInt(3, uid);
            rsPs.setInt(4, uid);
            rsPs.setInt(5, uid);
            ResultSet rs = rsPs.executeQuery();

            if (!rs.isBeforeFirst()) {
                out.write("<div class='center'><img src='img/conversation.png' class='responsiveimg' style='margin-top: 100px'></div>");
                out.write("<div class='grey-text center'>Icons made by <a href='https://www.freepik.com/' title='Freepik'>Freepik</a> from <a href='https://www.flaticon.com/' title='Flaticon'>www.flaticon.com</a> is licensed by <a href='http://creativecommons.org/licenses/by/3.0/' title='Creative Commons BY 3.0' target='_blank'>CC 3.0 BY</a></div>");
            }

            while (rs.next()) {
                int muiddd = rs.getInt(4);
                int unseenSent = rs.getInt(5);
                int unseenRecv = rs.getInt(6);
                int unseenTotal = unseenSent + unseenRecv;

                String outString;
                if (unseenTotal > 9) {
                    outString = "<b>9+</b>";
                } else if (unseenTotal == 0) {
                    outString = "<i class='material-icons " + esc(Dcolor) + "'>add</i>";
                } else {
                    outString = "<b>" + unseenTotal + "</b>";
                }

                out.write("<div class='col s6 m3 l2'>");
                out.write("    <div class='" + esc(Acolor) + " card-panel hoverable'>");
                out.write("        <img src='" + esc(rs.getString(3)) + "' class='circle responsive-img'>");
                out.write("        <div class='card-content center " + esc(Dcolor) + "'>");
                out.write("            <p class='truncate'>" + esc(rs.getString(1)) + " " + esc(rs.getString(2)) + "</p>");
                out.write("            <a onclick='setuser(" + muiddd + ");' class='btn-floating " + esc(Bcolor) + " " + esc(Dcolor) + " waves-effect'>");
                out.write(outString);
                out.write("            </a>");
                out.write("        </div>");
                out.write("    </div>");
                out.write("</div>");
            }

            // Group chats - optimized with JOIN
            String groupSql = "SELECT g.group_id, g.group_name, g.group_image, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='0' AND users_idusers!=? AND Groups_group_id=g.group_id) AS unseen_others, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='0' AND users_idusers=? AND Groups_group_id=g.group_id) AS unseen_self, " +
                    "(SELECT COUNT(*) FROM group_chat WHERE chatstatus='1' AND users_idusers!=? AND Groups_group_id=g.group_id) AS seen_others " +
                    "FROM `groups` g " +
                    "WHERE g.group_id IN (SELECT Groups_group_id FROM group_members WHERE members=?) " +
                    "ORDER BY unseen_others DESC, unseen_self DESC, seen_others DESC";

            PreparedStatement rsgroupPs = DB.prepare(groupSql);
            rsgroupPs.setInt(1, uid);
            rsgroupPs.setInt(2, uid);
            rsgroupPs.setInt(3, uid);
            rsgroupPs.setInt(4, uid);
            ResultSet rsgroup = rsgroupPs.executeQuery();

            String groupOutString = "<i class='material-icons " + esc(Dcolor) + "'>add</i>";
            while (rsgroup.next()) {
                out.write("<div class='col s6 m3 l2'>");
                out.write("    <div class='" + esc(Acolor) + " card-panel hoverable'>");
                out.write("        <img src='" + esc(rsgroup.getString(3)) + "' class='circle responsive-img'>");
                out.write("        <div class='card-content center " + esc(Dcolor) + "'>");
                out.write("            <p class='truncate'>" + esc(rsgroup.getString(2)) + "</p>");
                out.write("            <a onclick=\"setusergroup('" + esc(rsgroup.getString(1)) + "');\" class='btn-floating " + esc(Bcolor) + " " + esc(Dcolor) + " waves-effect'>");
                out.write(groupOutString);
                out.write("            </a>");
                out.write("        </div>");
                out.write("    </div>");
                out.write("</div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading message overview");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
