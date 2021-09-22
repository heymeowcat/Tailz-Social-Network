package com.heymeowcat.tailznet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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

            if (!hasMsg && !hasSrc) {
                // Nothing to send
            } else if (hasMsg && !hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (chat_text, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, 1, 0)");
                ps.setString(1, ENCDEC.encrypt(esc(msg), new KEY().secretKey));
                ps.setInt(2, uid);
                ps.setInt(3, muid);
                ps.executeUpdate();
                pushWebSocketNotification(uid, muid);
            } else if (!hasMsg && hasSrc) {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (src, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, 1, 0)");
                ps.setString(1, ENCDEC.encrypt(esc(src), new KEY().secretKey));
                ps.setInt(2, uid);
                ps.setInt(3, muid);
                ps.executeUpdate();
                pushWebSocketNotification(uid, muid);
            } else {
                PreparedStatement ps = DB.prepare(
                        "INSERT INTO chat (chat_text, src, user_sender, users_receiver, chattype_idchattype, chatlinestatus) VALUES (?, ?, ?, ?, 1, 0)");
                ps.setString(1, ENCDEC.encrypt(esc(msg), new KEY().secretKey));
                ps.setString(2, ENCDEC.encrypt(esc(src), new KEY().secretKey));
                ps.setInt(3, uid);
                ps.setInt(4, muid);
                ps.executeUpdate();
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
