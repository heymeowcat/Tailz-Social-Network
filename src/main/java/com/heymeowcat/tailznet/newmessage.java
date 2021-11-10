package com.heymeowcat.tailznet;

import com.heymeowcat.tailznet.entities.Chat;
import com.heymeowcat.tailznet.service.ChatService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "newmessage", urlPatterns = {"/newmessage"})
public class newmessage extends HttpServlet {
    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }


    private void pushWebSocketNotification(int senderId, int receiverId) {
        try {
            ChatMessage wsMsg = new ChatMessage("new_message", String.valueOf(senderId), String.valueOf(receiverId), "");
            ChatWebSocket.sendMessageToUser(String.valueOf(receiverId), wsMsg.toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid uid/muid");
                return;
            }
            String msg = request.getParameter("msg");
            String src = request.getParameter("src");
            boolean hasMsg = msg != null && !msg.isEmpty();
            boolean hasSrc = src != null && !src.equals("undefined");

            ChatService chatService = new ChatService();

            if (!hasMsg && !hasSrc) {
                // Nothing to send
            } else if (hasMsg && !hasSrc) {
                Chat chat = new Chat();
                chat.setChatText(ENCDEC.encrypt(esc(msg), new KEY().secretKey));
                chat.setUserSender(uid);
                chat.setUserReceiver(muid);
                chat.setChatTypeId(1);
                chat.setChatlineStatus(0);
                chatService.saveMessage(chat);
                pushWebSocketNotification(uid, muid);
            } else if (!hasMsg && hasSrc) {
                Chat chat = new Chat();
                chat.setSrc(ENCDEC.encrypt(esc(src), new KEY().secretKey));
                chat.setUserSender(uid);
                chat.setUserReceiver(muid);
                chat.setChatTypeId(1);
                chat.setChatlineStatus(0);
                chatService.saveMessage(chat);
                pushWebSocketNotification(uid, muid);
            } else {
                Chat chat = new Chat();
                chat.setChatText(ENCDEC.encrypt(esc(msg), new KEY().secretKey));
                chat.setSrc(ENCDEC.encrypt(esc(src), new KEY().secretKey));
                chat.setUserSender(uid);
                chat.setUserReceiver(muid);
                chat.setChatTypeId(1);
                chat.setChatlineStatus(0);
                chatService.saveMessage(chat);
                pushWebSocketNotification(uid, muid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing message");
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
