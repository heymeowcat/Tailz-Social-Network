package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.ChatService;
import com.heymeowcat.tailznet.service.GroupChatService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "refreshmsgoverview", urlPatterns = {"/refreshmsgoverview"})
public class refreshmsgoverview extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


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

            ChatService chatService = new ChatService();
            GroupChatService groupChatService = new GroupChatService();

            List<Object[]> userChatResults = chatService.getMessageOverview(uid);

            if (userChatResults == null || userChatResults.isEmpty()) {
                out.write("<div class='center'><img src='img/conversation.png' class='responsiveimg' style='margin-top: 100px'></div>");
                out.write("<div class='grey-text center'>Icons made by <a href='https://www.freepik.com/' title='Freepik'>Freepik</a> from <a href='https://www.flaticon.com/' title='Flaticon'>www.flaticon.com</a> is licensed by <a href='http://creativecommons.org/licenses/by/3.0/' title='Creative Commons BY 3.0' target='_blank'>CC 3.0 BY</a></div>");
            }

            if (userChatResults != null) {
                for (Object[] row : userChatResults) {
                    int muiddd = ((Number) row[3]).intValue();
                    int unseenSent = ((Number) row[4]).intValue();
                    int unseenRecv = ((Number) row[5]).intValue();
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
                    out.write("        <img src='" + esc((String) row[3]) + "' class='circle responsive-img'>");
                    out.write("        <div class='card-content center " + esc(Dcolor) + "'>");
                    out.write("            <p class='truncate'>" + esc((String) row[0]) + " " + esc((String) row[1]) + "</p>");
                    out.write("            <a onclick='setuser(" + muiddd + ");' class='btn-floating " + esc(Bcolor) + " " + esc(Dcolor) + " waves-effect'>");
                    out.write(outString);
                    out.write("            </a>");
                    out.write("        </div>");
                    out.write("    </div>");
                    out.write("</div>");
                }
            }

            List<Object[]> groupResults = groupChatService.getGroupMessageOverview(uid);

            String groupOutString = "<i class='material-icons " + esc(Dcolor) + "'>add</i>";
            if (groupResults != null) {
                for (Object[] row : groupResults) {
                    out.write("<div class='col s6 m3 l2'>");
                    out.write("    <div class='" + esc(Acolor) + " card-panel hoverable'>");
                    out.write("        <img src='" + esc((String) row[2]) + "' class='circle responsive-img'>");
                    out.write("        <div class='card-content center " + esc(Dcolor) + "'>");
                    out.write("            <p class='truncate'>" + esc((String) row[1]) + "</p>");
                    out.write("            <a onclick=\"setusergroup('" + esc((String) row[0]) + "');\" class='btn-floating " + esc(Bcolor) + " " + esc(Dcolor) + " waves-effect'>");
                    out.write(groupOutString);
                    out.write("            </a>");
                    out.write("        </div>");
                    out.write("    </div>");
                    out.write("</div>");
                }
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
