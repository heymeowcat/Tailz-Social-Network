package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.service.ChatService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "directmessages", urlPatterns = {"/directmessages"})
public class directmessages extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int uid = 0;
            int muid = 0;
            try {
                uid = Integer.parseInt(request.getParameter("uid"));
                muid = Integer.parseInt(request.getParameter("muid"));
            } catch (NumberFormatException | NullPointerException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/muid parameter");
                return;
            }

            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];
            String muidFirstName = "";
            String uidFirstName = "";
            String uidLastName = "";
            String uidImage = "";

            UserService userService = new UserService();
            muidFirstName = userService.getUserFirstName(muid);
            uidFirstName = userService.getUserFirstName(uid);
            uidLastName = userService.getUserLastName(uid);

            UserProfilePicService picService = new UserProfilePicService();
            uidImage = picService.getProfilePicPath(uid);

            ChatService chatService = new ChatService();
            java.util.List<com.heymeowcat.tailznet.entities.Chat> chatList = chatService.getConversation(muid, uid);
            if (chatList != null) {
                for (com.heymeowcat.tailznet.entities.Chat chat : chatList) {
                    if (chat.getUserReceiver() == muid) {
                        out.write("<div class='message__list'>");
                        out.write("<div class='message__item message__item--bot'>");
                        out.write("<span class='message message--bot " + Dcolor + "'  data-balloon='" + esc(String.valueOf(chat.getUserSender())) + "' data-balloon-pos='right' >");
                        out.write("<b>" + esc(muidFirstName) + "</b><br>");
                        if (chat.getChatText() != null) {
                            out.write(esc(ENCDEC.decrypt(chat.getChatText(), new KEY().secretKey)));
                        }
                        if (chat.getSrc() != null) {
                            if (chat.getChatText() == null) {
                                out.write("<img class='responsive-img' src='" + esc(ENCDEC.decrypt(chat.getSrc(), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                            } else {
                                out.write("<br><img class='responsive-img' src='" + esc(ENCDEC.decrypt(chat.getSrc(), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                            }
                        }
                        out.write("</span>");
                        out.write("</div>");
                        out.write("</div>");
                    } else if (chat.getUserReceiver() == uid) {
                        out.write("<div class='message__list'>");
                        out.write("<div class='message__item message__item--user'>");
                        out.write("<span class='message message--user " + Dcolor + "' data-balloon='" + esc(String.valueOf(chat.getUserSender())) + "' data-balloon-pos='left' >");
                        out.write("<b class='right'>Me</b><br>");
                        if (chat.getChatText() != null) {
                            out.write(esc(ENCDEC.decrypt(chat.getChatText(), new KEY().secretKey)));
                        }
                        if (chat.getSrc() != null) {
                            if (chat.getChatText() == null) {
                                out.write("<img class='responsive-img' src='" + esc(ENCDEC.decrypt(chat.getSrc(), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                            } else {
                                out.write("<br><img class='responsive-img' src='" + esc(ENCDEC.decrypt(chat.getSrc(), new KEY().secretKey)) + "' width='300px' style='border-radius: 15px'>");
                            }
                        }
                        out.write("</span>");
                        out.write("</div>");
                        out.write("</div>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading messages");
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
