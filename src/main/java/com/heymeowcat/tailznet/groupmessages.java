/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.GroupChat;
import com.heymeowcat.tailznet.service.GroupChatService;
import com.heymeowcat.tailznet.service.UserProfilePicService;
import com.heymeowcat.tailznet.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author heymeowcat
 */
@WebServlet(name = "groupmessages", urlPatterns = {"/groupmessages"})
public class groupmessages extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            String muid = request.getParameter("muid");
            String[] themeColors = ThemeHelper.getThemeColors(uid);
            String Acolor = themeColors[0];
            String Bcolor = themeColors[1];
            String Ccolor = themeColors[2];
            String Dcolor = themeColors[3];
            String Ecolor = themeColors[4];
            String Fcolor = themeColors[5];
            GroupChatService groupChatService = new GroupChatService();
            UserService userService = new UserService();
            UserProfilePicService picService = new UserProfilePicService();

            java.util.List<GroupChat> chatList = groupChatService.getGroupMessages(muid);
            for (GroupChat chat : chatList) {
                String senderName = esc(userService.getUserFirstName(chat.getUserId())) + " " + esc(userService.getUserLastName(chat.getUserId()));
                String senderPic = picService.getProfilePicPath(chat.getUserId());
                String chatText = chat.getChatText() != null ? esc(ENCDEC.decrypt(chat.getChatText(), new KEY().secretKey)) : null;
                String chatSrc = chat.getSrc() != null ? esc(ENCDEC.decrypt(chat.getSrc(), new KEY().secretKey)) : null;

                if (chat.getUserId() != uid) {
                    out.write("<div class='message__list'>");
                    out.write("<div class='message__item message__item--bot'>");
                    out.write("<span class='message message--bot " + Dcolor + "'  data-balloon='" + senderName + "' data-balloon-pos='right' >");
                    out.write("<b>" + senderName + "</b><br>");
                    if (chatText != null) {
                        out.write(chatText);
                    }
                    if (chatSrc != null) {
                        if (chatText == null) {
                            out.write("<img class='responsive-img' src='" + chatSrc + "' width='300px' style='border-radius: 15px'>");
                        } else {
                            out.write("<br><img class='responsive-img' src='" + chatSrc + "' width='300px' style='border-radius: 15px'>");
                        }
                    }
                    out.write("</span>");
                    out.write("</div>");
                    out.write("</div>");
                } else if (chat.getUserId() == uid) {
                    out.write("<div class='message__list'>");
                    out.write("<div class='message__item message__item--user'>");
                    out.write("<span class='message message--user " + Dcolor + " ' data-balloon='" + senderName + "' data-balloon-pos='left' >");
                    out.write("<b class='right'>Me</b><br>");
                    if (chatText != null) {
                        out.write(chatText);
                    }
                    if (chatSrc != null) {
                        if (chatText == null) {
                            out.write("<img class='responsive-img' src='" + chatSrc + "' width='300px' style='border-radius: 15px'>");
                        } else {
                            out.write("<br><img class='responsive-img' src='" + chatSrc + "' width='300px' style='border-radius: 15px'>");
                        }
                    }
                    out.write("</span>");
                    out.write("</div>");
                    out.write("</div>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading group messages");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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